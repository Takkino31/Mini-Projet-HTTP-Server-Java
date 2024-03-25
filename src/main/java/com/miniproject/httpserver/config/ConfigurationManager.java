package com.miniproject.httpserver.config;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;

    public ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    /*
    *
    */

    public void loadConfigurationFile() {

    }

    /*
    *
     */

    public void getCurrentConfiguration() {

    }
}

