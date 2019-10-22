/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.gui_compilador;

import eam.gui_compilador.Views.FrmMain;

/**
 *
 * @author daryl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmMain mainWindow = new FrmMain();
        
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
    
}
