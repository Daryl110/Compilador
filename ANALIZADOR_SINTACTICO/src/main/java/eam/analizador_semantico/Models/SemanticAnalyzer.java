/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eam.analizador_semantico.Models;

import eam.analizador_sintactico.Models.Statements.Structure.Statement;

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
        return this.root.generateContext(null, null);
    }
}
