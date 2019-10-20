/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Controllers;

import eam.analizador_lexico.Models.Lexeme;
import eam.analizador_lexico.Models.LexicalAnalyzer;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daryl Ospina
 */
public class LexicalAnalyzerController {

    public static ArrayList<Lexeme> lexemes = new ArrayList<>();
    private LexicalAnalyzer lexicalAnalyzer;

    public DefaultTableModel analyze(String text) {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Token");
        model.addColumn("Type");
        model.addColumn("Positions");
        
        this.lexicalAnalyzer = new LexicalAnalyzer(text);
        LexicalAnalyzerController.lexemes = this.lexicalAnalyzer.analyze();
        
        lexemes.forEach((lexeme) -> {
            model.addRow(new Object[]{
                lexeme.getWord(),
                lexeme.getType(),
                "row: "+lexeme.getRow()+" - columns: "+lexeme.getColumn()
            });
        });

        return model;
    }
}
