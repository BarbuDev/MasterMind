package fr.logicrooms.mastermind;

import fr.logicrooms.main.Accueil;
import fr.logicrooms.main.CallConfig;
import fr.logicrooms.main.EndGame;
import fr.logicrooms.main.Fenetre;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Joue le jeu de mastermind en mode Duel<br>
 *     Algorithme utilisé : Knuth (recherche de sous chaine)
 *
 * @author BarbuDev
 * @version 1.0
 */
public class MastermindDuel {
    // Ajout du logger
    public static final Logger logger = Logger.getLogger(MastermindChallenger.class);

    // Variables a récupérées dans config.properties
    CallConfig config = new CallConfig();
    Boolean modeDev = config.CallConfigBoolean("modeDev");
    int colorNumbers = config.CallConfig("couleurMastermind");
    int life = config.CallConfig("vie");

    // autres variables
    int choix = 0;
    String strSolution = "";
    int combinaisonAleatoire[] = new int[4];
    int score=0;
    String historique = "<br> Proposition joueur : <br>";
    int tabPropositionJoueur = 0;
    int combinaisonSecrete = 0;
    String history = "";
    int indice = 0;
    ArrayList tabCombinaisonPossible = new ArrayList();
    int tabCombinaisonPossibleInt = 0;
    int combinaisonPossible[] = new int[4];
    int propositionIA = 0;
    CalculScore calcul = new CalculScore();
    String stringPropositionIA ="";

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
    JLabel solutionLabJoueur = new JLabel("Vos propositions précédentes : ");
    JLabel solutionLabIA = new JLabel("Propositions de l'IA");
    JLabel entriesLab = new JLabel("Choisissez une combinaison secrète :");
    JLabel endGame = new JLabel("Fin de jeu par ici");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Valider");
    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    // ajout champ de text
    JTextField champText = new JTextField();

    // objet listener
    Commencer commencer = new Commencer();
    Jouer jouer = new Jouer();
    TourIA tourIA = new TourIA();

    JOptionPane jOP = new JOptionPane();


