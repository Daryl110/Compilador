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

    private Statement statement;
    private Context parent;
    private List<Variable> variables;
    private List<Function> functions;
    private List<Context> childsContexts;

    public Context(Statement statement) {
        this.statement = statement;
        this.variables = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.childsContexts = new ArrayList<>();
        this.parent = null;
    }

    public Context(Context parent, Statement statement) {
        this.statement = statement;
        this.variables = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.childsContexts = new ArrayList<>();
        this.parent = parent;
    }

    public Context getParent() {
        return parent;
    }

    public void setParent(Context parent) {
        this.parent = parent;
    }

    public boolean addVariable(Variable var) {
        this.validateVariableContext(var);
        Context aux = this.getParent();
        while (aux != null) {
            if(aux.validateVariableContext(var))return true;
            aux = aux.getParent();
        }
        return this.variables.add(var);
    }

    public boolean addChildContext(Context childContext) {
        return this.childsContexts.add(childContext);
    }

    public boolean validateVariableContext(Variable var) {
        boolean setValue = false;
        int positionAux = 0;
        for (Variable auxVar : this.variables) {
            if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())
                    && var.getDataType() != null) {
                throw new SemanticError("ya existe una variable con el nombre de " + var.getIdentifier().getWord());
            } else if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())
                    && var.getDataType() == null) {
                setValue = true;
                break;
            }
            positionAux++;
        }
        if (setValue) {
            this.variables.get(positionAux).setValue(var.getValue());
        }
        return setValue;
    }

    public String getVariables() {
        String concat = "";
        return "{\""+this.statement.toString() + "\" : {\"variables\" : " + Arrays
                .toString(
                        this.variables.toArray()
                ) + ", \"contextosHijos\" : ["
                + this.childsContexts
                        .stream()
                        .map(
                                (context) -> context.getVariables()
                        )
                        .reduce(
                                concat,
                                String::concat
                        ) + "]}}";
    }
}
