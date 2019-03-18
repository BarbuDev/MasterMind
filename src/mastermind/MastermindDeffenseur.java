package mastermind;

import main.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Joue le jeu de mastermind en mode Deffenseur<br>
 *     Algorithme utilisé : Knuth (Jeu.recherche de sous chaine)
 *
 * @author BarbuDev
 * @version 1.0
 */
public class MastermindDeffenseur {

    // Ajout du logger
    public static final Logger logger = Logger.getLogger(MastermindChallenger.class);

    // Variables a récupérées dans config.properties

    CallConfig config = new CallConfig();
    int colorNumber = config.CallConfig("couleurMastermind");
    int longueurCombinaison = config.CallConfig("nombreChiffreCombinaison");

    // autres variables
    int combinaisonSecrete = 0;
    String history = "";

    int propositionIA = 0;
    String stringPropositionIA="";
    int indice = 0;
    CalculScore calcul = new CalculScore();
    int score = 0;
    int combinaisonPossible[] = new int[longueurCombinaison];
    ArrayList tabCombinaisonPossible = new ArrayList();
    int tabCombinaisonPossibleInt = 0;
    String messageErreur = "Saisissez une combinaison à " + longueurCombinaison + " chiffres compris entre 0 et " + (colorNumber-1);

    // création de la fenetre
    Fenetre fenetre = new Fenetre();

    // création des panels
    JPanel mainPan = new JPanel();
    JPanel gamePan = new JPanel();
    JPanel entriesPan = new JPanel();
    JPanel endGamePan = new JPanel();
    JPanel endGameButtonPan = new JPanel();

    // création des labels
    JLabel mainLab = new JLabel("Mastermind");
    JLabel solutionLab = new JLabel("Choisissez une combinaison secrète de " + longueurCombinaison + " chiffres compris entre 0 et " + (colorNumber-1));
    JLabel entriesLab = new JLabel("Faites votre proposition :");
    JLabel endGame = new JLabel("Fin de jeu par ici");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Commencer");


    // ajout champ de text
    JTextField champText = new JTextField();

    // objet listener
    Commencer commencer = new Commencer();
    Jouer jouer = new Jouer();

    JOptionPane jOP = new JOptionPane();