    /**
     * Construit l'IHM et choisi une combinaison aléatoire pour le joueur
     */
    public MastermindDuel(){
        // création de la fenetre
        fenetre.setSize(680, 350);

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
        gamePan.add(solutionLabJoueur, BorderLayout.WEST);
        gamePan.add(solutionLabIA, BorderLayout.EAST);
        entriesPan.add(entriesLab, BorderLayout.WEST);
        entriesPan.add(champText,BorderLayout.CENTER);
        entriesPan.add(boutonValider, BorderLayout.EAST);
        endGamePan.add(endGame, BorderLayout.CENTER);
        endGamePan.add(endGameButtonPan, BorderLayout.SOUTH);
        endGameButtonPan.add(boutonRejouer);
        endGameButtonPan.add(boutonChanger);
        endGameButtonPan.add(boutonQuiter);

        // Ajout des listeners
        boutonValider.addActionListener(commencer);

        // affichage de la fenetre
        fenetre.setVisible(true);

        // choix de la combinaison aléatoire
        for (int i = 0; i < 4; i++) {
            //combinaisonAleatoire[i] = 9;
            combinaisonAleatoire[i] = (int) (Math.random() * (0-colorNumbers)*-1);
        }

        if(modeDev){
            // affichage de la combinaison
            for (int i = 0; i < 4; i++) {
                System.out.print(combinaisonAleatoire[i]);
            }
        }

        // construction ArrayList de toutes les combinaisons possibles
        for (int i = 0; i < colorNumbers;i++){
            for(int j = 0;j<colorNumbers;j++){
                for (int k = 0; k < colorNumbers; k++){
                    for (int l = 0; l < colorNumbers; l++){
                        tabCombinaisonPossible.add(""+i+j+k+l);
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
                entriesLab.setText("Faites votre proposition");
                boutonValider.setText("Valider");
                if (Integer.parseInt(champText.getText())<0){
                    champText.setText("a");

                }else if (Integer.parseInt(champText.getText())>((colorNumbers-1)*1111)){
                    champText.setText("a");
                }
                combinaisonSecrete = Integer.parseInt(champText.getText());
                boutonValider.removeActionListener(commencer);
                boutonValider.addActionListener(jouer);
                logger.trace("Le joueur choisi la combinaison secrète : " + combinaisonSecrete);

                history = history + "Vous avez choisi la combinaison secrète suivante : " + combinaisonSecrete;

                solutionLabIA.setText("<html>" + history + "</html>");
                history = history + "<br> l'ordinateur propose : ";

                champText.setText("");

            } catch (Exception z) {
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à 4 chiffres compris entre 0 et " + (colorNumbers-1), "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    /**
     * Lance l'algorithme et traite les information saisie par le joueur
     */
    class Jouer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if (Integer.parseInt(champText.getText())<0){
                    champText.setText("a");

                }else if (Integer.parseInt(champText.getText())>((colorNumbers-1)*1111)){
                    champText.setText("a");
                }

                tabPropositionJoueur = Integer.parseInt(champText.getText());
                CalculScore calcul = new CalculScore();
                score = calcul.calculer(tabPropositionJoueur,combinaisonAleatoire,colorNumbers);

                // sortie de la boucle de jeu lorsque la combinaison est trouvée
                if(score == 40){
                    EndGame endGame = new EndGame();
                    endGame.finDeJeu("mastermindDuel","Bravo vous avez GAGNEZ !");
                    logger.info("Le joueur gagne il lui reste " + life + " vie(s)");
                    fenetre.setVisible(false);
                }


                if(tabPropositionJoueur == 0){
                    historique = historique + "0000";
                }else if(tabPropositionJoueur < 10){
                    historique = historique + "000" + tabPropositionJoueur;
                }else if(tabPropositionJoueur <100){
                    historique = historique + "00" + tabPropositionJoueur;
                }else if(tabPropositionJoueur<1000){
                    historique = historique + "0" + tabPropositionJoueur;
                }else{
                    historique = historique + tabPropositionJoueur;
                }

                life--;
                historique = historique + " " + score/10 + " bien placé(s)" + score%10 + " mal placé(s). Ils vous reste " + life + " vie(s)" + "<br>";


                solutionLabJoueur.setText("<html>" + historique + "</html>");
                champText.setText("");

                entriesLab.setText("Donnez l'indice à l'IA");

                if(propositionIA < 10){
                    stringPropositionIA = "000" + propositionIA;
                }else if(propositionIA < 100){
                    stringPropositionIA = "00" + propositionIA;
                }else if(propositionIA <1000){
                    stringPropositionIA = "0" + propositionIA;
                }else{
                    stringPropositionIA = "" + propositionIA;
                }

                history = history + "<br>" +stringPropositionIA;
                solutionLabIA.setText("<html>" + history + "</html>");
                boutonValider.removeActionListener(jouer);
                boutonValider.addActionListener(tourIA);

            }catch (Exception z){
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à 4 chiffres compris entre 0 et " + colorNumbers, "Attention", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
    /**
     * Lance l'algorithme et traite les information saisie par l'IA
     */
    class TourIA implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {



                // récupération de l'indice

                entriesLab.setText("Faites votre proposition :");
                indice = Integer.parseInt(champText.getText());
                champText.setText("");

                if(indice == 40){
                    EndGame endGame = new EndGame();
                    endGame.finDeJeu("mastermindDuel","L'IA a trouvé la solution : " + combinaisonSecrete);
                    fenetre.setVisible(false);
                }else {

                    tabCombinaisonPossible.remove(0);

                    // traitement de l'information
                    for (int i = 0; i < tabCombinaisonPossible.size(); i++) {
                        tabCombinaisonPossibleInt = Integer.parseInt((String) tabCombinaisonPossible.get(i));
                        for (int j = 3; j >= 0; j--) {
                            combinaisonPossible[j] = tabCombinaisonPossibleInt % 10;
                            tabCombinaisonPossibleInt /= 10;
                        }
                        score = calcul.calculer(propositionIA, combinaisonPossible, colorNumbers);


                        if (score != indice) {
                            tabCombinaisonPossible.remove(i);
                            i--;
                        }
                    }
                    // proposition d'une nouvelle solution
                    propositionIA = Integer.parseInt((String) tabCombinaisonPossible.get(0));

                    fenetre.setVisible(true);
                }

                boutonValider.removeActionListener(tourIA);
                boutonValider.addActionListener(jouer);


            } catch (Exception z) {
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à 4 chiffres compris entre 0 et " + (colorNumbers-1), "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
