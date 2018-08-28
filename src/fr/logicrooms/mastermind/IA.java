package fr.logicrooms.mastermind;

import java.util.ArrayList;
import java.util.Scanner;

public class IA {
    public IA(){
        // déclaration des variables
        String str = "";
        int combinaisonSecrete = 0;
        Scanner scan = new Scanner(System.in);
        int propositionIA = 0;
        boolean win = false;
        int indice = 0;
        CalculScore calcul = new CalculScore();
        int score = 0;
        int colorNumber = 6;
        int combinaisonPossible[] = new int[4];
        ArrayList tabCombinaisonPossible = new ArrayList();
        int tabCombinaisonPossibleInt = 0;

        // récupération de la combinaison
        System.out.println("Choisissez une combinaison secrète : ");
        combinaisonSecrete = scan.nextInt();

        // construction ArrayList de toutes les combinaisons possibles
        for (int i = 0; i < colorNumber;i++){
            for(int j = 0;j<colorNumber;j++){
                for (int k = 0; k < colorNumber; k++){
                    for (int l = 0; l < colorNumber; l++){
                        tabCombinaisonPossible.add(""+i+j+k+l);
                    }
                }
            }
        }

        for(int i = 0; i<tabCombinaisonPossible.size();i++){
            System.out.println(tabCombinaisonPossible.get(i));
        }

        // Boucle de jeu
        while(win == false) {
            // récupération de l'indice
            System.out.println("L'IA propose la combinaison suivante : " + propositionIA);
            System.out.println("Donner les indices à l'IA : ");
            indice = scan.nextInt();
            if(indice == 40){
                win = true;
                break;
            }
            System.out.println("Raté");
            tabCombinaisonPossible.remove(0);

            // traitement de l'information
            for(int i = 0; i<tabCombinaisonPossible.size();i++) {
                tabCombinaisonPossibleInt = Integer.parseInt((String) tabCombinaisonPossible.get(i));
                for (int j = 3;j>=0;j--){
                    combinaisonPossible[j] = tabCombinaisonPossibleInt%10;
                    tabCombinaisonPossibleInt/=10;
                }
                score = calcul.calculer(propositionIA, combinaisonPossible, colorNumber);

                for(int z = 0; z <4;z++){
                    str = str + combinaisonPossible[z];
                }

                System.out.println(str + " " +score + " " + indice);
                str = "";
                if (score != indice){
                    tabCombinaisonPossible.remove(i);
                    i--;
                }
            }
            // proposition d'une nouvelle solution
            propositionIA = Integer.parseInt((String) tabCombinaisonPossible.get(0));
            System.out.println(tabCombinaisonPossible.size());

            // fin de boucle


        }
    }
}
