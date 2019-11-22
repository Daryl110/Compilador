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
import eam.analizador_sintactico.Models.Statements.Assignment.OthersAssignmentsStatement;
import eam.analizador_sintactico.Models.Statements.Assignment.SimpleAssignmentStatement;
import eam.analizador_sintactico.Models.Statements.Expressions.NumericExpressionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.FunctionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.InvokeFunctionStatement;
import eam.analizador_sintactico.Models.Statements.Functions.ParameterStatement;
import eam.analizador_sintactico.Models.Statements.Iterators.ForEachStatement;
import eam.analizador_sintactico.Models.Statements.Others.ReturnStatement;
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
    
    public void addDefaultFunctions(){
       this.addLogFunction();
       this.addSumFunction();
    }
    
    private void addSumFunction(){
        Statement sum = new FunctionStatement(this.statement);
        Statement forEachStatement = new ForEachStatement(sum);
        Statement assignment1 = new SimpleAssignmentStatement(sum);
        Statement assignment2 = new OthersAssignmentsStatement(forEachStatement);
        ReturnStatement returnStatement = new ReturnStatement(sum);
        Statement paramStatement = new ParameterStatement(sum);
        Statement numericExpression1 = new NumericExpressionStatement(assignment1);
        Statement numericExpression2 = new NumericExpressionStatement(assignment2);
        Statement numericExpression3 = new NumericExpressionStatement(returnStatement);
        
        sum.addChild(new Lexeme(-1, -1, "function", LexemeTypes.FUNCTIONS));
        sum.addChild(new Lexeme(-1, -1, "number", LexemeTypes.DATA_TYPE));
        sum.addChild(new Lexeme(-1, -1, "sum", LexemeTypes.IDENTIFIERS));
        sum.addChild(new Lexeme(-1, -1, "(", LexemeTypes.OPEN_PARENTHESIS));
        
        paramStatement.addChild(new Lexeme(-1, -1, "Array", LexemeTypes.DATA_TYPE));
        paramStatement.addChild(new Lexeme(-1, -1, "array", LexemeTypes.IDENTIFIERS));
        
        sum.addChild(paramStatement);
        sum.addChild(new Lexeme(-1, -1, ")", LexemeTypes.CLOSE_PARENTHESIS));
        sum.addChild(new Lexeme(-1, -1, "{", LexemeTypes.OPEN_BRACES));
        
        assignment1.addChild(new Lexeme(-1, -1, "number", LexemeTypes.DATA_TYPE));
        assignment1.addChild(new Lexeme(-1, -1, "suma", LexemeTypes.IDENTIFIERS));
        assignment1.addChild(new Lexeme(-1, -1, "=", LexemeTypes.ASSIGNMENT_OPERATORS));
        
        numericExpression1.addChild(new Lexeme(-1, -1, "0", LexemeTypes.NUMBERS));
        
        assignment1.addChild(numericExpression1);
        assignment1.addChild(new Lexeme(-1, -1, ";", LexemeTypes.DELIMITERS));
        
        sum.addChild(assignment1);
        
        forEachStatement.addChild(new Lexeme(-1, -1, "for", LexemeTypes.ITERATIVE_CONTROL_STRUCTURE));
        forEachStatement.addChild(new Lexeme(-1, -1, "(", LexemeTypes.OPEN_PARENTHESIS));
        forEachStatement.addChild(new Lexeme(-1, -1, "any", LexemeTypes.DATA_TYPE));
        forEachStatement.addChild(new Lexeme(-1, -1, "item", LexemeTypes.IDENTIFIERS));
        forEachStatement.addChild(new Lexeme(-1, -1, ":", LexemeTypes.OTHERS));
        forEachStatement.addChild(new Lexeme(-1, -1, "array", LexemeTypes.IDENTIFIERS));
        forEachStatement.addChild(new Lexeme(-1, -1, ")", LexemeTypes.CLOSE_PARENTHESIS));
        forEachStatement.addChild(new Lexeme(-1, -1, "{", LexemeTypes.OPEN_BRACES));
        
        assignment2.addChild(new Lexeme(-1, -1, "suma", LexemeTypes.IDENTIFIERS));
        assignment2.addChild(new Lexeme(-1, -1, "+=", LexemeTypes.ASSIGNMENT_OPERATORS));
        assignment2.addChild(new Lexeme(-1, -1, "", LexemeTypes.ASSIGNMENT_OPERATORS));
        
        numericExpression2.addChild(new Lexeme(-1, -1, "item", LexemeTypes.IDENTIFIERS));
        
        assignment2.addChild(numericExpression2);
        assignment2.addChild(new Lexeme(-1, -1, ";", LexemeTypes.DELIMITERS));
        
        forEachStatement.addChild(assignment2);
        forEachStatement.addChild(new Lexeme(-1, -1, "}", LexemeTypes.CLOSE_BRACES));
        
        sum.addChild(forEachStatement);
        
        returnStatement.addChild(new Lexeme(-1, -1, "return", LexemeTypes.FUNCTIONS));
        
        numericExpression3.addChild(new Lexeme(-1, -1, "suma", LexemeTypes.IDENTIFIERS));
        
        returnStatement.addChild(numericExpression3);
        returnStatement.setReturnValue(numericExpression3);
        returnStatement.addChild(new Lexeme(-1, -1, ";", LexemeTypes.DELIMITERS));
        
        sum.addChild(returnStatement);
        sum.addChild(new Lexeme(-1, -1, "}", LexemeTypes.CLOSE_BRACES));
        
        this.statement.getChilds().add(0, sum);
    }
    
    private void addLogFunction(){
        
        Statement log = new FunctionStatement(this.statement);
        Statement paramStatement = new ParameterStatement(log);
        
        log.addChild(new Lexeme(-1, -1, "function", LexemeTypes.FUNCTIONS));
        log.addChild(new Lexeme(-1, -1, "void", LexemeTypes.FUNCTIONS));
        log.addChild(new Lexeme(-1, -1, "log", LexemeTypes.IDENTIFIERS));
        log.addChild(new Lexeme(-1, -1, "(", LexemeTypes.OPEN_PARENTHESIS));
        
        paramStatement.addChild(new Lexeme(-1, -1, "any", LexemeTypes.DATA_TYPE));
        paramStatement.addChild(new Lexeme(-1, -1, "data", LexemeTypes.IDENTIFIERS));
        
        log.addChild(paramStatement);
        log.addChild(new Lexeme(-1, -1, ")", LexemeTypes.CLOSE_PARENTHESIS));
        log.addChild(new Lexeme(-1, -1, "{", LexemeTypes.OPEN_BRACES));
        
        this.statement.getChilds().add(0, log);
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
        if (var.getDataType() != null && var.getValue() != null) {
            this.validateDataTypeVariable(var);
        } else if (var.getDataType() == null) {
            this.validateExiststVariable(var.getIdentifier());
            Variable auxVar = this.getVariable(var.getIdentifier());
            auxVar.setValue(var.getValue());
            this.validateFunctionExists(var.getIdentifier());
            return true;
        } else if (this.getFunction(var.getIdentifier()) != null) {
            throw new SemanticError("ya existe una funcion con el nombre de "
                    + var.getIdentifier().getWord() + "\nen la posicion "
                    + var.getDataType().getRow() + ":"
                    + var.getDataType().getColumn());
        }else if(var.getValue() == null && var.getIdentifier() != null && var.getDataType() != null){
            var.setValue(new Lexeme(-1, -1, "null", LexemeTypes.OTHERS));
            this.validateDataTypeVariable(var);
        }
        
        this.validateFunctionExists(var.getIdentifier());
        return this.variables.add(var);
    }
    
    private void validateFunctionExists(Lexeme identifier){
        Function func = this.getFunction(identifier);
        if (func != null) {
            throw new SemanticError("ya existe una funcion con el nombre de "
                    + func.getIdentifier().getWord() + "\nen la posicion "
                    + func.getReturnType().getRow() + ":"
                    + func.getReturnType().getColumn());
        }
    }

    public boolean addChildContext(Context childContext) {
        return this.childsContexts.add(childContext);
    }

    private void validateDataTypeVariable(Variable var) {
        if (!((((var.getDataType().getType().equals(LexemeTypes.DATA_TYPE)
                && var.getDataType().getWord().equals("number"))
                && (var.getValue().toString().equals(SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT)
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("NaN"))
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("null"))
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
                || var.getValue().toString().equals(SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT)
                || (var.getValue().isLeaf() && ((Lexeme) (var.getValue())).getWord().equals("null"))
                || var.getValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT))))
                || (((var.getDataType().getType().equals(LexemeTypes.DATA_TYPE)
                && var.getDataType().getWord().equals("any"))
                && (var.getValue().toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                || var.getValue().toString().equals(SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT)
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
                    + " no coincide con el valor de una "
                    + var.getValue().toString() + "\nen la posicion "
                    + var.getDataType().getRow() + ":" + var.getDataType().getColumn());
        }
    }

    public boolean validateVariableParentContext(Variable var) {
        boolean setValue = false;
        int positionAux = 0;
        for (Variable auxVar : this.variables) {
            if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())
                    && var.getDataType() != null) {
                throw new SemanticError("ya existe una variable con el nombre de "
                        + var.getIdentifier().getWord() + "\nen la posicion "
                        + var.getDataType().getRow() + ":" + var.getDataType().getColumn());
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
            if (!var.getDataType().getWord().equals("Array")) {
                this.validateDataTypeVariable(var);
            }
        } else {
            throw new SemanticError("La variable con el nombre "
                    + identifier.getWord() + "\nen la posicion "
                    + identifier.getRow() + ":" + identifier.getColumn() + " no existe.");
        }
    }

    private Variable getVariableContextParent(Lexeme identifier) {
        Variable var = new Variable(this), varReturn = null;
        var.setIdentifier(identifier);
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

    private Variable getVariableContext(Lexeme identifier) {
        Variable var = new Variable(this), varReturn = null;
        var.setIdentifier(identifier);
        for (Variable auxVar : this.variables) {
            if (auxVar.getIdentifier().getWord().equals(var.getIdentifier().getWord())) {
                varReturn = auxVar;
                break;
            }
        }
        return varReturn;
    }

    public Variable getVariable(Lexeme identifier) {
        Variable varReturn = this.getVariableContextParent(identifier);
        if (varReturn != null) {
            return varReturn;
        }
        varReturn = this.getVariableContext(identifier);
        return varReturn;
    }

    private Function getFunctionContextParent(Lexeme identifier) {
        Function function = new Function(this), functionReturn = null;
        function.setIdentifier(identifier);
        Context context = this.parent;
        while (context != null) {
            for (Function auxFunction : context.getFunctions()) {
                if (auxFunction.getIdentifier().getWord().equals(function.getIdentifier().getWord())) {
                    functionReturn = auxFunction;
                    break;
                }
            }
            context = context.getParent();
        }

        return functionReturn;
    }

    private Function getFunctionContext(Lexeme identifier) {
        Function function = new Function(this), functionReturn = null;
        function.setIdentifier(identifier);
        for (Function auxFunction : this.functions) {
            if (auxFunction.getIdentifier().getWord().equals(function.getIdentifier().getWord())) {
                functionReturn = auxFunction;
                break;
            }
        }
        return functionReturn;
    }

    public Function getFunction(Lexeme identifier) {
        Function functionReturn = this.getFunctionContextParent(identifier);
        if (functionReturn != null) {
            return functionReturn;
        }
        functionReturn = this.getFunctionContext(identifier);
        return functionReturn;
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

    public List<Function> getFunctions() {
        return this.functions;
    }

    public void addFunction(Function function) {
        if (this.getFunction(function.getIdentifier()) != null) {
            throw new SemanticError("ya existe una funcion con el nombre de "
                    + function.getIdentifier().getWord() + "\nen la posicion "
                    + function.getReturnType().getRow() + ":" + function.getReturnType().getColumn());
        } else if (this.getVariable(function.getIdentifier()) != null) {
            throw new SemanticError("ya existe una variable con el nombre de "
                    + function.getIdentifier().getWord() + "\nen la posicion " + function.getReturnType().getRow()
                    + ":" + function.getReturnType().getColumn());
        }
        this.functions.add(function);
    }
}
