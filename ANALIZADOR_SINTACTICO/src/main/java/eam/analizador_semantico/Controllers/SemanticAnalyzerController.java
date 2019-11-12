/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eam.analizador_semantico.Controllers;

import eam.analizador_semantico.Models.Context;
import eam.analizador_semantico.Models.SemanticAnalyzer;
import eam.analizador_semantico.Models.Variable;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import java.util.function.Consumer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daryl
 * @date 12/11/2019
 */
public class SemanticAnalyzerController {

    private final SemanticAnalyzer semanticAnalyzer;

    public SemanticAnalyzerController(Statement root) {
        this.semanticAnalyzer = new SemanticAnalyzer(root);
    }
    
    public DefaultTableModel analyze() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("TIPO DE DATO");
        model.addColumn("IDENTIFICADOR");
        model.addColumn("VALOR");
        model.addColumn("CONTEXTO");
        model.addColumn("CONTEXTO PADRE");
        
        Context rootContext = this.semanticAnalyzer.analyze();
        generateRowsModel(rootContext, model);
        
        return model;
    }
    
    private void generateRowsModel(Context context, DefaultTableModel model){
        context.getVariables().forEach((Variable var) -> {
            model.addRow(new Object[]{
                var.getDataType().getWord(),
                var.getIdentifier().getWord(),
                var.getValue().toString(),
                context.getStatement().toString(),
                context.getParent() == null ? "null" : context.getParent().getStatement().toString()
            });
        });
        context.getChildsContexts().forEach((childContext) -> {
            generateRowsModel(childContext, model);
        });
    }
}
