/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eam.analizador_semantico.Models;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexemeTypes;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.analizador_sintactico.Models.Statements.Structure.SyntacticTypes;

/**
 *
 * @author daryl
 * @date 7/11/2019
 */
public class SemanticAnalyzer {
    
    private Statement root;
    
    public SemanticAnalyzer(Statement root){
        this.root = root;
    }
    
    public Context analyze() {
        Context rootContext = new Context(this.root);
        for (Statement child : this.root.getChilds()) {
            if (child.toString().equals(SyntacticTypes.SIMPLE_ASSIGNMENT_STATMENT)) {
                Variable var = new Variable(rootContext);
                for(Statement grandChild : child.getChilds()){
                    if (grandChild.isLeaf()) {
                        Lexeme lexeme = ((Lexeme)(grandChild));
                        if (lexeme.getType().equals(LexemeTypes.DATA_TYPE)) {
                            var.setDataType(lexeme);
                        }else if(lexeme.getType().equals(LexemeTypes.IDENTIFIERS)){
                            var.setIdentifier(lexeme);
                        }
                    }else{
                        var.setValue(grandChild);
                    }
                }
                rootContext.addVariable(var);
            }
        }
        return rootContext;
    }
}
