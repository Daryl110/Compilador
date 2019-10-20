/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Controllers.Automatas;

import eam.analizador_lexico.Models.Automata;
import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;

/**
 *
 * @author Daryl Ospina
 */
public class A_GroupingSymbols implements Automata{

    @Override
    public Lexeme execute(String word, int row, int column) {
        switch (word) {
            case "(":
                return new Lexeme(row, column, word, LexemeTypes.OPEN_PARENTHESIS);
            case ")":
                return new Lexeme(row, column, word, LexemeTypes.CLOSE_PARENTHESIS);
            case "[":
                return new Lexeme(row, column, word, LexemeTypes.OPEN_BRACKETS);
            case "]":
                return new Lexeme(row, column, word, LexemeTypes.CLOSE_BRACKETS);
            case "{":
                return new Lexeme(row, column, word, LexemeTypes.OPEN_BRACES);
            case "}":
                return new Lexeme(row, column, word, LexemeTypes.CLOSE_BRACES);
                
        }
        return null;
    }
    
}
