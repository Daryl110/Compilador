/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Expressions;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_lexico.Models.Statement;
import eam.analizador_lexico.Models.TokensFlow;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Statements.Functions.InvokeFunctionStatement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 * 
 */
public class RelationalExpressionStatement extends Statement {

    private int openedParenthesis;
    private boolean operation;

    public RelationalExpressionStatement(Statement root) {
        super(root);
        this.openedParenthesis = 0;
        this.operation = false;
    }

    public RelationalExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
        this.openedParenthesis = 0;
        this.operation = false;
    }

    @Override
    public String toString() {
        return SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
            this.childs.add(lexeme);
            this.openedParenthesis++;
            lexeme = tokensFlow.move();
        }

        Statement invokeFunction = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
        invokeFunction = invokeFunction.analyze(tokensFlow, lexeme);
        if (invokeFunction != null) {
            this.childs.add(invokeFunction);
            return this.recursiveAnalyze(tokensFlow, tokensFlow.getCurrentToken());
        } else {
            Statement numeric = new NumericExpressionStatement(this, tokensFlow.getPositionCurrent());
            numeric = numeric.analyze(tokensFlow, lexeme);
            if (numeric != null) {
                this.childs.add(numeric);
                return this.recursiveAnalyze(tokensFlow, tokensFlow.getCurrentToken());
            } else {
                Statement string = new StringExpressionStatement(this, tokensFlow.getPositionCurrent());
                string = string.analyze(tokensFlow, lexeme);
                if (string != null) {
                    this.childs.add(string);
                    return this.recursiveAnalyze(tokensFlow, tokensFlow.getCurrentToken());
                } else {
                    int size = this.childs.size() - 1;
                    if (size > 0) {
                        if (((Lexeme) (this.childs.get(size))).getType().equals(LexemeTypes.RELATIONAL_OPERATORS)) {
                            throw new SyntaxError("la expresion relacional no puede terminar con un operador");
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
        }
    }

    private Statement recursiveAnalyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis > 0) {
            this.childs.add(lexeme);
            this.openedParenthesis--;
            return this.recursiveAnalyze(tokensFlow, tokensFlow.move());
        }
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)) {
            this.childs.add(lexeme);
            this.operation = true;
            return analyze(tokensFlow, tokensFlow.move());
        } else {
            if (this.openedParenthesis == 0 && this.childs.size() > 0 && this.operation) {
                if (this.childs.size() == 1) {
                    lexeme = (Lexeme) this.childs.get(this.childs.size() - 1);
                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                        if (this.positionBack != -1) {
                            tokensFlow.moveTo(this.positionBack);
                        } else {
                            tokensFlow.backTrack();
                        }
                        return null;
                    }
                }
                return this;
            } else if (this.openedParenthesis > 0) {
                throw new SyntaxError("los parentesis de la expresion relacional, estan mal distribuidos.");
            } else {
                if (this.positionBack != -1) {
                    tokensFlow.moveTo(this.positionBack);
                } else {
                    tokensFlow.backTrack();
                }
                return null;
            }
        }
    }

}
