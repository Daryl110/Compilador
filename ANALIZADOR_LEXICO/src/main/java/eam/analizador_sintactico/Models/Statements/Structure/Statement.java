/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Structure;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_semantico.Models.Context;
import eam.analizador_semantico.Models.Function;
import eam.analizador_semantico.Models.Parameter;
import eam.analizador_semantico.Models.Variable;
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

    public Context generateContext(Context parent) {
        Context rootContext = new Context(parent, this);
        boolean added = false;
        for (Statement child : this.childs) {
            if (child.toString().equals(SyntacticTypes.SIMPLE_ASSIGNMENT_STATMENT) && this.withContext()) {
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
                        rootContext.addChildContext(grandChild.generateContext(rootContext));
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
            } else if (child.toString().equals(SyntacticTypes.FUNCTION_STATEMENT)) {
                Function function = new Function(rootContext);
                int auxCont = 0;
                for (Statement grandChild : child.childs) {
                    if (grandChild.isLeaf()) {
                        Lexeme lexeme = ((Lexeme) (grandChild));
                        if (lexeme.getType().equals(LexemeTypes.DATA_TYPE) || (lexeme.getType().equals(LexemeTypes.FUNCTIONS)
                                && lexeme.getWord().equals("void"))) {
                            function.setReturnType(lexeme);
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
                                        rootContext.addVariable(var);
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
                rootContext.addChildContext(child.generateContext(rootContext));
            } else if (!child.isLeaf() && child.withContext()) {
                rootContext.addChildContext(child.generateContext(rootContext));
            }
        }
        return rootContext;
    }
}
