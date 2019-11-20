/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_semantico.Models;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_semantico.Exceptions.SemanticError;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daryl
 * @date 7/11/2019
 */
public class Function {

    private Context context;
    private Lexeme identifier, returnType;
    private final List<Parameter> parameters;

    public Function(Context context) {
        this.parameters = new ArrayList<>();
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
            throw new SemanticError(identifier.getWord() + " no es un "
                    + "identificador valido\nen la posicion "
                    + identifier.getRow() + ":" + identifier.getColumn());
        }
        this.identifier = identifier;
    }

    public Lexeme getReturnType() {
        return returnType;
    }

    public void setReturnType(Lexeme returnType) {
        this.returnType = returnType;
    }

    public void addParam(Parameter param) {
        for (Parameter auxParam : this.parameters) {
            if (auxParam.getIdentifier().getWord().equals(param.getIdentifier().getWord())) {
                throw new SemanticError("ya existe un parametro con este "
                        + "identificador" + identifier.getWord() + "\nen la posicion "
                        + identifier.getRow() + ":" + identifier.getColumn());
            }
        }
        this.parameters.add(param);
    }
    
    public List<Parameter> getParameters(){
        return this.parameters;
    }
}
