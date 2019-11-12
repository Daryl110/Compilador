/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_semantico.Exceptions;

/**
 *
 * @author daryl
 */
public class SemanticError extends RuntimeException {

    /**
     * Creates a new instance of <code>SemanticError</code> without detail
     * message.
     */
    public SemanticError() {
    }

    /**
     * Constructs an instance of <code>SemanticError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SemanticError(String msg) {
        super(msg);
    }
}
