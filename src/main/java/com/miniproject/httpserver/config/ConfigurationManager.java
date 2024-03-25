package com.miniproject.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.miniproject.httpserver.util.Json;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    public ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    /*
     * Utilisé pour charger un fichier de configuration
     * par le chemin fourni
     */

    public static void loadConfigurationFile(String filePath) throws FileNotFoundException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }

        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }

        } catch (Exception e) {
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Erreur de parsing du fichier de configuration", e);
        }

        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (IOException e) {
            throw new HttpConfigurationException("Erreur interne de parsing du fichier de configuration", e);
        }

    }

    /*
     * Renvoie la configuration chargée actuelle
     */

    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationException("Aucune configuration définie !!! ");
        }
        return myCurrentConfiguration;
    }
}