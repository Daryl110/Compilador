/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.gui_compilador.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author daryl
 * @date 22/11/2019
 */
public class Tools {
    
    public static String changeExtFile(String route, String extOld, String extNew){
        return route.replace(extOld, extNew);
    }

    public static String readFile(String fileRoute) throws IOException {
        Scanner input = null;
        try {
            File file = new File(fileRoute);
            input = new Scanner(file);
            String text = "";
            while (input.hasNext()) {
                text += input.nextLine()+"\n";
            }
            return text;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static void writeInFile(String fileRoute, String text) throws IOException {
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter(fileRoute);

            flwriter.write(text);

            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            //cierra el buffer intermedio
            bfwriter.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    throw new IOException(e.getMessage());
                }
            }
        }
    }
}
