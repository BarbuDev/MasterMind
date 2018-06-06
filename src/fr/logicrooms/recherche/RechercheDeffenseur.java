package fr.logicrooms.recherche;
import fr.ligicrooms.main.Fenetre;

import java.util.Scanner;

public class RechercheDeffenseur {
    int intervalMin = 0;
    int intervalMax = 99;

    Fenetre fenetre = new Fenetre();


    public RechercheDeffenseur() {

        // récup^ération des variables dans config.properties


        String indice = "";
        int choix = 0;
        String solution = "";


        choix = (intervalMax - intervalMin) / 2 + intervalMin;

        Scanner sc = new Scanner(System.in);

        System.out.println("Choisissez un nombre entre " + intervalMin + " et " + intervalMax + " :");
        solution = sc.nextLine();

        while (!indice.equals("=")) {

            if (indice.equals("+")) {
                intervalMin = choix;
                choix = (intervalMax - intervalMin) / 2 + intervalMin;
            } else if (indice.equals("-")) {
                intervalMax = choix;
                choix = (intervalMax - intervalMin) / 2 + intervalMin;
            }

            System.out.println("L'ordinateur propose " + choix + " comme solution.");
            indice = sc.nextLine();

        }
        System.out.println("Je suis un ordinateur fou et je résous toutes les solutions que je veux hahahaaaa!");
    }

}
