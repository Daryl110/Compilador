/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_semantico.Controllers;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_semantico.Models.Context;
import eam.analizador_semantico.Models.Function;
import eam.analizador_semantico.Models.SemanticAnalyzer;
import eam.analizador_semantico.Models.Variable;
import eam.analizador_sintactico.Models.Statements.Structure.Statement;
import eam.gui_compilador.util.Tools;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daryl
 * @date 12/11/2019
 */
public class SemanticAnalyzerController {

    private final SemanticAnalyzer semanticAnalyzer;
    private Context rootContext;

    public SemanticAnalyzerController(Statement root) {
        this.semanticAnalyzer = new SemanticAnalyzer(root);
    }
    
    private String parsear(){
        return this.semanticAnalyzer.getRoot().parse();
    }
    
    public void analyze(){
        this.rootContext = this.semanticAnalyzer.analyze();
    }

    public DefaultTableModel getVariables() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("TIPO DE DATO");
        model.addColumn("IDENTIFICADOR");
        model.addColumn("VALOR");
        model.addColumn("CONTEXTO");
        model.addColumn("CONTEXTO PADRE");
        
        generateRowsModelVariables(this.rootContext, model);

        return model;
    }

    private void generateRowsModelVariables(Context context, DefaultTableModel model) {
        context.getVariables().forEach((Variable var) -> {
            model.addRow(new Object[]{
                var.getDataType().getWord(),
                var.getIdentifier().getWord(),
                var.getValue() == null ? "null" : (var.getValue().isLeaf() ? ((Lexeme)var.getValue()).getWord() : var.getValue().toString()),
                context.getStatement().toString(),
                context.getParent() == null ? "null" : context.getParent().getStatement().toString()
            });
        });
        context.getChildsContexts().forEach((childContext) -> {
            generateRowsModelVariables(childContext, model);
        });
    }

    public DefaultTableModel getFunctions() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("TIPO DE RETORNO");
        model.addColumn("IDENTIFICADOR");
        model.addColumn("PARAMETROS");
        model.addColumn("CONTEXTO");
        model.addColumn("CONTEXTO PADRE");
        
        generateRowsModelFunctions(this.rootContext, model);

        return model;
    }

    private void generateRowsModelFunctions(Context context, DefaultTableModel model) {
        context.getFunctions().forEach((Function function) -> {
            String params = "";
            params = function.getParameters()
                    .stream()
                    .map((param) -> param.toString() + " , ")
                    .reduce(params, String::concat);
            if (!params.isEmpty()) {
                params = params.substring(0, params.length() - 3);
            }
            model.addRow(new Object[]{
                function.getReturnType().getWord(),
                function.getIdentifier().getWord(),
                params,
                context.getStatement().toString(),
                context.getParent() == null ? "null" : context.getParent().getStatement().toString()
            });
        });
        context.getChildsContexts().forEach((childContext) -> {
            generateRowsModelFunctions(childContext, model);
        });
    }
    
    public String execute(String route) throws IOException{
        String code = this.parsear(), output = "";
        byte[] contents = new byte[1024];
        int byteRead = 0;
        
        Tools.writeInFile(route, code);
        
        Process process = Runtime.getRuntime().exec("node "+route);
        InputStream input = process.getInputStream();
        InputStream inputError = process.getErrorStream();
        BufferedInputStream bufferInput = new BufferedInputStream(input);
        
        while ((byteRead = bufferInput.read(contents)) != -1) {
            output += new String(contents, 0, byteRead);
        }
        
        byteRead = 0;
        contents = new byte[1024];
        bufferInput = new BufferedInputStream(inputError);
        
        while ((byteRead = bufferInput.read(contents)) != -1) {
            output += new String(contents, 0, byteRead);
        }
        
        return output;
    }
}
