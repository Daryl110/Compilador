/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Structure;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_semantico.Exceptions.SemanticError;
import eam.analizador_semantico.Models.Context;
import eam.analizador_semantico.Models.Function;
import eam.analizador_semantico.Models.Parameter;
import eam.analizador_semantico.Models.Variable;
import eam.analizador_sintactico.Models.Exceptions.SyntaxError;
import eam.analizador_sintactico.Models.Statements.Assignment.SimpleAssignmentStatement;
import eam.analizador_sintactico.Models.Statements.Functions.InvokeFunctionStatement;
import eam.analizador_sintactico.Models.Statements.Others.ReturnStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Daryl Ospina
 */
public abstract class Statement implements TreeNode {

    protected int positionBack = -1;

    public Statement(Statement root) {
        this.root = root;
        this.childs = new ArrayList<>();
    }

    public Statement(Statement root, int positionBack) {
        this.root = root;
        this.childs = new ArrayList<>();
        this.positionBack = positionBack;
    }

    /**
     * Sentencia dentro de la que se encuentra esta sentencia.
     */
    protected Statement root;

    /**
     * Hijos de la raiz de derivacion.
     */
    protected List<Statement> childs;

    @Override
    public abstract String toString();

    public abstract String parse();

    public abstract Statement analyze(TokensFlow tokensFlow, Lexeme lexeme);

    public boolean addChild(Statement statement) {
        return this.childs.add(statement);
    }

    public void setParent(Statement root) {
        this.root = root;
    }

    public abstract boolean withContext();

    @Override
    public TreeNode getChildAt(int childIndex) {
        return this.childs.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return this.childs.size();
    }

    @Override
    public TreeNode getParent() {
        return root;
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.childs.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        if (this.childs == null) {
            return true;
        }
        return this.childs.isEmpty();
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(this.childs);
    }

    /**
     * @param childs the hijos to set
     */
    public void setChilds(List<Statement> childs) {
        this.childs = childs;
    }

    public List<Statement> getChilds() {
        return childs;
    }

