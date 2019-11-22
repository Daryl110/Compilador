/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.IF;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Structure.TokensFlow;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Statements.BlockStatement;
import eam.analizador_sintactico.Models.Statements.Others.ReturnStatement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 */
public class ElseStatement extends Statement {

    public ElseStatement(Statement root) {
        super(root);
    }

    public ElseStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.ELSE_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                && lexeme.getWord().equals("else"))) {
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

            } else if (lexeme != null && (lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                    && lexeme.getWord().equals("if"))) {

                Statement ifStatement = new IfStatement(this, tokensFlow.getPositionCurrent());
                ifStatement = ifStatement.analyze(tokensFlow, lexeme);
                if (ifStatement != null) {
                    this.childs.add(ifStatement);
                    return this;
                } else {
                    if (this.positionBack != -1) {
                        tokensFlow.moveTo(this.positionBack);
                    } else {
                        tokensFlow.backTrack();
                    }
                    return null;
                }
            } else {
                throw new SyntaxError("[Error] : "
                        + tokensFlow.getCurrentToken().toString()
                        + " se esperaba un { o una gramatica if");
            }
        }
        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        return null;
    }

    @Override
    public boolean withContext() {
        return true;
    }
    
    @Override
    public String parse() {
        if (this.parse == null) {
            return this.childs.stream().map((child) -> child.parse() + " ").reduce("", String::concat);
        }
        return this.parse;
    }
}
