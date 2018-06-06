package fr.logicrooms.recherche;

import java.util.Scanner;

public class RechercheDuel {

    public RechercheDuel(){

        int intervalMin = 0;
        int intervalMax = 99;
        boolean modeDev = true;

        String indice = "";
        int solutionOrdi = 0;
        int solutionJoueur = (int) (Math.random() * (intervalMin - intervalMax)) * -1;
        int choixJoueur = 0;
        int choixOrdi = 0;
        boolean tourJoueur = true;

        Scanner sc = new Scanner(System.in);




            System.out.println("Choisir votre solution pour l'ordinateur entre " + intervalMin + " et " + intervalMax + " :");
            solutionOrdi = sc.nextInt();
            choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;


            while(solutionJoueur != choixJoueur && solutionOrdi != choixOrdi) {
                if (tourJoueur) {
                    System.out.println("Au tour du joueur votre proposition :");

                    if (choixJoueur != solutionJoueur) {
                        choixJoueur = sc.nextInt();
                        if (solutionJoueur != choixJoueur && solutionJoueur < choixJoueur) {
                            System.out.println("Raté le chiffre est plus petit !");
                        } else if(solutionJoueur != choixJoueur && solutionJoueur > choixJoueur){
                            System.out.println("Raté le chiffre est plus grand !");
                        }
                    }
                    tourJoueur = false;


                } else {
                    System.out.println("Au tour de l'ordinateur");
                    tourJoueur = true;
                    if (!indice.equals("=")) {

                        if (indice.equals("+")) {
                            intervalMin = choixOrdi;
                            choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;
                        } else if (indice.equals("-")) {
                            intervalMax = choixOrdi;
                            choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;
                        }

                        System.out.println("L'ordinateur propose " + choixOrdi + " comme solution.");
                        System.out.println("Vous avez choisi au dépat : " + solutionOrdi);
                        indice = sc.nextLine();
                        indice = sc.nextLine();

                    }
                }

                if(modeDev) {
                    System.out.println(solutionJoueur + "    " + solutionOrdi);
                    System.out.println(choixJoueur + "    " + choixOrdi);
                }
            }
            if (solutionJoueur == choixJoueur){
                System.out.println("Le joueur a gagné !");
            }else{
                System.out.println("L'ordinateur a gagné !");
            }

    }
}
