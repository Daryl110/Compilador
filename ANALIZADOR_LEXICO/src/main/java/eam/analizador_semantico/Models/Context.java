/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_semantico.Models;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_semantico.Exceptions.SemanticError;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;
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

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public boolean addVariable(Variable var) {
        this.validateVariableParentContext(var);
        Context aux = this.getParent();
        while (aux != null) {
            if (aux.validateVariableParentContext(var)) {
                return true;
            }
            aux = aux.getParent();
        }
        if (var.getDataType() != null) {
            this.validateDataTypeVariable(var);
        } else {
            this.validateExiststVariable(var.getIdentifier());
        }
        return this.variables.add(var);
    }

    public boolean addChildContext(Context childContext) {
        return this.childsContexts.add(childContext);
    }

    private void validateDataTypeVariable(Variable var) {
        if (!((((var.getDataType().getType().equals(LexemeTypes.DATA_TYPE)
                && var.getDataType().getWord().equals("number"))
                && (var.getValue().toString().equals(SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT)
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("NaN"))
                || var.getValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)))
                || ((var.getDataType().getType().equals(LexemeTypes.DATA_TYPE)
                && var.getDataType().getWord().equals("Array"))
                && (var.getValue().toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT)
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("null"))
                || var.getValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)))
                || ((var.getDataType().getType().equals(LexemeTypes.DATA_TYPE)
                && var.getDataType().getWord().equals("string"))
                && (var.getValue().toString().equals(SyntacticTypes.STRING_EXPRESSION_STATEMENT)
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("null"))
                || var.getValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)))
                || ((var.getDataType().getType().equals(LexemeTypes.DATA_TYPE)
                && var.getDataType().getWord().equals("boolean"))
                && (var.getValue().toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                || var.getValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT))))
                || (((var.getDataType().getType().equals(LexemeTypes.DATA_TYPE)
                && var.getDataType().getWord().equals("any"))
                && (var.getValue().toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                || var.getValue().toString().equals(SyntacticTypes.ARROW_FUNCTION_STATEMENT)
                || var.getValue().toString().equals(SyntacticTypes.STRING_EXPRESSION_STATEMENT)
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("null"))
                || var.getValue().toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT)
                || var.getValue().toString().equals(SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT)
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("NaN"))
                || var.getValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)))))) {
            throw new SemanticError("el tipo de dato "
                    + "" + var.getDataType().getWord() + " de la variable "
                    + "" + var.getIdentifier().getWord() + ""
                    + " no coincide con el valor de una " + var.getValue().toString() + "\nen la posicion " + var.getDataType().getRow() + ":" + var.getDataType().getColumn());
        }
    }

    public boolean validateVariableParentContext(Variable var) {
        boolean setValue = false;
        int positionAux = 0;
        for (Variable auxVar : this.variables) {
            if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())
                    && var.getDataType() != null) {
                throw new SemanticError("ya existe una variable con el nombre de " + var.getIdentifier().getWord() + "\nen la posicion " + var.getDataType().getRow() + ":" + var.getDataType().getColumn());
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

    public void validateExiststVariable(Lexeme identifier) {
        Variable var = this.getVariable(identifier);
        if (var != null) {
            if (!var.getDataType().getWord().equals("number")) {
                throw new SemanticError("no se puede incrementar o decrementar una variable de tipo " + var.getDataType().getWord());
            }
        } else {
            throw new SemanticError("La variable con el nombre " + (identifier).getWord() + "\nen la posicion " + var.getDataType().getRow() + ":" + var.getDataType().getColumn()+" no existe.");
        }
    }

    public Variable getVariable(Lexeme identifier) {
        Variable var = new Variable(this), varReturn = null;
        var.setIdentifier(identifier);
        for (Variable auxVar : this.variables) {
            if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())) {
                varReturn = auxVar;
                break;
            }
        }
        for (Context context : this.childsContexts) {
            for (Variable auxVar : context.getVariables()) {
                if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())) {
                    varReturn = auxVar;
                    break;
                }
            }
        }
        Context context = this.parent;
        while (context != null) {
            for (Variable auxVar : context.getVariables()) {
                if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())) {
                    varReturn = auxVar;
                    break;
                }
            }
            context = context.getParent();
        }

        return varReturn;
    }

    public String getVariablesJSON() {
        String concat = "";
        for (int i = 0; i < this.childsContexts.size(); i++) {
            concat += this.childsContexts.get(i).getVariablesJSON();
            if (i != this.childsContexts.size() - 1) {
                concat += ",";
            }
        }
        return "{\"" + this.statement.toString() + "\" : {\"variables\" : " + Arrays
                .toString(
                        this.variables.toArray()
                ) + ", \"contextosHijos\" : ["
                + concat + "]}}";
    }

    public List<Variable> getVariables() {
        return this.variables;
    }

    public List<Context> getChildsContexts() {
        return this.childsContexts;
    }
}
