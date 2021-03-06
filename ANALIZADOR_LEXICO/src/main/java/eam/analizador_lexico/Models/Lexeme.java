/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Models;

import eam.analizador_sintactico.Models.Statements.Structure.TokensFlow;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;

/**
 *
 * @author Daryl Ospina
 */
public class Lexeme extends Statement {

    private int row;
    private int column;
    private String word;
    private String type;

    public Lexeme(Statement root, int row, int column, String word, String type) {
        super(root);
        this.row = row;
        this.column = column;
        this.word = word;
        this.type = type;
    }

    public Lexeme(Statement root, int positionBack, int row, int column, String word, String type) {
        super(root, positionBack);
        this.row = row;
        this.column = column;
        this.word = word;
        this.type = type;
    }

    public Lexeme(int row, int column, String word, String type) {
        super(null, -1);
        this.row = row;
        this.column = column;
        this.word = word;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumns(int column) {
        this.column = column;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{ token: " + this.word + ", tipo: " + this.type + ", posicion: { row: " + this.row + ", column: " + this.column + " } }";
    }

    @Override
    public Statement analyze(TokensFlow tokensFlow, Lexeme lexeme) {
        return this;
    }

    @Override
    public boolean withContext() {
        return false;
    }

    @Override
    public String parse() {
        if (this.parse == null) {
            if (this.type.equals(LexemeTypes.DATA_TYPE)) {
                return "var";
            }
            if (this.type.equals(LexemeTypes.ASSIGNMENT_OPERATORS)
                    && this.word.equals("^=")) {
                return "**=";
            }
            if(this.type.equals(LexemeTypes.ARITHMETIC_OPERATORS)
                    && this.word.equals("^")){
                return "**";
            }
            return this.word;
        }
        return this.parse;
    }

}
