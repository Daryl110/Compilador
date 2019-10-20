/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Models;

import java.util.List;

/**
 *
 * @author Daryl Ospina
 */
public class TokensFlow {

    private final List<Lexeme> lexemes;
    private int posCurrent;
    private int posTemp;

    public TokensFlow(List<Lexeme> lista) {
        this.lexemes = lista;
    }
    
    public Lexeme getLexeme(int index){
        return this.lexemes.get(index);
    }

    public Lexeme move() {
        posCurrent++;
        return getCurrentToken();
    }

    public Lexeme getCurrentToken() {
        if (posCurrent >= this.lexemes.size()) {
            return null;
        }
        return this.lexemes.get(posCurrent);
    }
    
    public Lexeme moveTo(int position){
        posCurrent = position;
        return  getCurrentToken();
    }
    
    public int getPositionCurrent(){
        return posCurrent;
    }

    public int getPosTemp() {
        return posTemp;
    }

    public void savePositionCurrent() {
        posTemp = posCurrent;
    }

    public void backTrack() {
        posCurrent = posTemp;
    }
}
