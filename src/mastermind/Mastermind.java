package mastermind;

import org.apache.log4j.Logger;
/**
 * Renvoi vers le bon mode du jeu Recherche
 * @see MastermindChallenger
 * @see MastermindDeffenseur
 * @see MastermindDuel
 *
 * @author BarbuDev
 * @version 1.0
 */
public class Mastermind {
    private String modeDeJeu = "";
    public static final Logger logger = Logger.getLogger(Mastermind.class);
    public Mastermind(String mode) {
        this.modeDeJeu = mode;
        switch (modeDeJeu) {

            // condition de lancement du mode de jeu
            case "Mode Challenger":
                logger.trace("Lancement du mode challenger");
                MastermindChallenger challenger = new MastermindChallenger();
                break;
            case "Mode défenseur":
                logger.trace("Lancement du mode deffenseur");
                MastermindDeffenseur deffenseur = new MastermindDeffenseur();
                break;
            case "Mode Duel":
                logger.trace("Lancement du mode duel");
                MastermindDuel duel = new MastermindDuel();
                break;
            default:
                break;
        }
    }
}
