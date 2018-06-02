package fr.logicrooms.recherche;

import java.util.Scanner;

public class RechercheChallenger {

    public RechercheChallenger(){

        // Déclaration et initialisation des variables récupérées dans config.properties
        int min =0;
        int max=99;
        int vie = 10;

        // Déclaration initialmisation des variables propres au jeu
        int choix;
        Scanner sc = new Scanner(System.in);

        // Génération du nombre aléatoire
        int solution = (int) (Math.random() * ( min - max ))*-1;

        // lancement du jeu
        System.out.println(solution);
        System.out.println("Donnez un nombre entre " + min + " et " + max + " :");
        choix = sc.nextInt();

        // Boucle principal du jeu
        while(vie > 1 && choix != solution) {
            if (solution != choix && solution < choix) {
                vie--;
                System.out.println("Raté le chiffre est plus petit ! il te reste " + vie + " vie(s).");
            } else {
                vie--;
                System.out.println("Raté le chiffre est plus grand ! il te reste " + vie + " vie(s).");
            }
            choix = sc.nextInt();
        }

        // fin du jeu
        if (vie > 1) {
            System.out.println("Bien joué la solution était : " + solution);
        }else{
            System.out.println("Domage tu as perdu la solution était : " + solution);
        }
    }
}