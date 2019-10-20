/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Statements.Structure;

import eam.analizador_sintactico.Models.Lexeme;
import eam.analizador_sintactico.Models.TokensFlow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Daryl Ospina
 */
public abstract class Statement implements TreeNode{
    
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
    
    public boolean addChild(Statement statement){
        return this.childs.add(statement);
    }
    
    public void setParent(Statement root){
        this.root = root;
    }
    
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
}
