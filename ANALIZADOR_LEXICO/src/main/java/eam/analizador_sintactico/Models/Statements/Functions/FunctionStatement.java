/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Functions;

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
public class FunctionStatement extends Statement {

    public FunctionStatement(Statement root) {
        super(root);
    }

    public FunctionStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.FUNCTION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.FUNCTIONS)
                && lexeme.getWord().equals("function"))) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.DATA_TYPE)
                    || (lexeme.getType().equals(LexemeTypes.FUNCTIONS)
                    && lexeme.getWord().equals("void")))) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();

                if (lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();

                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                            Statement parameter = new ParameterStatement(this, tokensFlow.getPositionCurrent());
                            parameter = parameter.analyze(tokensFlow, lexeme);
                            if (parameter != null) {
                                this.childs.add(parameter);
                                lexeme = tokensFlow.getCurrentToken();

                                if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                                        && lexeme.getWord().equals(","))) {
                                    this.childs.add(lexeme);
                                    lexeme = tokensFlow.move();
                                } else {
                                    break;
                                }
                            }
                        }

                        try {
                            Lexeme lex = ((Lexeme) (this.childs.get(this.childs.size() - 1)));
                            if (lex.getType().equals(LexemeTypes.OTHERS) && lex.getWord().equals(",")) {
                                throw new SyntaxError("[Error] : "
                                        + lex.toString()
                                        + " se esperaba un )");
                            }
                        } catch (ClassCastException e) {
                        }

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();

                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACES)) {
                                this.childs.add(lexeme);
                                lexeme = tokensFlow.move();

                                while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {

                                    if (lexeme.getType().equals(LexemeTypes.FUNCTIONS) && lexeme.getWord().equals("return")) {

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
                                + " se esperaba un (");
                    }
                } else {
                    throw new SyntaxError("[Error] : "
                            + tokensFlow.getCurrentToken().toString()
                            + " se esperaba un identificador");
                }
            } else {
                throw new SyntaxError("[Error] : "
                        + tokensFlow.getCurrentToken().toString()
                        + " se esperaba un tipo de return valido.");
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
        return this.childs.stream().map((child) -> child.parse()+" ").reduce("", String::concat);
    }

}
