package main;

import org.apache.log4j.Logger;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Lance le jeu
 * @see Accueil
 * @author BarbuDev
 * @version 1.0
 */
public class Main {
    public static boolean modeDev;

    public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        if(args.length>0 && args[0].equals("dev")){
            modeDev = true;
        }


        logger.trace("Entré dans le programme");
        Accueil accueil = new Accueil();
    }
}
