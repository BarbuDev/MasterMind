package fr.logicrooms.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Appel la configuration dans le fichier .properties <br>
 *
 *
 * @author BarbuDev
 * @version 1.0
 */
public class CallConfig {

    String nom;
    private int valeur;
    boolean valeurBoolean;

    public CallConfig() {
    }

    /**
     * Renvoi les variable de type integer
     *
     * @param nom Repprends le nom du paramètre à appeler
     * @return Variable de type integer correspondant au paramètre nom
     */
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

    /**
     * Renvoi les variable de type boolean
     *
     * @param nom Repprends le nom du paramètre à appeler
     * @return Variable de type boolean correspondant au paramètre nom
     */
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
