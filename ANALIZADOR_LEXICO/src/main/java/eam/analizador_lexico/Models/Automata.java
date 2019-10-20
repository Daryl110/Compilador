/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Models;

/**
 *
 * @author Daryl Ospina
 */
public interface Automata {
    
    public abstract Lexeme execute(String word, int row, int column);
}
