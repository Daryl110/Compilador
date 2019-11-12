/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eam.analizador_semantico.Models;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;

/**
 *
 * @author daryl
 * @date 7/11/2019
 */
public class Variable extends Param {

    private Statement value;
    
    public Variable(Context context){
        super(context);
    }

    public Statement getValue() {
        return value;
    }

    public void setValue(Statement value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{tipoDato : "+this.getDataType().getWord()+", identificador : "+this.getIdentifier().getWord()+", value : "+this.value.toString()+"}";
    }
}
