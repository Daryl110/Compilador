/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Models;

import eam.analizador_lexico.Controllers.Automatas.A_ArithmeticOperators;
import eam.analizador_lexico.Controllers.Automatas.A_AssignmentOperators;
import eam.analizador_lexico.Controllers.Automatas.A_Comments;
import eam.analizador_lexico.Controllers.Automatas.A_DataTypes;
import eam.analizador_lexico.Controllers.Automatas.A_Delimiters;
import eam.analizador_lexico.Controllers.Automatas.A_Functions;
import eam.analizador_lexico.Controllers.Automatas.A_GroupingSymbols;
import eam.analizador_lexico.Controllers.Automatas.A_Identifiers;
import eam.analizador_lexico.Controllers.Automatas.A_IncrementalDecrementalOperators;
import eam.analizador_lexico.Controllers.Automatas.A_IterativeControlStructure;
import eam.analizador_lexico.Controllers.Automatas.A_LogicalOperators;
import eam.analizador_lexico.Controllers.Automatas.A_Numbers;
import eam.analizador_lexico.Controllers.Automatas.A_Others;
import eam.analizador_lexico.Controllers.Automatas.A_RelationalOperators;
import eam.analizador_lexico.Controllers.Automatas.A_SelectiveControlStructure;
import eam.analizador_lexico.Controllers.Automatas.A_String;
import java.util.ArrayList;

/**
 *
 * @author Daryl Ospina
 */
public class LexicalAnalyzer {

    private String text;
    private final ArrayList<Lexeme> lexemes;

    /* Automatas */
    private final A_IterativeControlStructure aIterativeControlStructure;
    private final A_DataTypes aDataTypes;
    private final A_Functions aFunctions;
    private final A_Others aOther;
    private final A_SelectiveControlStructure aSelectiveStructures;
    private final A_Identifiers aIdentifiers;
    private final A_Numbers aNumbers;
    private final A_Delimiters aDelimiters;
    private final A_GroupingSymbols aGroupingSymbols;
    private final A_ArithmeticOperators aArithmeticOperator;
    private final A_LogicalOperators aLogicalOperators;
    private final A_RelationalOperators a_RelationalOperators;
    private final A_AssignmentOperators a_AssignmentOperators;
    private final A_IncrementalDecrementalOperators a_IncrementalDecrementalOperators;
    private final A_Comments a_Comments;
    private final A_String a_String;

    public LexicalAnalyzer(String text) {
        this.text = text + " ";
        this.lexemes = new ArrayList<>();

        this.aIterativeControlStructure = new A_IterativeControlStructure();
        this.aDataTypes = new A_DataTypes();
        this.aFunctions = new A_Functions();
        this.aOther = new A_Others();
        this.aSelectiveStructures = new A_SelectiveControlStructure();
        this.aIdentifiers = new A_Identifiers();
        this.aNumbers = new A_Numbers();
        this.aDelimiters = new A_Delimiters();
        this.aGroupingSymbols = new A_GroupingSymbols();
        this.aArithmeticOperator = new A_ArithmeticOperators();
        this.aLogicalOperators = new A_LogicalOperators();
        this.a_RelationalOperators = new A_RelationalOperators();
        this.a_AssignmentOperators = new A_AssignmentOperators();
        this.a_IncrementalDecrementalOperators = new A_IncrementalDecrementalOperators();
        this.a_Comments = new A_Comments();
        this.a_String = new A_String();
    }

