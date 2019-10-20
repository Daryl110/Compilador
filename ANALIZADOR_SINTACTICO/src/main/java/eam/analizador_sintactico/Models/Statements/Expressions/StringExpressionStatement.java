/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Expressions;

import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Lexeme;
import eam.analizador_sintactico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;
import eam.analizador_sintactico.Models.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class StringExpressionStatement extends Statement {

    private int openedParenthesis;
    private boolean withString;
    private boolean operator;

    public StringExpressionStatement(Statement root) {
        super(root);
        this.openedParenthesis = 0;
        this.withString = false;
        this.operator = false;
    }

    public StringExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
        this.openedParenthesis = 0;
        this.withString = false;
        this.operator = false;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
            this.openedParenthesis++;
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        } else if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis > 0) {
            this.openedParenthesis--;
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();
        }

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.STRINGS)) {
            this.childs.add(lexeme);
            this.withString = true;
            lexeme = tokensFlow.move();
            this.operator = false;

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS) && this.openedParenthesis > 0) {
                this.openedParenthesis--;
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
            }

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                    && lexeme.getWord().equals("+"))) {
                this.childs.add(lexeme);
                this.operator = true;
                return this.analyze(tokensFlow, tokensFlow.move());
            } else {
                return this.analyze(tokensFlow, lexeme);
            }
        }

        if (this.openedParenthesis == 0 && this.childs.size() > 0 && this.withString && !this.operator) {
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
            throw new SyntaxError("los parentesis de la expresion cadena, estan mal distribuidos.");
        } else if (this.operator) {
            throw new SyntaxError("la expresion cadena no puede terminar con un +");
        }

        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        return null;
    }

    @Override
    public String toString() {
        return SyntacticTypes.STRING_EXPRESSION_STATEMENT;
    }

}
