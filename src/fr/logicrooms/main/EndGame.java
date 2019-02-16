package fr.logicrooms.main;

import fr.logicrooms.recherche.*;
import org.apache.log4j.Logger;

public class EndGame {
    public static final Logger logger = Logger.getLogger(EndGame.class);
    public EndGame(String endGame){
        String game = endGame;
        //fenetre.setVisible(false);
        switch (endGame){
            case "rechercheChallenger":
                    logger.trace("Le joueur rejour au même jeu");
                    RechercheChallenger recherche = new RechercheChallenger();
                    break;
            case "recherche":
                logger.trace("Le joueur");
        }

    }
}
