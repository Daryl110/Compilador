/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eam.analizador_semantico.Models;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;

/**
 *
 * @author daryl
 * @date 7/11/2019
 */
public class Parameter {

    private final Context context;
    private Lexeme identifier;
    private Lexeme dataType;
    
    public Parameter(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Lexeme getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Lexeme identifier) {
        if (!identifier.getType().equals(LexemeTypes.IDENTIFIERS)) {
            throw new SyntaxError("[Error] : "+identifier.getWord()+" no es un identificador valido.");
        }
        this.identifier = identifier;
    }

    public Lexeme getDataType() {
        return dataType;
    }

    public void setDataType(Lexeme dataType) {
        if (!dataType.getType().equals(LexemeTypes.DATA_TYPE)) {
            throw new SyntaxError("[Error] : "+identifier.getWord()+" no es un tipo de dato valido.");
        }
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Identificador : " + identifier.getWord() + " - Tipo : " + dataType.getWord();
    }
}
