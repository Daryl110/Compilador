/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Others;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Structure.TokensFlow;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Statements.Expressions.ExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.ArrowFunctionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.InvokeFunctionStatement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 */
public class ReturnStatement extends Statement {

    private Statement returnValue;
    
    public ReturnStatement(Statement root) {
        super(root);
        returnValue = null;
    }

    public ReturnStatement(Statement root, int positionBack) {
        super(root, positionBack);
        returnValue = null;
    }
    
    public void setReturnValue(Statement returnValue){
        this.returnValue = returnValue;
    }

    public Statement getReturnValue() {
        return returnValue;
    }

    @Override
    public String toString() {
        return SyntacticTypes.RETURN_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {

        if (lexeme != null
                && (lexeme.getType().equals(LexemeTypes.FUNCTIONS) && lexeme.getWord().equals("return"))) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && (lexeme.getType().equals(LexemeTypes.OTHERS)
                    && (lexeme.getWord().equals("NaN") || lexeme.getWord().equals("null")))) {
                this.childs.add(lexeme);
                lexeme = tokensFlow.move();
                this.returnValue = lexeme;
            } else {
                Statement expression = new ExpressionStatement(this, tokensFlow.getPositionCurrent());
                expression = expression.analyze(tokensFlow, lexeme);
                if (expression != null) {
                    this.childs.add(expression);
                    lexeme = tokensFlow.getCurrentToken();
                    this.returnValue = expression;
                } else {
                    Statement invokeFunction = new InvokeFunctionStatement(this, tokensFlow.getPositionCurrent());
                    invokeFunction = invokeFunction.analyze(tokensFlow, lexeme);
                    if (invokeFunction != null) {
                        this.childs.add(invokeFunction);
                        lexeme = tokensFlow.getCurrentToken();
                        this.returnValue = invokeFunction;
                    } else {
                        Statement arrowFunction = new ArrowFunctionStatement(this, tokensFlow.getPositionCurrent());
                        arrowFunction = arrowFunction.analyze(tokensFlow, lexeme);
                        if (arrowFunction != null) {
                            this.childs.add(arrowFunction);
                            lexeme = tokensFlow.getCurrentToken();
                            this.returnValue = arrowFunction;
                        } else {
                            throw new SyntaxError("[Error] : "
                                    + tokensFlow.getCurrentToken().toString()
                                    + " se esperaba una expresion valida de retorno");
                        }
                    }
                }
            }

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.childs.add(lexeme);
                tokensFlow.move();
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

    @Override
    public boolean withContext() {
        return false;
    }

    @Override
    public String parse() {
        return this.childs.stream().map((child) -> child.parse()+" ").reduce("", String::concat);
    }

}
