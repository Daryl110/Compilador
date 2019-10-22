/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.Statement;
import eam.analizador_lexico.Models.TokensFlow;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author Daryl Ospina
 */
public class CompilationUnit extends Statement {

    public CompilationUnit(Statement root) {
        super(root);
    }

    public CompilationUnit(Statement root, int positionBack) {
        super(root, positionBack);
    }

    @Override
    public String toString() {
        return SyntacticTypes.COMPILATION_UNIT;
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) throws SyntaxError{
        while (lexeme != null) {
            Statement blockStatement = new BlockStatement(this, tokensFlow.getPositionCurrent());
            blockStatement = blockStatement.analyze(tokensFlow, lexeme);
            if (blockStatement != null) {
                this.childs.add(blockStatement);
                lexeme = tokensFlow.getCurrentToken();
            } else {
                lexeme = tokensFlow.getCurrentToken();
                throw new SyntaxError("[Error]: "+lexeme.toString());
            }
        }
        return this;
    }
}
