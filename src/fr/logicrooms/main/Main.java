package fr.logicrooms.main;

import org.apache.log4j.Logger;
/**
 * Lance le jeu
 * @see Accueil
 * @author BarbuDev
 * @version 1.0
 */
public class Main {

    public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.trace("Entré dans le programme");
        Accueil accueil = new Accueil();
    }
}
