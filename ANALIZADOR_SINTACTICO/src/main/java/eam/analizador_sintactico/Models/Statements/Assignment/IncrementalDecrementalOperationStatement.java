/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Assignment;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_lexico.Models.Statement;
import eam.analizador_lexico.Models.TokensFlow;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 */
public class IncrementalDecrementalOperationStatement extends Statement {

    public IncrementalDecrementalOperationStatement(Statement root) {
        super(root);
    }

    public IncrementalDecrementalOperationStatement(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.INCREMENTAL_DECREMENTAL_OPERATION_STATEMENT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        if (lexeme != null && lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
            this.childs.add(lexeme);
            lexeme = tokensFlow.move();

            if (lexeme != null && lexeme.getType().equals(LexemeTypes.INCREMENTAL_AND_DECREMENTAL_OPERATORS)) {
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

}
