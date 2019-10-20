/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico;

import eam.analizador_sintactico.Views.FrmMain;

/**
 *
 * @author Daryl Ospina
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            FrmMain mainWindow = new FrmMain();
            mainWindow.setLocationRelativeTo(null);
            mainWindow.setVisible(true);
        });
    }
    
}
