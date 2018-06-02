package fr.logicrooms.recherche;

import fr.ligicrooms.main.Fenetre;

import javax.swing.*;
import java.util.Scanner;

public class RechercheChallenger {

    public RechercheChallenger(){

        // Déclaration et initialisation des variables récupérées dans config.properties
        int min =0;
        int max=99;
        int vie = 10;
        boolean modeDev = true;

        // Déclaration initialmisation des variables propres au jeu
        int choix;
        boolean rejouer = false;
        boolean quiter = false;
        Scanner sc = new Scanner(System.in);

        // Génération du nombre aléatoire
        int solution = (int) (Math.random() * ( min - max ))*-1;

        // création de la fenetre
        Fenetre fenetre = new Fenetre();
        fenetre.setSize(300,300);
        fenetre.setVisible(true);

        // Création des JPenel
        JPanel panProposition = new JPanel();
        JPanel panResolution = new JPanel();

        // Création des Labels
        JLabel labProposition = new JLabel("Faites une proposition il vous reste " + vie + " chances : ");
        JLabel labResolution = new JLabel("Nombre entre " + min + " et " + max);

        // ajout du bouton pour valider la proposition
        JButton valider = new JButton("Valider");











        // lancement du jeu
        // activation du mode développeur
        if (modeDev) {
            System.out.println(solution);
        }

        // première tentative
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









        // Quiter rejouer ou retour à l'accueil
        if(rejouer){

        }else
        if (quiter){
            System.exit(0);
        }else {
            fenetre.setVisible(false);
        }



    }
}