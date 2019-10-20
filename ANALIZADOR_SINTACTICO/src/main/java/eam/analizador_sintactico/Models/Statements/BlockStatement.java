/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements;

import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Lexeme;
import eam.analizador_sintactico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.Assignment.IncrementalDecrementalOperationStatement;
import eam.analizador_sintactico.Models.Statements.Assignment.OthersAssignmentsStatement;
import eam.analizador_sintactico.Models.Statements.Assignment.SimpleAssignmentStatement;
import eam.analizador_sintactico.Models.Statements.Functions.FunctionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.InvokeFunctionStatement;
import eam.analizador_sintactico.Models.Statements.IF.IfStatement;
import eam.analizador_sintactico.Models.Statements.Iterators.ForEachStatement;
import eam.analizador_sintactico.Models.Statements.Iterators.ForStatement;
import eam.analizador_sintactico.Models.Statements.Iterators.UntilStatement;
import eam.analizador_sintactico.Models.Statements.Iterators.WhileStatement;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Switch.SwitchStatement;
import eam.analizador_sintactico.Models.TokensFlow;

/**
 *
 * @author Daryl Ospina
 */
public class BlockStatement extends Statement {

    private Statement statement;

    public BlockStatement(Statement root) {
        super(root);
    }

    public BlockStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return this.statement.toString();
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        this.statement = new FunctionStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new UntilStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new SwitchStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new IfStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new WhileStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new ForStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new ForEachStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new SimpleAssignmentStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new InvokeFunctionStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            lexeme = tokensFlow.getCurrentToken();

            if (lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.statement.addChild(lexeme);
                tokensFlow.move();
                return this.statement;
            }
            if (this.positionBack != -1) {
                tokensFlow.moveTo(this.positionBack);
            } else {
                tokensFlow.backTrack();
            }
            return null;
        }
        this.statement = new OthersAssignmentsStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            return this.statement;
        }
        this.statement = new IncrementalDecrementalOperationStatement(this.root, tokensFlow.getPositionCurrent());
        this.statement = this.statement.analyze(tokensFlow, tokensFlow.getCurrentToken());
        if (this.statement != null) {
            lexeme = tokensFlow.getCurrentToken();

            if (lexeme.getType().equals(LexemeTypes.DELIMITERS)) {
                this.statement.addChild(lexeme);
                tokensFlow.move();
                return this.statement;
            }
            if (this.positionBack != -1) {
                tokensFlow.moveTo(this.positionBack);
            } else {
                tokensFlow.backTrack();
            }
            return null;
        }
        //Las demas sentencias van debajo o arriba de la sentencia de arriba de este comentario
        if (this.positionBack != -1) {
            tokensFlow.moveTo(this.positionBack);
        } else {
            tokensFlow.backTrack();
        }
        throw new SyntaxError(lexeme.toString());
    }
}
