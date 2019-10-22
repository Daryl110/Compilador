/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico.Controllers.API.Config;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author daryl
 */
public class API extends ResourceConfig {
    public API(){
        /* Injectables */
        register(new APIBinder());
        /* Controllers */
        packages(true, "eam.analizador_lexico.Controllers.API");
    }
}
