/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Assignment;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_lexico.Models.Statement;
import eam.analizador_lexico.Models.TokensFlow;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Statements.Expressions.NumericExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Expressions.StringExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.InvokeFunctionStatement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 */
public class OthersAssignmentsStatement extends Statement {

    private Lexeme operator;

    public OthersAssignmentsStatement(Statement root) {
        super(root);
    }

    public OthersAssignmentsStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.OTHERS_ASSIGNMENTS_STATMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.ASSIGNMENT_OPERATORS)
                    && !lexeme.getWord().equals("=")) {
                this.childs.add(lexeme);
                this.operator = lexeme;
                lexeme = tokensFlow.move();

                Statement value;
                value = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
                value = value.analyze(tokensFlow, lexeme);
                if (value != null) {
                    this.childs.add(value);
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                        this.childs.add(lexeme);
                        tokensFlow.move();

                        return this;
                    } else {
                        if (lexeme == null) {
                            lexeme = tokensFlow.moveTo(tokensFlow.getPositionCurrent() - 1);
                            throw new SyntaxError("[Error] : "
                                    + "se esperaba un ; al final de " + this.toString() + " posicion: row: " + lexeme.getRow() + " - columna: " + lexeme.getColumn());
                        } else {
                            throw new SyntaxError("[Error] : "
                                    + tokensFlow.getCurrentToken().toString()
                                    + " se esperaba un ; ");
                        }
                    }
                } else {
                    value = new NumericExpressionStatement(this, tokensFlow.getPositionCurrent());
                    value = value.analyze(tokensFlow, lexeme);
                    if (value != null) {
                        this.childs.add(value);
                        lexeme = tokensFlow.getCurrentToken();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                            this.childs.add(lexeme);
                            tokensFlow.move();

                            return this;
                        } else {
                            if (lexeme == null) {
                                lexeme = tokensFlow.moveTo(tokensFlow.getPositionCurrent() - 1);
                                throw new SyntaxError("[Error] : "
                                        + "se esperaba un ; al final de " + this.toString() + " posicion: row: " + lexeme.getRow() + " - columna: " + lexeme.getColumn());
                            } else {
                                throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba un ; ");
                            }
                        }
                    } else {
                        value = new StringExpressionStatement(this.root, tokensFlow.getPositionCurrent());
                        value = value.analyze(tokensFlow, lexeme);
                        if (this.operator.getWord().equals("+=") && value != null) {
                            this.childs.add(value);
                            lexeme = tokensFlow.getCurrentToken();

                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                                this.childs.add(lexeme);
                                tokensFlow.move();

                                return this;
                            } else {
                                if (this.positionBack != -1) {
                                    tokensFlow.moveTo(this.positionBack);
                                } else {
                                    tokensFlow.backTrack();
                                }
                                return null;
                            }
                        }else if (value != null) {
                            throw new SyntaxError("[Error] : una concatenacion solo puede hacerce con += como operador de asignacion");
                        }else if (value == null) {
                            throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba una expresion valida entre invocacion de funciones, expresion numerica o cadena");
                        }
                    }
                }
            }
        }
        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        return null;
    }

}
