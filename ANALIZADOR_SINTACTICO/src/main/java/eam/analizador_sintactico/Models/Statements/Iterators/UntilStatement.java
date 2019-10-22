/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Iterators;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_lexico.Models.Statement;
import eam.analizador_lexico.Models.TokensFlow;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Statements.BlockStatement;
import eam.analizador_sintactico.Models.Statements.Expressions.ExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.InvokeFunctionStatement;
import eam.analizador_sintactico.Models.Statements.Others.ReturnStatement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 */
public class UntilStatement extends Statement {

    public UntilStatement(Statement root) {
        super(root);
    }

    public UntilStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.UNTIL_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ITERATIVE_CONTROL_STRUCTURE)
                && lexeme.getWord().equals("until"))) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                this.childs.add(lexeme);

                lexeme = tokensFlow.move();

                Statement invokeFunction = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
                Statement logicalOrRelational = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                invokeFunction = invokeFunction.analyze(tokensFlow, lexeme);
                if (invokeFunction == null) {
                    logicalOrRelational = logicalOrRelational.analyze(tokensFlow, lexeme);
                }

                if ((invokeFunction != null) || (logicalOrRelational != null && (logicalOrRelational.toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                        || logicalOrRelational.toString().equals(SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT)))) {
                    if (invokeFunction != null) {
                        this.childs.add(invokeFunction);
                    } else if (logicalOrRelational != null) {
                        this.childs.add(logicalOrRelational);
                    }
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACES)) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();

                            while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {

                                if ((lexeme.getType().equals(LexemeTypes.FUNCTIONS) && lexeme.getWord().equals("return"))
                                        || (lexeme.getType().equals(LexemeTypes.OTHERS) && (lexeme.getWord().equals("break")
                                        || lexeme.getWord().equals("continue")))) {

                                    if (lexeme.getWord().equals("return")) {
                                        Statement returnStatement = new ReturnStatement(this, tokensFlow.getPositionCurrent());
                                        returnStatement = returnStatement.analyze(tokensFlow, lexeme);
                                        if (returnStatement != null) {
                                            this.childs.add(returnStatement);
                                            lexeme = tokensFlow.getCurrentToken();
                                            break;
                                        } else {
                                            if (this.positionBack != -1) {
                                                tokensFlow.moveTo(this.positionBack);
                                            } else {
                                                tokensFlow.backTrack();
                                            }
                                            return null;
                                        }
                                    }

                                    this.childs.add(lexeme);
                                    lexeme = tokensFlow.move();

                                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                                        this.childs.add(lexeme);
                                        lexeme = tokensFlow.move();
                                        break;
                                    } else {
                                        if (this.positionBack != -1) {
                                            tokensFlow.moveTo(this.positionBack);
                                        } else {
                                            tokensFlow.backTrack();
                                        }
                                        return null;
                                    }
                                }

                                Statement statement = new BlockStatement(this, tokensFlow.getPositionCurrent());
                                statement = statement.analyze(tokensFlow, lexeme);
                                if (statement != null) {
                                    this.childs.add(statement);
                                    lexeme = tokensFlow.getCurrentToken();
                                }
                            }

                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {
                                this.childs.add(lexeme);
                                tokensFlow.move();

                                return this;
                            } else {
                                throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba un }");
                            }
                        } else {
                            throw new SyntaxError("[Error] : "
                                    + tokensFlow.getCurrentToken().toString()
                                    + " se esperaba un {");
                        }
                    } else {
                        throw new SyntaxError("[Error] : "
                                + tokensFlow.getCurrentToken().toString()
                                + " se esperaba un )");
                    }
                } else {
                    throw new SyntaxError("[Error] : "
                            + tokensFlow.getCurrentToken().toString()
                            + " se esperaba una operacion logica o relacional");
                }
            } else {
                throw new SyntaxError("[Error] : "
                        + tokensFlow.getCurrentToken().toString()
                        + " se esperaba un )");
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
