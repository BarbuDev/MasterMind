package fr.logicrooms.mastermind;

public class CalculScore {

    public CalculScore(){}

    public int calculer(int propositionJoueur, int[] combinaisonAleatoire, int colorNumbers){

        int combinaisonPossible[] = new int[colorNumbers];
        int score = 0;
        int tabPropositionJoueur[] = new int[4];

        // Tableau référencent les couleur existante dans la combinaison
        for(int i = 0; i < 4; i++){
            for(int j = 0;j<colorNumbers;j++)
                if(combinaisonAleatoire[i] == j){
                    combinaisonPossible[j]++;
                }
        }




        // Enregistrement de la solution proposée par le joueur
        for(int i =3; i>=0;i--){
            tabPropositionJoueur[i] = propositionJoueur%10;
            propositionJoueur/=10;
        }



        // affichage d'un "O" lors d'une couleur bien placée
        for(int i = 0; i<4;i++){
            if(combinaisonAleatoire[i] == tabPropositionJoueur[i]){
                score+=10;
                tabPropositionJoueur[i] = -1;
                combinaisonPossible[combinaisonAleatoire[i]]--;
            }
        }

        // affichage d'un "X" si couleur mal placée
        for(int i = 0; i < 4;i++){
            if(tabPropositionJoueur[i] != -1){
                if (combinaisonPossible[tabPropositionJoueur[i]] > 0){
                    combinaisonPossible[tabPropositionJoueur[i]]--;
                    tabPropositionJoueur[i] = -1;
                    score+=1;
                }
            }
        }
        return score;
    }

}
