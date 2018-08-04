package fr.ligicrooms.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CallConfig {

    String nom;
    int valeur;
    boolean valeurBoolean;

    public CallConfig() {
    }

    public int CallConfig(String nom){

        this.nom = nom;

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            valeur = Integer.parseInt(prop.getProperty(nom));



        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return valeur;
    }

    public boolean CallConfigBoolean(String nom){

        this.nom = nom;

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            valeurBoolean = Boolean.parseBoolean(prop.getProperty(nom));



        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return valeurBoolean;
    }

}
