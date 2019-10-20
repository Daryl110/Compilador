/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Controllers.Automatas;

import eam.analizador_lexico.Models.Automata;
import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Daryl Ospina
 */
public class A_Numbers implements Automata{

    @Override
    public Lexeme execute(String word, int row, int column) {
        
        Pattern regex = Pattern.compile("^\\d+(\\.\\d+)?$");
        Matcher mat = regex.matcher(word);
        
        if (mat.matches())return new Lexeme(row, column, word, LexemeTypes.NUMBERS);
        
        return null;
    }
    
}
