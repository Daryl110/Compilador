/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eam.analizador_semantico.Models;

import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_semantico.Exceptions.SemanticError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author daryl
 * @date 7/11/2019
 */
public class Context {
    
    private Statement parent;
    private List<Variable> variables;
    private List<Function> functions;
    private List<Context> childsContexts;
    
    public Context(Statement parent){
        this.parent = parent;
        this.variables = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.childsContexts = new ArrayList<>();
    }
    
    public boolean addVariable(Variable var) {
        boolean setValue = false;
        int positionAux = 0;
        for (Variable auxVar : this.variables) {
            if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())
                    && var.getDataType() != null) {
                throw new SemanticError("ya existe una variable con el nombre de "+var.getIdentifier().getWord());
            }else if(auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())
                    && var.getDataType() == null){
                setValue = true;
                break;
            }
            positionAux++;
        }
        if (setValue) {
            this.variables.get(positionAux).setValue(var.getValue());
            return true;
        }
        return this.variables.add(var);
    }
    
    public String getVariables(){
        return Arrays.toString(this.variables.toArray());
    }
}
