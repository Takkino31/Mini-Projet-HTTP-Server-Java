package com.miniproject.httpserver;

import java.io.FileNotFoundException;

import com.miniproject.httpserver.config.Configuration;
import com.miniproject.httpserver.config.ConfigurationManager;

public class HttpServer {

    /**
     * Demarrage du Server HTTP
     * 
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Server Starting ...");

        ConfigurationManager.loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port : " + conf.getPort());
        System.out.println("Using PorWebroot : " + conf.getWebroot());
    }
}
