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
import eam.analizador_sintactico.Models.Statements.Expressions.ExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 */
public class InvokeFunctionStatement extends Statement {

    private boolean atLeastOneInvoke;
    private Statement expression;

    public InvokeFunctionStatement(Statement root) {
        super(root);
        this.atLeastOneInvoke = false;
    }

    public InvokeFunctionStatement(Statement root, int positionBack) {
        super(root, positionBack);
        this.atLeastOneInvoke = false;
    }

    @Override
    public String toString() {
        return SyntacticTypes.INVOKE_FUNCTION_STATMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                && lexeme.getWord().equals("-")) && this.atLeastOneInvoke) {
            Lexeme lexeme1 = tokensFlow.move();

            if (lexeme1 != null && (lexeme1.getType().equals(LexemeTypes.RELATIONAL_OPERATORS)
                    && lexeme1.getWord().equals(">"))) {
                this.childs.add(lexeme);
                this.childs.add(lexeme1);
                this.atLeastOneInvoke = false;

                return this.createChildInvokeFunction(tokensFlow, tokensFlow.move());
            } else {
                tokensFlow.moveTo(tokensFlow.getPositionCurrent() - 1);
                return this;
            }
        } else if (this.atLeastOneInvoke && (lexeme == null || !lexeme.getType().equals(LexemeTypes.IDENTIFIERS))) {
            return this;
        }

        if (lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();

                if (lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {

                    this.atLeastOneInvoke = true;
                    this.childs.add(lexeme);

                    lexeme = tokensFlow.move();

                    if (lexeme != null && (lexeme.getType().equals(LexemeTypes.ARITHMETIC_OPERATORS)
                            && lexeme.getWord().equals("-")) && this.atLeastOneInvoke) {
                        return analyze(tokensFlow, lexeme);
                    } else {
                        return this;
                    }
                } else {
                    Statement statement = recursiveAnalyze(tokensFlow, lexeme);

                    if (statement != null) {

                        lexeme = tokensFlow.getCurrentToken();

                        if (lexeme != null && lexeme.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {

                            this.atLeastOneInvoke = true;
                            this.childs.add(lexeme);
                            return analyze(tokensFlow, tokensFlow.move());
                        } else {
                            if (lexeme == null) {
                                lexeme = tokensFlow.moveTo(tokensFlow.getPositionCurrent() - 1);
                                throw new SyntaxError("[Error] : "
                                        + "se esperaba un ) al final de " + this.toString() 
                                        + " posicion: row: " + lexeme.getRow() + " - columna: " + lexeme.getColumn());
                            } else {
                                throw new SyntaxError("[Error] : "
                                        + tokensFlow.moveTo(tokensFlow.getPositionCurrent() - 1).toString()
                                        + " se esperaba un ) ");
                            }
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

    private Statement recursiveAnalyze(TokensFlow tokensFlow, Lexeme lexeme) {
        this.expression = new ExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, lexeme);
        if (this.expression != null) {
            this.childs.add(this.expression);
            this.atLeastOneInvoke = true;
            lexeme = tokensFlow.getCurrentToken();

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                    && lexeme.getWord().equals(","))) {

                this.childs.add(lexeme);
                return recursiveAnalyze(tokensFlow, tokensFlow.move());
            } else {
                return this;
            }
        } else {
            return this.createChildInvokeFunction(tokensFlow, lexeme);
        }
    }

    private Statement createChildInvokeFunction(TokensFlow tokensFlow, Lexeme lexeme) {
        InvokeFunctionStatement invokeFunction = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
        invokeFunction = (InvokeFunctionStatement) invokeFunction.analyze(tokensFlow, lexeme);
        if (invokeFunction != null) {
            this.childs.add(invokeFunction);
            this.atLeastOneInvoke = true;
            lexeme = tokensFlow.getCurrentToken();

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                    && lexeme.getWord().equals(","))) {

                this.childs.add(lexeme);
                return recursiveAnalyze(tokensFlow, tokensFlow.move());
            } else {
                return this;
            }
        } else {
            throw new SyntaxError("[Error] : "
                                        + tokensFlow.getCurrentToken().toString()
                                        + " se esperaba una expresion valida ");
        }
    }

    @Override
    public boolean withContext() {
        return false;
    }

}