        public ArrayList<Lexeme> analyze() {
        String word = "";
        int row = 0, column = -1, count = 0;
        Lexeme lexeme = null;
        for (int i = 0; i < this.text.length(); i++) {
            if (word.length() == 1) {
                column = count - 1;
            }
            char character = this.text.charAt(i);
            if ((Character.isLetter(character)
                    || Character.isDigit(character)
                    || character == '_'
                    || character == '.')
                    && (this.text.length() - 1 == i)) {
                word += character;
                this.text += " ";
            } else if (Character.isLetter(character)
                    || Character.isDigit(character)
                    || character == '_'
                    || character == '.') {
                word += character;
            } else if (character == '\u0040'
                    || character == '\u00B0'
                    || character == '\u00AC'
                    || character == '\u0023'
                    || character == '\u0024'
                    || character == '\u003F'
                    || character == '\u00A1'
                    || character == '\u00BF'
                    || character == '\u0060'
                    || character == '\u00B4') {

                word += character;
            } else if (!Character.isLetter(character)
                    || !Character.isDigit(character)
                    || character != '_'
                    || character != '.') {
                if (!word.isEmpty()) {
                    if (character == ' ' || !Character.isLetter(character)
                            || !Character.isDigit(character)
                            || character != '_'
                            || character != '.') {
                        validate(lexeme, word, row, column);
                    } else {
                        count++;
                        if (character == '\n') {
                            validate(lexeme, word, row, column);
                            row += 1;
                            count = 0;
                            continue;
                        }
                    }

                }
                column = count;
                lexeme = this.aOther.execute(character + "", row, column);
                if (lexeme != null) {
                    this.lexemes.add(lexeme);
                } else {
                    lexeme = this.a_Comments.execute(character + "", row, column);
                    if (lexeme != null) {
                        this.lexemes.add(lexeme);
                    } else {
                        lexeme = this.aDelimiters.execute(character + "", row, column);
                        if (lexeme != null) {
                            this.lexemes.add(lexeme);
                        } else {
                            lexeme = this.aGroupingSymbols.execute(character + "", row, column);
                            if (lexeme != null) {
                                this.lexemes.add(lexeme);
                            } else {
                                if (character == '%' && this.text.charAt(i + 1) == '='
                                        || character == '+' && this.text.charAt(i + 1) == '='
                                        || character == '-' && this.text.charAt(i + 1) == '='
                                        || character == '*' && this.text.charAt(i + 1) == '='
                                        || character == '/' && this.text.charAt(i + 1) == '=') {
                                    lexeme = this.a_AssignmentOperators.execute(character + "" + this.text.charAt(i + 1), row, column);
                                    i = i + 1;
                                    count = count + 1;
                                } else if (character == '=' && this.text.charAt(i + 1) == '=') {
                                    lexeme = this.a_RelationalOperators.execute(character + "" + this.text.charAt(i + 1), row, column);
                                    i = i + 1;
                                    count = count + 1;
                                } else if (lexeme == null && character == '='
                                        && (character == '=')) {
                                    lexeme = this.a_AssignmentOperators.execute(character + "", row, column);
                                } else if (character == '*' && this.text.charAt(i + 1) == '*'
                                        || character == '-' && this.text.charAt(i + 1) == '-'
                                        || character == '+' && this.text.charAt(i + 1) == '+') {
                                    lexeme = this.a_IncrementalDecrementalOperators.execute(character + "" + this.text.charAt(i + 1), row, column);
                                    i = i + 1;
                                    count = count + 1;
                                }
                                if (lexeme != null) {
                                    this.lexemes.add(lexeme);
                                } else {
                                    if (character == '!' && this.text.charAt(i + 1) != '=') {
                                        lexeme = this.aLogicalOperators.execute(character + "", row, column);
                                    } else if ((character == '&' && this.text.charAt(i + 1) == '&')
                                            || (character == '|' && this.text.charAt(i + 1) == '|')) {
                                        lexeme = this.aLogicalOperators.execute(this.text.charAt(i + 1) + "" + character, row, column);
                                        i = i + 1;
                                        count = count + 1;
                                    }
                                    if (lexeme != null) {
                                        this.lexemes.add(lexeme);
                                    } else {
                                        //Operadores relaciones
                                        if (character == '!' && this.text.charAt(i + 1) == '='
                                                || character == '<' && this.text.charAt(i + 1) == '='
                                                || character == '>' && this.text.charAt(i + 1) == '='
                                                || character == '=' && this.text.charAt(i + 1) == '=') {
                                            lexeme = this.a_RelationalOperators.execute(character + "" + this.text.charAt(i + 1), row, column);
                                            i = i + 1;
                                            count = count + 1;
                                        } else if (character == '<' || character == '>') {
                                            lexeme = this.a_RelationalOperators.execute(character + "", row, column);
                                        }
                                        if (lexeme != null) {

                                            this.lexemes.add(lexeme);
                                        } else {
                                            lexeme = this.aArithmeticOperator.execute(character + "", row, column);
                                            if (lexeme != null) {

                                                this.lexemes.add(lexeme);
                                            } else {
                                                lexeme = this.a_String.execute(character + "", row, column);
                                                if (lexeme != null) {

                                                    this.lexemes.add(lexeme);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (lexeme == null && word.equals("") && !Character.isLetter(character)
                        && !Character.isDigit(character) && character != '\n') {
                    if (character != ' ') {
                        word = character + "";
                        if (column == -1) {
                            column = 0;
                        }
                        if ((character == '&' && this.text.charAt(i + 1) == '&')
                                || (character == '|' && this.text.charAt(i + 1) == '|')) {
                            i = i + 1;
                            count = count + 1;
                        } else if (this.text.charAt(i + 1) == ' ') {
                            lexeme = new Lexeme(row, column, word, "Error");
                            this.lexemes.add(lexeme);
                            lexeme = null;
                        } else {
                            word += character;
                            count++;
                            if (character == '\n') {
                                row += 1;
                                count = 0;
                            }
                            continue;
                        }
                    }
                }
                word = "";
            } else {
                validate(lexeme, word, row, column);
                word = "";
            }

            count++;

            if (character == '\n') {
                row += 1;
                count = 0;
            }
        }
        for (int i = 0; i < lexemes.size();) {
            if (lexemes.get(i).getWord().equals("\"")) {
                int doomy = i;
                if (i == (lexemes.size() - 1)) {
                    throw new RuntimeException("Falta otra comilla doble(\")");
                } else {
                    String aux = lexemes.get(doomy).getWord() + "";
                    i++;
                    while (i < lexemes.size()) {
                        if (lexemes.get(i).getWord().equals("\"")) {
                            aux += lexemes.get(i).getWord();
                            lexemes.remove(lexemes.get(i));
                            lexemes.get(doomy).setWord(aux);
                            i = doomy + 1;
                            break;
                        }
                        aux += lexemes.get(i).getWord() + " ";
                        lexemes.remove(lexemes.get(i));
                        if (i >= lexemes.size()) {
                            throw new RuntimeException("Falta otra comilla doble(\")");
                        }

                    }

                }
            } else if (lexemes.get(i).getWord().equals("\'")) {
                int doomy = i;
                if (i == (lexemes.size() - 1)) {
                    throw new RuntimeException("Falta otra comilla simple(\")");
                }
                String aux = lexemes.get(doomy).getWord() + "";
                i++;
                if (lexemes.get(i).getWord().length() == 1 && lexemes.get(i + 1).getWord().equals("\'")) {
                    aux += lexemes.get(i).getWord() + "\'";
                    lexemes.remove(lexemes.get(i));
                    lexemes.remove(lexemes.get(i));
                    lexemes.get(doomy).setWord(aux);
                    i = doomy + 1;
                } else {
                    throw new RuntimeException("en las comillas simples solo deden haber caracteres");
                }
            } else if (lexemes.get(i).getWord().equals("~")) {
                lexemes.remove(lexemes.get(i));
                do {
                    if (lexemes.get(i).getWord().equals("~")) {
                        lexemes.remove(lexemes.get(i));
                        break;
                    } else {
                        lexemes.remove(lexemes.get(i));
                    }
                } while (i < lexemes.size());
            } else {
                i++;
            }
        }
        lexemes.stream().filter((lexeme1) -> (lexeme1.getType().equalsIgnoreCase("Error"))).forEachOrdered((lexeme1) -> {
            throw new RuntimeException("Caracter in valido en la posicion "
                    + lexeme1.getRow() + " : " + lexeme1.getColumn());
        });
        return lexemes;
    }

    public void validate(Lexeme lexeme, String word, int row, int column) {
        lexeme = this.aIterativeControlStructure.execute(word, row, column);
        if (lexeme != null) {
            this.lexemes.add(lexeme);
        } else {
            lexeme = this.aDataTypes.execute(word, row, column);
            if (lexeme != null) {
                this.lexemes.add(lexeme);
            } else {
                lexeme = this.aFunctions.execute(word, row, column);
                if (lexeme != null) {
                    this.lexemes.add(lexeme);
                } else {
                    lexeme = this.aOther.execute(word, row, column);
                    if (lexeme != null) {
                        this.lexemes.add(lexeme);
                    } else {
                        lexeme = this.aSelectiveStructures.execute(word, row, column);
                        if (lexeme != null) {
                            this.lexemes.add(lexeme);
                        } else {
                            lexeme = this.aIdentifiers.execute(word, row, column);
                            if (lexeme != null) {
                                this.lexemes.add(lexeme);
                            } else {
                                lexeme = this.aNumbers.execute(word, row, column);
                                if (lexeme != null) {
                                    this.lexemes.add(lexeme);
                                } else {
                                    lexeme = new Lexeme(row, column, word, "Error");
                                    this.lexemes.add(lexeme);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
