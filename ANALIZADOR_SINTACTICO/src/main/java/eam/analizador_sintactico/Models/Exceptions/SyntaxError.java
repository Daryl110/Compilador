/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_sintactico.Models.Exceptions;

/**
 *
 * @author Daryl Ospina
 */
public class SyntaxError extends RuntimeException{

    public SyntaxError(String message) {
        super(message);
    }
}
