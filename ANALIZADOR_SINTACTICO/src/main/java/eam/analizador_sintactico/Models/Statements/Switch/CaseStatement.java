/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Switch;

import eam.analizador_sintactico.Models.Lexeme;
import eam.analizador_sintactico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.BlockStatement;
import eam.analizador_sintactico.Models.Statements.Expressions.ExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Others.ReturnStatement;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;
import eam.analizador_sintactico.Models.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class CaseStatement extends Statement {

    public CaseStatement(Statement root) {
        super(root);
    }

    public CaseStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.CASE_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                && (lexeme.getWord().equals("case")
                || lexeme.getWord().equals("default")))) {

            boolean caseIsDefault = lexeme.getWord().equals("default");

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (caseIsDefault) {
                return terminateCase(tokensFlow, tokensFlow.getCurrentToken());
            } else {
                Statement expression = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                expression = expression.analyze(tokensFlow, lexeme);
                if (expression != null) {
                    this.childs.add(expression);
                    return terminateCase(tokensFlow, tokensFlow.getCurrentToken());
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

    private Statement terminateCase(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                && lexeme.getWord().equals(":"))) {

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && ((lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                    && (lexeme.getWord().equals("case")
                    || lexeme.getWord().equals("default")))
                    || lexeme.getType().equals(LexemeTypes.CLOSE_BRACES))) {
                return this;
            } else {

                while (lexeme != null && !((lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                        && (lexeme.getWord().equals("case")
                        || lexeme.getWord().equals("default")))
                        || lexeme.getType().equals(LexemeTypes.CLOSE_BRACES))) {

                    if ((lexeme.getType().equals(LexemeTypes.FUNCTIONS)
                            && lexeme.getWord().equals("return"))
                            || (lexeme.getType().equals(LexemeTypes.OTHERS)
                            && lexeme.getWord().equals("break"))) {

                        if (lexeme.getWord().equals("return")) {
                            Statement returnStatement = new ReturnStatement(this, tokensFlow.getPositionCurrent());
                            returnStatement = returnStatement.analyze(tokensFlow, lexeme);
                            if (returnStatement != null) {
                                this.childs.add(returnStatement);
                                return this;
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
                    }

                    Statement blockStatment = new BlockStatement(this, tokensFlow.getPositionCurrent());
                    blockStatment = blockStatment.analyze(tokensFlow, lexeme);
                    if (blockStatment != null) {
                        this.childs.add(blockStatment);
                        lexeme = tokensFlow.getCurrentToken();
                    } else {
                        if (this.positionBack != -1) {
                            tokensFlow.moveTo(this.positionBack);
                        } else {
                            tokensFlow.backTrack();
                        }
                        return null;
                    }
                }
                return this;
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
