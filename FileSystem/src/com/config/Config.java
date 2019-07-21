package com.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static final String MAIN_PORT = "MPORT";
    public static final String ERROR_PORT = "EPORT";
    public static final String IS_DEBUG = "DEBUG";
    public static final String ETFAPIHOST = "ETFAPIHOST";
    public static final String ETFAPIPORT = "ETFAPIPORT";

    public static void main(String[] args) {
        try {
            Properties test = getProperties();
            System.out.println(test.getProperty("Default-Port"));
        } catch (Exception e){

        }
    }

    public static void SaveData(Properties applicationProps) {
        try {
            FileOutputStream out = new FileOutputStream("app.Properties.data");
            applicationProps.store(out, "---No Comment---");
            out.close();
        }catch (Exception e){

        }
    }

    public static Properties getProperties() throws IOException {
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream("app.Properties.data");
        defaultProps.load(in);
        in.close();
        return defaultProps;
    }

    public static Properties getDefaults() {
        Properties config = new Properties();
        config.setProperty(Config.MAIN_PORT, "8080");
        config.setProperty(Config.ERROR_PORT, "2020");
        config.setProperty(Config.IS_DEBUG, "true");
        config.setProperty(Config.ETFAPIHOST, "http://etfapi.ac-local.com");
        config.setProperty(Config.ETFAPIPORT,"2846");
        return config;

    }
}
