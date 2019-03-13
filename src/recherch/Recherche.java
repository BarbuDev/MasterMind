package recherch;
import org.apache.log4j.Logger;

/**
 * Renvoi vers le bon mode du jeu Recherche
 * @see RechercheChallenger
 * @see RechercheDeffenseur
 * @see RechercheDuel
 *
 * @author BarbuDev
 * @version 1.0
 */

public class Recherche {

    private String modeDeJeu = "";
    public static final Logger logger = Logger.getLogger(Recherche.class);

    public Recherche(String mode) {
        this.modeDeJeu = mode;
        switch (modeDeJeu) {

            // condition de lancement du mode de jeu
            case "Mode Challenger":
                logger.trace("Lancement du mode challenger");
                RechercheChallenger Recherche = new RechercheChallenger();
                break;
            case "Mode défenseur":
                logger.trace("Lancement du mode deffenseur");
                RechercheDeffenseur deffenseur = new RechercheDeffenseur();
                break;
            case "Mode Duel":
                logger.trace("Lancement du mode duel");
                RechercheDuel duel = new RechercheDuel();
                break;
            default:
                break;
        }
    }
}
