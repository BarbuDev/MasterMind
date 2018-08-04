package fr.ligicrooms.main;

import org.apache.log4j.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.trace("Entré dans le programme");
        Accueil accueil = new Accueil();
    }
}
