/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Switch;

import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Lexeme;
import eam.analizador_sintactico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.Expressions.ExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;
import eam.analizador_sintactico.Models.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class SwitchStatement extends Statement {

    public SwitchStatement(Statement root) {
        super(root);
    }

    public SwitchStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.SWITCH_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.SELECTIVE_CONTROL_STRUCTURE)
                && lexeme.getWord().equals("switch"))) {

            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {

                this.childs.add(lexeme);

                Statement expression = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                expression = expression.analyze(tokensFlow, tokensFlow.move());
                if (expression != null) {
                    this.childs.add(expression);
                    lexeme = tokensFlow.getCurrentToken();

                    if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                        this.childs.add(lexeme);
                        lexeme = tokensFlow.move();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_BRACES)) {
                            this.childs.add(lexeme);
                            lexeme = tokensFlow.move();

                            while (lexeme != null && !lexeme.getType().equals(LexemeTypes.CLOSE_BRACES)) {

                                Statement caseStatement = new CaseStatement(this, tokensFlow.getPositionCurrent());
                                caseStatement = caseStatement.analyze(tokensFlow, lexeme);
                                if (caseStatement != null) {
                                    this.childs.add(caseStatement);
                                    lexeme = tokensFlow.getCurrentToken();
                                } else {
                                    throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba un case valido");
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
                            + " se esperaba una expesion valida");
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
