/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eam.analizador_lexico;

import eam.analizador_lexico.Controllers.API.Config.API;
import eam.analizador_lexico.Views.FrmMain;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Daryl Ospina
 */
public class Main {

    public static HttpServer server;
    public static String BASE_URI = "http://";
    public static JSONObject objProperties = new JSONObject();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmMain mainWindow = new FrmMain();
        
        try {
            JSONObject objTest = (JSONObject) (new JSONParser().parse(
                    new BufferedReader(
                            new InputStreamReader(
                                    Main.class.getClassLoader().getResourceAsStream("aplication.properties.json")
                            )
                    )
            ));
            if (objTest != null) {
                objProperties = objTest;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        new Thread(() -> {
            BASE_URI += objProperties.get("host").toString() + ":" + objProperties.get("port") + "/" + objProperties.get("urlAPI").toString();
            Main.startServer();
        }).start();
        
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    private static void startServer() {
        final ResourceConfig configApp = new API();
        Main.server = GrizzlyHttpServerFactory.createHttpServer(URI.create(Main.BASE_URI), configApp);
    }
}
