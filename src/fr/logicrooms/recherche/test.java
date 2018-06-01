package fr.logicrooms.recherche;

import java.util.Scanner;

public class test {

    public test(){
        Scanner sc = new Scanner(System.in);
        int min =0;
        int max=0;

        System.out.println("Donnez un interval de jeu mini :");
        min = sc.nextInt();

        System.out.println("Donnez un interval de jeu maxi :");
        max = sc.nextInt();

        int solution = (int) (Math.random() * ( min - max ))*-1;
        int vie = 10;
        int choix;

        System.out.println("Donnez un nombre entre " + min + " et " + max + " :");
        choix = sc.nextInt();

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
        if (vie > 1) {
            System.out.println("Bien joué la solution était : " + solution);
        }else{
            System.out.println("Domage tu as perdu la solution était : " + solution);
        }
    }
}