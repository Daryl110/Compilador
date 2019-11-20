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
        if (current != null) {
            rootContext = current;
        }
        boolean added = false;
        for (Statement child : this.childs) {
            if (child.toString().equals(SyntacticTypes.SIMPLE_ASSIGNMENT_STATMENT) && this.withContext()
                    && !((SimpleAssignmentStatement) (child)).isArrayAssigment()) {
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
                        var.setValue(grandChild);
                        if (var.getIdentifier() != null) {
                            rootContext.addVariable(var);
                            added = true;
                        }
                        rootContext.addChildContext(grandChild.generateContext(rootContext, null));
                    } else {
                        var.setValue(grandChild);
                    }
                }
                if (var.getIdentifier() != null && !added) {
                    rootContext.addVariable(var);
                } else {
                    added = false;
                }
            } else if (child.toString().equals(SyntacticTypes.INCREMENTAL_DECREMENTAL_OPERATION_STATEMENT)) {
                rootContext.validateExiststVariable((Lexeme) child.getChildAt(0));
            } else if (child.toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)) {
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
                                    Lexeme varIdentifier = (Lexeme) invokeFunctionStatement.getChildAt(i).getChildAt(0);
                                    if (func.getParameters().isEmpty()) {
                                        throw new SemanticError("la funcion " + func.getIdentifier().getWord()
                                                + " no tiene parametros pero se le estan enviando."
                                                + "\nen la posicion " + func.getIdentifier().getRow() + ":" + func.getIdentifier().getColumn());
                                    }
                                    Variable var = rootContext.getVariable(varIdentifier);
                                    if (var.getDataType().getWord().equals(func.getParameters().get(aux).getDataType().getWord())
                                            || func.getParameters().get(aux).getDataType().getWord().equals("any")) {
                                        aux++;
                                    } else {
                                        throw new SemanticError("el parametro " + var.getIdentifier().getWord() + " es de tipo " + var.getDataType().getWord()
                                                + " y el parametro requerido es de tipo " + func.getParameters().get(aux).getDataType().getWord()
                                                + "\nen la invocacion de la funcion " + func.getIdentifier().getWord()
                                                + "\nen la posicion " + func.getIdentifier().getRow() + ":" + func.getIdentifier().getColumn());
                                    }
                                }
                            }

                            if (func.getParameters().size() - 1 < aux) {
                                throw new SemanticError("la funcion " + func.getIdentifier().getWord()
                                        + " tiene menos parametros."
                                        + "\nen la posicion " + func.getIdentifier().getRow() + ":" + func.getIdentifier().getColumn());
                            } else if (aux < func.getParameters().size() - 1) {
                                if (func.getParameters().size() - 1 < aux) {
                                    throw new SemanticError("la funcion " + func.getIdentifier().getWord()
                                            + " tiene mas parametros."
                                            + "\nen la posicion " + func.getIdentifier().getRow() + ":" + func.getIdentifier().getColumn());
                                }
                            }
                        } else {
                            throw new SemanticError("La funcion con el nombre " + identifier.getWord() + " no existe "
                                    + "\nen la posicion " + identifier.getRow() + ":" + identifier.getColumn());
                        }
                    }
                }
            } else if (child.toString().equals(SyntacticTypes.FUNCTION_STATEMENT)) {
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
                                }
                            }

                            if (returnStatement != null && !lexeme.getWord().equals("void")) {
                                try {
                                    if (!((lexeme.getWord().equals("number")
                                            && (returnStatement.getReturnValue().toString().equals(SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT)
                                            || (returnStatement.getReturnValue().isLeaf() && ((Lexeme) returnStatement.getReturnValue()).getWord().equals("NaN"))
                                            || (returnStatement.getReturnValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                                            && (rootContext.getFunction((Lexeme) returnStatement.getReturnValue().getChildAt(0))
                                                    .getReturnType().getWord().equals("number")))))
                                            || (lexeme.getWord().equals("Array")
                                            && (returnStatement.getReturnValue().toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT)
                                            || (returnStatement.getReturnValue().isLeaf() && ((Lexeme) returnStatement.getReturnValue()).getWord().equals("null"))
                                            || (returnStatement.getReturnValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                                            && (rootContext.getFunction((Lexeme) returnStatement.getReturnValue().getChildAt(0))
                                                    .getReturnType().getWord().equals("Array")))))
                                            || (lexeme.getWord().equals("string")
                                            && (returnStatement.getReturnValue().toString().equals(SyntacticTypes.STRING_EXPRESSION_STATEMENT)
                                            || (returnStatement.getReturnValue().isLeaf() && ((Lexeme) returnStatement.getReturnValue()).getWord().equals("null"))
                                            || (returnStatement.getReturnValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                                            && (rootContext.getFunction((Lexeme) returnStatement.getReturnValue().getChildAt(0))
                                                    .getReturnType().getWord().equals("string")))))
                                            || (lexeme.getWord().equals("boolean")
                                            && (returnStatement.getReturnValue().toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                                            || (returnStatement.getReturnValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                                            && (rootContext.getFunction((Lexeme) returnStatement.getReturnValue().getChildAt(0))
                                                    .getReturnType().getWord().equals("boolean")))))
                                            || (lexeme.getWord().equals("any")
                                            && (returnStatement.getReturnValue().toString().equals(SyntacticTypes.LOGICAL_EXPRESSION_STATEMENT)
                                            || returnStatement.getReturnValue().toString().equals(SyntacticTypes.NUMERIC_EXPRESSION_STATEMENT)
                                            || returnStatement.getReturnValue().toString().equals(SyntacticTypes.STRING_EXPRESSION_STATEMENT)
                                            || returnStatement.getReturnValue().toString().equals(SyntacticTypes.ARRAY_EXPRESSION_STATEMENT)
                                            || (returnStatement.getReturnValue().isLeaf() && ((Lexeme) returnStatement.getReturnValue()).getWord().equals("null"))
                                            || (returnStatement.getReturnValue().isLeaf() && ((Lexeme) returnStatement.getReturnValue()).getWord().equals("NaN"))
                                            || (returnStatement.getReturnValue().toString().equals(SyntacticTypes.INVOKE_FUNCTION_STATMENT)
                                            && !rootContext.getFunction((Lexeme) returnStatement.getReturnValue().getChildAt(0)).getReturnType().getWord().equals("void")))))) {

                                        throw new SemanticError("el tipo de retorno "
                                                + "" + lexeme.getWord() + " de la funcion "
                                                + "" + ((Lexeme) child.childs.get(2)).getWord() + ""
                                                + " no coincide con el valor de una "
                                                + returnStatement.getReturnValue().toString() + "\nen la posicion "
                                                + lexeme.getRow() + ":" + lexeme.getColumn());
                                    }
                                } catch (NullPointerException e) {
                                    throw new SemanticError("La funcion con el nombre " + ((Lexeme) returnStatement.getReturnValue().getChildAt(0)).getWord() + " no existe "
                                            + "\nen la posicion " + ((Lexeme) returnStatement.getReturnValue().getChildAt(0)).getRow() + ":" + ((Lexeme) returnStatement.getReturnValue().getChildAt(0)).getColumn());
                                }
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
                        }
                    }
                    auxCont++;
                    if (auxCont >= child.getChildCount()) {
                        break;
                    }
                }
                rootContext.addFunction(function);
                rootContext.addChildContext(child.generateContext(rootContext, contextFunction));
            } else if (!child.isLeaf() && child.withContext()) {
                rootContext.addChildContext(child.generateContext(rootContext, null));
            } else {
                this.validateVariables(child, rootContext);
            }
        }
        return rootContext;
    }

    private void validateVariables(Statement child, Context rootContext) {
        for (Statement grandchild : child.childs) {
            if (grandchild.isLeaf()) {
                if (((Lexeme) grandchild).getType().equals(LexemeTypes.IDENTIFIERS)) {
                    if (rootContext.getVariable(((Lexeme) grandchild)) == null) {
                        throw new SemanticError("la variable " + ((Lexeme) grandchild).getWord()
                                + " no existe y se esta llamando en la posicion "
                                + ((Lexeme) grandchild).getRow() + ":" + ((Lexeme) grandchild).getColumn());
                    }
                }
            } else {
                this.validateVariables(grandchild, rootContext);
            }
        }
    }
}
