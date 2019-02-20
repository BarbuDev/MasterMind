package fr.logicrooms.mastermind;

import fr.logicrooms.main.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MastermindDeffenseur {

    // Ajout du logger
    public static final Logger logger = Logger.getLogger(MastermindChallenger.class);

    // Variables a récupérées dans config.properties

    CallConfig config = new CallConfig();
    Boolean modeDev = config.CallConfigBoolean("modeDev");
    int colorNumber = config.CallConfig("couleurMastermind");

    // autres variables
    int combinaisonSecrete = 0;
    String history = "";

    int propositionIA = 0;
    int indice = 0;
    CalculScore calcul = new CalculScore();
    int score = 0;
    int combinaisonPossible[] = new int[4];
    ArrayList tabCombinaisonPossible = new ArrayList();
    int tabCombinaisonPossibleInt = 0;

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
    JLabel solutionLab = new JLabel("Choisissez une combinaison secrète de 4 chiffres compris entre 0 et " + (colorNumber-1));
    JLabel entriesLab = new JLabel("Faites votre proposition :");
    JLabel endGame = new JLabel("Fin de jeu par ici");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Commencer");
    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    // ajout champ de text
    JTextField champText = new JTextField();

    // objet listener
    Commencer commencer = new Commencer();
    Jouer jouer = new Jouer();

    JOptionPane jOP = new JOptionPane();


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
        endGameButtonPan.add(boutonRejouer);
        endGameButtonPan.add(boutonChanger);
        endGameButtonPan.add(boutonQuiter);

        // Ajout des listeners
        boutonValider.addActionListener(commencer);
        boutonRejouer.addActionListener(new Rejouer());
        boutonChanger.addActionListener(new Changer());
        boutonQuiter.addActionListener(new Quiter());

        // affichage de la fenetre
        fenetre.setVisible(true);

        // construction Arraylist de toutes les combinaisons possibles
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


    }

    class Commencer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                boutonValider.setText("Valider");
                combinaisonSecrete = Integer.parseInt(champText.getText());
                boutonValider.removeActionListener(commencer);
                boutonValider.addActionListener(jouer);


                logger.trace("Le joueur choisi la combinaison secrète : " + combinaisonSecrete);

                history = history + "Vous avez choisi la combinaison secrète suivante : " + combinaisonSecrete + "<br> L'IA propose la solution suivante : 0000";

                solutionLab.setText("<html>" + history + "</html>");

                champText.setText("");
                entriesLab.setText("Donnez l'indice à l'IA");

            } catch (Exception z) {
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à 4 chiffres compris entre 0 et " + (colorNumber-1), "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    class Jouer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {



                // récupération de l'indice
                indice = Integer.parseInt(champText.getText());

                if(indice == 40){
                    endGame.setText("L'IA a trouvé la solution : " + combinaisonSecrete);
                    fenetre.setContentPane(endGamePan);
                    fenetre.setSize(580, 200);
                    fenetre.setVisible(true);
                }else {

                    tabCombinaisonPossible.remove(0);

                    // traitement de l'information
                    for (int i = 0; i < tabCombinaisonPossible.size(); i++) {
                        tabCombinaisonPossibleInt = Integer.parseInt((String) tabCombinaisonPossible.get(i));
                        for (int j = 3; j >= 0; j--) {
                            combinaisonPossible[j] = tabCombinaisonPossibleInt % 10;
                            tabCombinaisonPossibleInt /= 10;
                        }
                        score = calcul.calculer(propositionIA, combinaisonPossible, colorNumber);


                        if (score != indice) {
                            tabCombinaisonPossible.remove(i);
                            i--;
                        }
                    }
                    // proposition d'une nouvelle solution
                    propositionIA = Integer.parseInt((String) tabCombinaisonPossible.get(0));

                    // fin de boucle


                    history = history + "<br> L'IA propose la solution suivante : " + propositionIA;

                    solutionLab.setText("<html>" + history + "</html>");
                    champText.setText("");

                    fenetre.setVisible(true);
                }


            } catch (Exception z) {
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à 4 chiffres compris entre 0 et " + colorNumber, "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

        class Rejouer implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.setVisible(false);
                logger.trace("Le joueur choisi de rejouer");
                MastermindDeffenseur mastermind = new MastermindDeffenseur();
            }
        }

        class Changer implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.setVisible(false);
                logger.trace("Le joueur choisi de changer de jeu");
                Accueil accueil = new Accueil();
            }
        }

        class Quiter implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                logger.trace("Le joueur quitte le jeu");
                System.exit(0);
            }
        }


}

