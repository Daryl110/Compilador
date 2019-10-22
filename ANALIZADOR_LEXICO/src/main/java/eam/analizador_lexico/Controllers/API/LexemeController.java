/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Controllers.API;

import eam.analizador_lexico.Controllers.LexicalAnalyzerController;
import eam.analizador_lexico.Models.Lexeme;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author daryl
 */
@Path("lexemes")
public class LexemeController {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Lexeme> listLexemes(){
        return LexicalAnalyzerController.lexemes;
    }
}
