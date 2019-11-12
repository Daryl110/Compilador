/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_semantico.Models;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daryl
 * @date 7/11/2019
 */
public class Function {

    private Context parentContext, childContext;
    private Lexeme identifier;
    private final List<Param> params;

    public Function() {
        this.params = new ArrayList<>();
    }

    public void addParam(Param param) {
        this.params.add(param);
    }

    public void createChildContext(Context childContext) {
        this.childContext = childContext;
    }

    public void createParentContext(Context parentContext) {
        this.parentContext = parentContext;
    }

    public void createIdentifier(Lexeme identifier) {
        if (!identifier.getType().equals(LexemeTypes.IDENTIFIERS)) {
            throw new SyntaxError("[Error] : " + identifier.getWord() + " no es un identificador valido.");
        }
        this.identifier = identifier;
    }
}