    public Context generateContext(Context parent, Context current) {
        Context rootContext = new Context(parent, this);
        if (parent == null) {
            rootContext.addDefaultFunctions();
        }
        if (current != null) {
            rootContext = current;
        }
        boolean added = false;
        for (Statement child : this.childs) {
            if (child.toString().equals(SyntacticTypes.SIMPLE_ASSIGNMENT_STATMENT) && this.withContext()
                    && !((SimpleAssignmentStatement) (child)).isArrayAssigment()) {
                this.validateCreateVariable(child, rootContext, added);
            } else if (child.toString().equals(SyntacticTypes.INCREMENTAL_DECREMENTAL_OPERATION_STATEMENT)) {
                rootContext.validateExiststVariable((Lexeme) child.getChildAt(0));
            } else if (child.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)) {
                this.validateInvokeFunction(child, rootContext);
            } else if (child.toString().equals(SyntacticTypes.FUNCTION_STATEMENT)) {
                this.validateCreateFunction(child, rootContext);
            } else if (child.toString().equals(SyntacticTypes.FOR_EACH_STATEMENT)) {
                this.validateCreateVariableInForEach(child, rootContext);
            } else if (!child.isLeaf() && child.withContext()) {
                rootContext.addChildContext(child.generateContext(rootContext, null));
            } else {
                this.validateVariables(child, rootContext);
            }
        }
        return rootContext;
    }

    private void validateCreateVariable(Statement child, Context rootContext, boolean added) {
        Variable var = new Variable(rootContext);
        for (Statement grandChild : child.childs) {
            if (grandChild.isLeaf()) {
                Lexeme lexeme = ((Lexeme) (grandChild));
                if (lexeme.getType().equals(LexemeTypes.DATA_TYPE)) {
                    var.setDataType(lexeme);
                } else if (lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
                    var.setIdentifier(lexeme);
                }
            } else if (grandChild.withContext()) {
                if (var.getIdentifier() != null) {
                    if (grandChild.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)) {
                        this.validateDataTypeStatement(grandChild, var.getDataType(),
                                child, rootContext, "El tipo de retorno de la funcion "
                                + ((Lexeme) grandChild.getChildAt(0)).getWord() + " el cual es "
                                + rootContext.getFunction((Lexeme) grandChild.getChildAt(0)).getReturnType().getWord()
                                + " no coincide con el tipo de dato "
                                + var.getDataType().getWord());
                    } else if (grandChild.toString().equals(SyntacticTypes.ARROW_FUNCTION_STATEMENT)) {
                        this.validateCreateArrowFunction(grandChild, rootContext, var.getIdentifier());
                        return;
                    }
                    rootContext.addVariable(var);
                    added = true;
                }
                this.validateVariables(grandChild, rootContext);
                var.setValue(grandChild);
                rootContext.addChildContext(grandChild.generateContext(rootContext, null));
            } else {
                if (grandChild.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)) {
                    this.validateDataTypeStatement(grandChild, var.getDataType(),
                            child, rootContext, "El tipo de retorno de la funcion "
                            + grandChild.getChildAt(0) + " el cual es "
                            + rootContext.getFunction((Lexeme) grandChild.getChildAt(0)).getReturnType().getWord()
                            + " no coincide con el tipo de dato "
                            + var.getDataType().getWord());
                } else if (grandChild.toString().equals(SyntacticTypes.ARROW_FUNCTION_STATEMENT)) {
                    this.validateCreateArrowFunction(grandChild, rootContext, var.getIdentifier());
                    return;
                }
                this.validateVariables(grandChild, rootContext);
                var.setValue(grandChild);
            }
        }
        if (var.getIdentifier() != null && !added) {
            rootContext.addVariable(var);
        }
    }

    private void validateCreateArrowFunction(Statement child, Context rootContext, Lexeme identifier) {
        Context contextFunction = new Context(rootContext, child);
        Function function = new Function(contextFunction);
        for (Statement grandChild : child.childs) {
            if (grandChild.isLeaf()) {
                Lexeme lexeme = ((Lexeme) (grandChild));
                if (lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                    for (int i = 0; i < child.getChildCount(); i++) {
                        Parameter param = new Parameter(rootContext);
                        Variable var = new Variable(rootContext);
                        if (!child.getChildAt(i).isLeaf()) {
                            for (Statement greatGrandSon : ((Statement) child.getChildAt(i)).childs) {
                                if (greatGrandSon.isLeaf()) {
                                    Lexeme lex = ((Lexeme) greatGrandSon);
                                    if (lex.getType().equals(LexemeTypes.DATA_TYPE)) {
                                        param.setDataType(lex);
                                        var.setDataType(lex);
                                    } else if (lex.getType().equals(LexemeTypes.IDENTIFIERS)) {
                                        param.setIdentifier(lex);
                                        var.setIdentifier(lex);
                                    }
                                }
                            }
                            if (var.getIdentifier() != null) {
                                contextFunction.addVariable(var);
                            }
                            function.addParam(param);
                        } else if (child.getChildAt(i).isLeaf()
                                && ((Lexeme) child.getChildAt(i)).getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                            break;
                        }
                    }
                    break;
                }
            }
        }
        function.setIdentifier(identifier);
        function.setReturnType(new Lexeme(0, 0, "any", LexemeTypes.DATA_TYPE));
        rootContext.addFunction(function);
        rootContext.addChildContext(child.generateContext(rootContext, contextFunction));
    }

    private void validateCreateFunction(Statement child, Context rootContext) {
        Context contextFunction = new Context(rootContext, child);
        Function function = new Function(contextFunction);
        int auxCont = 0;
        for (Statement grandChild : child.childs) {
            if (grandChild.isLeaf()) {
                Lexeme lexeme = ((Lexeme) (grandChild));
                if (lexeme.getType().equals(LexemeTypes.DATA_TYPE)
                        || (lexeme.getType().equals(LexemeTypes.FUNCTIONS)
                        && lexeme.getWord().equals("void"))) {
                    function.setReturnType(lexeme);
                    ReturnStatement returnStatement = null;
                    for (Statement grandChild1 : child.childs) {
                        if (grandChild1.toString().equals(SyntacticTypes.RETURN_STATEMENT)) {
                            returnStatement = (ReturnStatement) grandChild1;
                            break;
                        }
                    }

                    if (returnStatement != null && !lexeme.getWord().equals("void")) {
                        this.validateDataTypeStatement(returnStatement.getReturnValue(), lexeme, child, rootContext,
                                "el tipo de retorno "
                                + lexeme.getWord() + " de la funcion "
                                + ((Lexeme) child.childs.get(2)).getWord()
                                + " no coincide con el valor de una "
                                + returnStatement.getReturnValue().toString() + "\nen la posicion "
                                + lexeme.getRow() + ":" + lexeme.getColumn());
                    } else if (returnStatement != null && lexeme.getWord().equals("void")) {
                        throw new SemanticError("La funcion con el nombre " + ((Lexeme) child.childs.get(2)).getWord() + " tiene un tipo de retorno void y tiene una sentencia de retorno "
                                + "\nen la posicion " + ((Lexeme) returnStatement.getReturnValue().getChildAt(0)).getRow() + ":" + ((Lexeme) returnStatement.getReturnValue().getChildAt(0)).getColumn());
                    } else if (returnStatement == null && !lexeme.getWord().equals("void")) {
                        throw new SemanticError("La funcion con el nombre " + ((Lexeme) child.childs.get(2)).getWord() + " no tiene una sentencia de retorno "
                                + "\nen la posicion " + ((Lexeme) child.childs.get(2)).getRow() + ":" + ((Lexeme) child.childs.get(2)).getColumn());
                    }
                } else if (lexeme.getType().equals(LexemeTypes.IDENTIFIERS)) {
                    function.setIdentifier(lexeme);
                } else if (lexeme.getType().equals(LexemeTypes.OPEN_PARENTHESIS)) {
                    for (int i = auxCont + 1; i < child.getChildCount(); i++) {
                        Parameter param = new Parameter(rootContext);
                        Variable var = new Variable(rootContext);
                        if (!child.getChildAt(i).isLeaf()) {
                            for (Statement greatGrandSon : ((Statement) child.getChildAt(i)).childs) {
                                if (greatGrandSon.isLeaf()) {
                                    Lexeme lex = ((Lexeme) greatGrandSon);
                                    if (lex.getType().equals(LexemeTypes.DATA_TYPE)) {
                                        param.setDataType(lex);
                                        var.setDataType(lex);
                                    } else if (lex.getType().equals(LexemeTypes.IDENTIFIERS)) {
                                        param.setIdentifier(lex);
                                        var.setIdentifier(lex);
                                    }
                                }
                            }
                            if (var.getIdentifier() != null) {
                                contextFunction.addVariable(var);
                            }
                            function.addParam(param);
                        } else if (child.getChildAt(i).isLeaf()
                                && ((Lexeme) child.getChildAt(i)).getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                            break;
                        }
                    }
                    break;
                }
            }
            auxCont++;
            if (auxCont >= child.getChildCount()) {
                break;
            }
        }
        rootContext.addFunction(function);
        rootContext.addChildContext(child.generateContext(rootContext, contextFunction));
    }

    private void validateCreateVariableInForEach(Statement child, Context rootContext) {
        Context contextForEach = new Context(rootContext, child);
        Variable var = new Variable(contextForEach);
        try {
            contextForEach.validateExiststVariable((Lexeme) child.getChildAt(3));
            throw new SyntaxError("Error al crear la variable "
                    + ((Lexeme) child.getChildAt(3)).getWord()
                    + " dentro del foreach en la posicion "
                    + ((Lexeme) child.getChildAt(0)).getRow()
                    + ":"
                    + ((Lexeme) child.getChildAt(0)).getColumn());
        } catch (SemanticError e) {
            var.setDataType((Lexeme) child.getChildAt(2));
            var.setIdentifier((Lexeme) child.getChildAt(3));
        }
        contextForEach.addVariable(var);
        rootContext.addChildContext(child.generateContext(rootContext, contextForEach));
    }

    private void validateVariables(Statement child, Context rootContext) {
        if (child.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)) {
            this.validateInvokeFunction(child, rootContext);
        }
        child.childs.forEach((grandchild) -> {
            if (grandchild.isLeaf()) {
                if (((Lexeme) grandchild).getType().equals(LexemeTypes.IDENTIFIERS)) {
                    if (rootContext.getVariable(((Lexeme) grandchild)) == null && rootContext.getFunction(((Lexeme) grandchild)) == null) {
                        throw new SemanticError(((Lexeme) grandchild).getWord()
                                + " no existe y se esta llamando en la posicion "
                                + ((Lexeme) grandchild).getRow() + ":" + ((Lexeme) grandchild).getColumn());
                    }
                }
            } else {
                this.validateVariables(grandchild, rootContext);
            }
        });
    }

    private void validateDataTypeStatement(Statement statement, Lexeme datatype, Statement child, Context rootContext, String mensaje) {
        try {
            if (!((datatype.getWord().equals("number")
                    && (statement.toString().equals(SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT)
                    || (statement.isLeaf() && ((Lexeme) statement).getWord().equals("NaN"))
                    || (statement.isLeaf() && ((Lexeme) statement).getWord().equals("null"))
                    || (statement.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                    && (rootContext.getFunction((Lexeme) statement.getChildAt(0))
                            .getReturnType().getWord().equals("number")))))
                    || (datatype.getWord().equals("Array")
                    && (statement.toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT)
                    || (statement.isLeaf() && ((Lexeme) statement).getWord().equals("null"))
                    || (statement.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                    && (rootContext.getFunction((Lexeme) statement.getChildAt(0))
                            .getReturnType().getWord().equals("Array")))))
                    || (datatype.getWord().equals("string")
                    && (statement.toString().equals(SyntacticTypes.STRING_EXPRESSION_STATEMENT)
                    || (statement.isLeaf() && ((Lexeme) statement).getWord().equals("null"))
                    || (statement.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                    && (rootContext.getFunction((Lexeme) statement.getChildAt(0))
                            .getReturnType().getWord().equals("string")))))
                    || (datatype.getWord().equals("boolean")
                    && (statement.toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                    || statement.toString().equals(SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT)
                    || (statement.isLeaf() && ((Lexeme) statement).getWord().equals("null"))
                    || (statement.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                    && (rootContext.getFunction((Lexeme) statement.getChildAt(0))
                            .getReturnType().getWord().equals("boolean")))))
                    || (datatype.getWord().equals("any")
                    && (statement.toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                    || statement.toString().equals(SyntacticTypes.RELATIONAL_EXPRESSION_STATEMENT)
                    || statement.toString().equals(SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT)
                    || statement.toString().equals(SyntacticTypes.STRING_EXPRESSION_STATEMENT)
                    || statement.toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT)
                    || (statement.isLeaf() && ((Lexeme) statement).getWord().equals("null"))
                    || (statement.isLeaf() && ((Lexeme) statement).getWord().equals("NaN"))
                    || (statement.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                    && !rootContext.getFunction((Lexeme) statement.getChildAt(0)).getReturnType().getWord().equals("void")))))) {

                throw new SemanticError(mensaje);
            }
        } catch (NullPointerException e) {
            throw new SemanticError("La funcion con el nombre " + ((Lexeme) statement.getChildAt(0)).getWord() + " no existe "
                    + "\nen la posicion " + ((Lexeme) statement.getChildAt(0)).getRow() + ":" + ((Lexeme) statement.getChildAt(0)).getColumn());
        }
    }

    private void validateInvokeFunction(Statement child, Context rootContext) {
        InvokeFunctionStatement invokeFunctionStatement = ((InvokeFunctionStatement) (child));
        if (invokeFunctionStatement.getChildAt(0).isLeaf()) {
            Lexeme identifier = (Lexeme) invokeFunctionStatement.getChildAt(0);
            if (identifier.getType().equals(LexemeTypes.IDENTIFIERS)) {
                Function func = rootContext.getFunction(identifier);
                if (func != null) {
                    int aux = 0;
                    for (int i = 2; i < invokeFunctionStatement.childs.size(); i++) {
                        if (invokeFunctionStatement.getChildAt(i).isLeaf()) {
                            Lexeme varIdentifier = (Lexeme) invokeFunctionStatement.getChildAt(i);
                            if (varIdentifier.getType().equals(LexemeTypes.CLOSE_PARENTHESIS)) {
                                break;
                            }
                        } else {
                            Statement varIdentifier = (Statement) invokeFunctionStatement.getChildAt(i);
                            if (func.getParameters().isEmpty()) {
                                throw new SemanticError("la funcion " + func.getIdentifier().getWord()
                                        + " no tiene parametros pero se le estan enviando."
                                        + "\nen la posicion " + func.getIdentifier().getRow() + ":" + func.getIdentifier().getColumn());
                            }
                            if (varIdentifier.getChildCount() == 1 && varIdentifier.getChildAt(0).isLeaf()) {
                                if (((Lexeme)varIdentifier.getChildAt(0)).getType().equals(LexemeTypes.IDENTIFIERS)) {
                                    Variable var = rootContext.getVariable(((Lexeme)varIdentifier.getChildAt(0)));
                                    this.validateDataTypeStatement(var.getValue(), func.getParameters().get(aux).getDataType(), child, rootContext,
                                    "El parametro numero " + (aux + 1) + " requerido para la funcion " + func.getIdentifier().getWord()
                                    + " es de tipo " + func.getParameters().get(aux).getDataType().getWord()
                                    + " y el valor que se le esta enviando es de tipo "
                                    + var.getDataType().getWord()
                                    + " en la posicion " + identifier.getRow() + ":" + identifier.getColumn());
                                    aux++;
                                    continue;
                                }
                            }
                            this.validateDataTypeStatement(varIdentifier, func.getParameters().get(aux).getDataType(), child, rootContext,
                                    "El parametro numero " + (aux + 1) + " requerido para la funcion " + func.getIdentifier().getWord()
                                    + " es de tipo " + func.getParameters().get(aux).getDataType().getWord()
                                    + " y el valor que se le esta enviando es de tipo "
                                    + varIdentifier.toString()
                                    + " en la posicion " + identifier.getRow() + ":" + identifier.getColumn());
                            aux++;
                        }
                    }

                    if (func.getParameters().size() < aux) {
                        throw new SemanticError("la funcion " + func.getIdentifier().getWord()
                                + " tiene menos parametros."
                                + "\nen la posicion " + func.getIdentifier().getRow() + ":" + func.getIdentifier().getColumn());
                    } else if (aux < func.getParameters().size()) {
                        throw new SemanticError("la funcion " + func.getIdentifier().getWord()
                                + " tiene mas parametros."
                                + "\nen la posicion " + func.getIdentifier().getRow() + ":" + func.getIdentifier().getColumn());
                    }
                } else {
                    throw new SemanticError("La funcion con el nombre " + identifier.getWord() + " no existe "
                            + "\nen la posicion " + identifier.getRow() + ":" + identifier.getColumn());
                }
            }
        }
    }
}
