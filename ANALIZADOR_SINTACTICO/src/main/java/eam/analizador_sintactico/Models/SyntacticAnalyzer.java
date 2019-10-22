/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.Statement;
import eam.analizador_lexico.Models.TokensFlow;
import eam.analizador_sintactico.Models.Statements.CompilationUnit;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class SyntacticAnalyzer {

    private final ArrayList<Lexeme> lexemes;

    public SyntacticAnalyzer(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Statement analyze() throws Exception {
        TokensFlow tokensFlow = new TokensFlow(this.lexemes);
        return new CompilationUnit(null).analyze(tokensFlow, tokensFlow.getCurrentToken());
    }
}
