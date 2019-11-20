/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Expressions;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Structure.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class ExpressionStatement extends Statement {

    private Statement expression;

    public ExpressionStatement(Statement root) {
        super(root);
    }
    
    public ExpressionStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return this.expression.toString();
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexem) {
        this.expression = new ArrayExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new LogicalExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new RelationalExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new NumericExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
        }
        this.expression = new StringExpressionStatement(this.root, tokensFlow.getPositionCurrent());
        this.expression = this.expression.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.expression != null) {
            return this.expression;
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

}