    /**
     * Construit l'IHM
     */
    public MastermindDeffenseur() {
        // création de la fenetre
        fenetre.setSize(580, 350);

        // parametrage du champ text
        champText.setPreferredSize(new Dimension(200, 50));

        // ajout des layout
        mainPan.setLayout(new BorderLayout());
        gamePan.setLayout(new BorderLayout());
        entriesPan.setLayout(new BorderLayout());
        endGamePan.setLayout(new BorderLayout());

        // ajout des panels et autre éléments
        fenetre.setContentPane(mainPan);
        mainPan.add(gamePan, BorderLayout.NORTH);
        mainPan.add(entriesPan, BorderLayout.SOUTH);
        gamePan.add(solutionLab, BorderLayout.CENTER);
        entriesPan.add(entriesLab, BorderLayout.WEST);
        entriesPan.add(champText, BorderLayout.CENTER);
        entriesPan.add(boutonValider, BorderLayout.EAST);
        endGamePan.add(endGame, BorderLayout.CENTER);
        endGamePan.add(endGameButtonPan, BorderLayout.SOUTH);

        // Ajout des listeners
        boutonValider.addActionListener(commencer);

        // affichage de la fenetre
        fenetre.setVisible(true);


        if(longueurCombinaison==1){
            for (int l = 0; l < colorNumber; l++){
                tabCombinaisonPossible.add(""+l);
            }
        }
        if(longueurCombinaison==2){
            for (int k = 0; k < colorNumber; k++){
                for (int l = 0; l < colorNumber; l++){
                    tabCombinaisonPossible.add(""+k+l);
                }
            }
        }
        if(longueurCombinaison==3){
            for(int j = 0;j<colorNumber;j++){
                for (int k = 0; k < colorNumber; k++){
                    for (int l = 0; l < colorNumber; l++){
                        tabCombinaisonPossible.add(""+j+k+l);
                    }
                }
            }
        }

        // construction ArrayList de toutes les combinaisons possibles
        if(longueurCombinaison==4) {
            for (int i = 0; i < colorNumber; i++) {
                for (int j = 0; j < colorNumber; j++) {
                    for (int k = 0; k < colorNumber; k++) {
                        for (int l = 0; l < colorNumber; l++) {
                            tabCombinaisonPossible.add("" + i + j + k + l);
                        }
                    }
                }
            }
        }
        if(longueurCombinaison==5) {
            for (int i = 0; i < colorNumber; i++) {
                for (int j = 0; j < colorNumber; j++) {
                    for (int k = 0; k < colorNumber; k++) {
                        for (int l = 0; l < colorNumber; l++) {
                            for (int m = 0; m < colorNumber; m++) {
                                tabCombinaisonPossible.add("" + i + j + k + l + m);
                            }
                        }
                    }
                }
            }
        }
        if(longueurCombinaison==6) {
            for (int i = 0; i < colorNumber; i++) {
                for (int j = 0; j < colorNumber; j++) {
                    for (int k = 0; k < colorNumber; k++) {
                        for (int l = 0; l < colorNumber; l++) {
                            for (int m = 0; m < colorNumber; m++) {
                                for (int n = 0; n < colorNumber; n++) {
                                    tabCombinaisonPossible.add("" + i + j + k + l + m + n);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(longueurCombinaison==7) {
            for (int i = 0; i < colorNumber; i++) {
                for (int j = 0; j < colorNumber; j++) {
                    for (int k = 0; k < colorNumber; k++) {
                        for (int l = 0; l < colorNumber; l++) {
                            for (int m = 0; m < colorNumber; m++) {
                                for (int n = 0; n < colorNumber; n++) {
                                    for (int o = 0; o < colorNumber; o++) {
                                        tabCombinaisonPossible.add("" + i + j + k + l + m + n + o);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(longueurCombinaison==8) {
            for (int i = 0; i < colorNumber; i++) {
                for (int j = 0; j < colorNumber; j++) {
                    for (int k = 0; k < colorNumber; k++) {
                        for (int l = 0; l < colorNumber; l++) {
                            for (int m = 0; m < colorNumber; m++) {
                                for (int n = 0; n < colorNumber; n++) {
                                    for (int o = 0; o < colorNumber; o++) {
                                        for (int p = 0; p < colorNumber; p++) {
                                            tabCombinaisonPossible.add("" + i + j + k + l + m + n + o + p);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }




    }
    /**
     * Récupère la combinaison choisie par le joueur
     */
    class Commencer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                String z="";
                for(int i = 0; i < longueurCombinaison;i++){
                    z = z +"1";
                }
                if (Integer.parseInt(champText.getText())<0){
                    champText.setText("a");

                }else if (Integer.parseInt(champText.getText())>((colorNumber-1)*Integer.parseInt(z))){
                    champText.setText("a");
                }

                boutonValider.setText("Valider");
                combinaisonSecrete = Integer.parseInt(champText.getText());
                boutonValider.removeActionListener(commencer);
                boutonValider.addActionListener(jouer);


                logger.trace("Le joueur choisi la combinaison secrète : " + combinaisonSecrete);

                String stringCombinaisonSecrete = "";


                int traitementZero = combinaisonSecrete;


                for(int i = 0; i < longueurCombinaison;i++){
                    if(traitementZero==0){
                        stringCombinaisonSecrete = stringCombinaisonSecrete + "0";
                    }
                    traitementZero = traitementZero/10;
                }
                if(combinaisonSecrete != 0){
                    stringCombinaisonSecrete = stringCombinaisonSecrete + combinaisonSecrete;
                }
                String premierChoix = "";

                for(int i=0;i<longueurCombinaison;i++){
                    premierChoix = premierChoix + "0";
                }


                history = history + "Vous avez choisi la combinaison secrète suivante : " + stringCombinaisonSecrete + "<br> L'IA propose la solution suivante : "+premierChoix;

                solutionLab.setText("<html>" + history + "</html>");

                champText.setText("");
                entriesLab.setText("Donnez l'indice à l'IA");

            } catch (Exception z) {
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à 4 chiffres compris entre 0 et " + (colorNumber-1), "Attention", JOptionPane.WARNING_MESSAGE);
            }
            messageErreur = "Saisissez un indice compris entre 00 et "+(longueurCombinaison-1)+"0";
        }
    }

    /**
     * Lance l'algorithme et traite les information saisie par le joueur et l'IA
     */
    class Jouer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {



                // récupération de l'indice
                indice = Integer.parseInt(champText.getText());



                if(indice == (longueurCombinaison*10)){
                    EndGame endGame = new EndGame();
                    endGame.finDeJeu("mastermindDeffenseur","L'IA a trouvé la solution : " + stringPropositionIA);
                    fenetre.setVisible(false);
                }else {

                    tabCombinaisonPossible.remove(0);



                    // traitement de l'information
                    for (int i = 0; i < tabCombinaisonPossible.size(); i++) {
                        tabCombinaisonPossibleInt = Integer.parseInt((String) tabCombinaisonPossible.get(i));
                        for (int j = (longueurCombinaison-1); j >= 0; j--) {
                            combinaisonPossible[j] = tabCombinaisonPossibleInt % 10;
                            tabCombinaisonPossibleInt /= 10;
                        }
                        score = calcul.calculer(propositionIA, combinaisonPossible, colorNumber,longueurCombinaison);


                        if (score != indice) {
                            tabCombinaisonPossible.remove(i);
                            i--;
                        }
                    }
                    // proposition d'une nouvelle solution
                    // Ajout des zéros pour affichage

                    propositionIA = Integer.parseInt((String) tabCombinaisonPossible.get(0));
                    int traitementZero = propositionIA;
                    for(int i = 0; i < longueurCombinaison;i++){
                        if(traitementZero==0){
                            stringPropositionIA = stringPropositionIA + "0";
                        }
                        traitementZero = traitementZero/10;
                    }
                    if(propositionIA != 0){
                        stringPropositionIA = stringPropositionIA + propositionIA;
                    }


                    // fin de boucle


                    history = history + "<br> L'IA propose la solution suivante : " + stringPropositionIA;

                    solutionLab.setText("<html>" + history + "</html>");
                    champText.setText("");
                    stringPropositionIA = "";

                    fenetre.setVisible(true);
                }


            } catch (Exception z) {
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, messageErreur, "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }



}

