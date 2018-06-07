package fr.logicrooms.recherche;

public class Recherche {

    private String modeDeJeu = "";

    public Recherche(String mode) {
        this.modeDeJeu = mode;
        switch (modeDeJeu) {

            // condition de lancement du mode de jeu
            case "Mode Challenger":
                System.out.println("Mode Challenger activé");
                RechercheChallenger challenger = new RechercheChallenger();
                break;
            case "Mode défenseur":
                System.out.println("Mode deffenseur activé");
                RechercheDeffenseur deffenseur = new RechercheDeffenseur();
                break;
            case "Mode Duel":
                System.out.println("Mode Duel activé");
                RechercheDuel duel = new RechercheDuel();
                break;
            default:
                break;
        }
    }

}
