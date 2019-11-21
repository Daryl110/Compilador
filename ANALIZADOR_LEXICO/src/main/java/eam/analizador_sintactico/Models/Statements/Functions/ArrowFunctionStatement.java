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
public class ArrowFunctionStatement extends Statement {

    public ArrowFunctionStatement(Statement root) {
        super(root);
    }

    public ArrowFunctionStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.ARROW_FUNCTION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
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
                } else {
                    if (this.positionBack != -1) {
                        tokensFlow.moveTo(this.positionBack);
                    } else {
                        tokensFlow.backTrack();
                    }
                    return null;
                }
            }

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();

                if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                        && lexeme.getWord().equals("-"))) {
                    this.childs.add(lexeme);
                    lexeme = tokensFlow.move();

                    if (lexeme != null && (lexeme.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)
                            && lexeme.getWord().equals(">"))) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACES)) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();

                            while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {

                                if (lexeme.getType().equals(LexemeTypes.FUNCTIONS) && lexeme.getWord().equals("return")) {
                                    Statement returnStatement = new ReturnStatement(this, tokensFlow.getPositionCurrent());
                                    returnStatement = returnStatement.analyze(tokensFlow, lexeme);
                                    if (returnStatement != null) {
                                        this.childs.add(returnStatement);
                                        lexeme = tokensFlow.getCurrentToken();
                                        break;
                                    } else {
                                        throw new SyntaxError("[Error] : "
                                                + tokensFlow.getCurrentToken().toString()
                                                + " se esperaba un return valido.");
                                    }
                                }

                                Statement statement = new BlockStatement(this, tokensFlow.getPositionCurrent());
                                statement = statement.analyze(tokensFlow, lexeme);
                                if (statement != null) {
                                    this.childs.add(statement);
                                    lexeme = tokensFlow.getCurrentToken();
                                } else {
                                    throw new SyntaxError("[Error] : "
                                            + tokensFlow.getCurrentToken().toString()
                                            + " se esperaba una sentencia valida.");
                                }
                            }

                            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {
                                this.childs.add(lexeme);
                                tokensFlow.move();

                                return this;
                            } else {
                                throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba un } ");
                            }
                        } else {
                            throw new SyntaxError("[Error] : "
                                    + tokensFlow.getCurrentToken().toString()
                                    + " se esperaba un { ");
                        }
                    } else {
                        throw new SyntaxError("[Error] : "
                                + tokensFlow.getCurrentToken().toString()
                                + " se esperaba un > ");
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

    @Override
    public boolean withContext() {
        return true;
    }
    
    @Override
    public String parse() {
        String parseo = "";
        
        for(Statement child : this.childs){
            if (child.isLeaf() && ((Lexeme)child).getWord().equals("-")) {
                parseo += " =";
            }else{
                parseo += child.parse()+" ";
            }
        }
        
        return parseo;
    }
}
