package fr.logicrooms.recherche;

import fr.logicrooms.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

/**
 * Joue le jeu de recherche en mode Deffenseur<br>
 *     Algorithme utilisé : intervale interval divisé par deux à chaque proposition IA
 *
 * @author BarbuDev
 * @version 1.0
 */

public class RechercheDeffenseur {

    public static final Logger logger = Logger.getLogger(RechercheDeffenseur.class);
    // variable à récupérer dans config.properties
    private CallConfig config = new CallConfig();
    private int nombreDeChiffre = config.CallConfig("nombreChiffreCombinaison");
    boolean modeDev = config.CallConfigBoolean("modDev");

    // autre variables
    int solution[] = new int[nombreDeChiffre];
    int proposition[] = new int[nombreDeChiffre];
    int intervalMin[] = new int[nombreDeChiffre];
    int intervalMax[] = new int[nombreDeChiffre];
    char indice[] = new char[nombreDeChiffre];
    boolean win = false;
    int choix = 0;
    String strHistorique = "";
    String solutionStr = "";
    String strFinJeu;

    // boite message d'erreur
    JOptionPane jOP = new JOptionPane();

    // création de la fenetre
    Fenetre fenetre = new Fenetre();

    // Création des JPenel
    // panel général
    JPanel panPrincipal = new JPanel();

    //panel composant le corp du jeu
    JPanel panCorpJeu = new JPanel();

    // panel de fin de jeu
    JPanel panFinJeu = new JPanel();
    JPanel panBoutonFinJeu = new JPanel();

    // Création des Labels
    JLabel labCorpJeu = new JLabel();
    JLabel labCorpTitre = new JLabel("Choisissez une combinaison à " + nombreDeChiffre + " chiffres pour l'IA.");
    JLabel labFinJeu = new JLabel("Fin de jeu");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Valider");
    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    // ajout champ de text
    JTextField champText = new JTextField();

    // objet listener
    Jouer jouer = new Jouer();
    Commencer commencer = new Commencer();

    /**
     * Construit l'IHM
     */

    public RechercheDeffenseur() {


        // création de al fenetre
        fenetre.setSize(580, 200);

        // parametrage du champ text
        champText.setPreferredSize(new Dimension(200, 50));

        // mise en place des Layout
        panCorpJeu.setLayout(new BorderLayout());
        panPrincipal.setLayout(new GridLayout(2, 1));
        panFinJeu.setLayout(new BorderLayout());

        // adds des panels et éléments
        panPrincipal.add(panCorpJeu, BorderLayout.NORTH);
        panCorpJeu.add(labCorpJeu, BorderLayout.WEST);
        panCorpJeu.add(boutonValider, BorderLayout.SOUTH);
        panCorpJeu.add(labCorpTitre, BorderLayout.NORTH);
        panFinJeu.add(labCorpJeu, BorderLayout.NORTH);
        panFinJeu.add(labFinJeu, BorderLayout.CENTER);
        panFinJeu.add(panBoutonFinJeu, BorderLayout.SOUTH);
        panBoutonFinJeu.add(boutonRejouer);
        panBoutonFinJeu.add(boutonChanger);
        panBoutonFinJeu.add(boutonQuiter);

        // configuration des police d'écriture
        Font font = new Font("Georgia", Font.CENTER_BASELINE, 15);
        Font fontChampText = new Font("Georgia", Font.CENTER_BASELINE, 25);
        champText.setForeground(Color.blue);
        champText.setFont(fontChampText);
        labCorpJeu.setFont(font);
        fenetre.setContentPane(panPrincipal);
        labFinJeu.setFont(font);

        // Ajout des listeners
        boutonValider.addActionListener(commencer);
        fenetre.setVisible(true);



    }

    /**
     * Récupère les information pour le lancement de la boucle de jeu
     */
    class Commencer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            panCorpJeu.add(champText, BorderLayout.EAST);

            boutonValider.removeActionListener(commencer);
            boutonValider.addActionListener(jouer);
            boutonValider.setText("Valider");

            labCorpJeu.setText("Donner votre chiffre : ");

            fenetre.setVisible(true);


        }


    }

    /**
     * Traite les informations et lance l'algorithme
     */
    class Jouer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (Integer.parseInt(champText.getText()) < 1){
                    champText.setText("a");
                }
                choix = Integer.parseInt(champText.getText());
                logger.debug("Le joueur choisi la combinaison :" + choix);

                for(int i = nombreDeChiffre-1;i >= 0;i--){
                    solution[i] = choix % 10;
                    choix/=10;
                }

                int nombreEssai = 0;

                    for (int i = 0; i < nombreDeChiffre; i++) {
                        intervalMin[i] = 0;
                        intervalMax[i] = 10;
                    }
                    while (!win) {

                        nombreEssai++;
                        for (int i = 0; i < nombreDeChiffre; i++) {
                            proposition[i] = (intervalMax[i] - intervalMin[i]) / 2 + intervalMin[i];
                        }

                        for (int i = 0; i < nombreDeChiffre; i++) {
                            strHistorique = strHistorique + proposition[i];
                        }

                        strHistorique = strHistorique + " - ";

                        for (int i = 0; i < nombreDeChiffre; i++) {
                            if (solution[i] < proposition[i]) {
                                intervalMax[i] = proposition[i];
                            } else if (solution[i] > proposition[i]) {
                                intervalMin[i] = proposition[i];
                            } else {
                                indice[i] = '=';
                            }
                        }
                        for (int i = 0; i < nombreDeChiffre; i++) {
                            if (indice[i] != '=') {
                                win = false;
                                break;
                            } else {
                                win = true;
                            }
                        }


                        String strProposition = "";


                        for (int i = 0; i < nombreDeChiffre; i++) {
                            strProposition = strProposition + proposition[i];

                        }
                        strFinJeu = "<html>Mes propositions : " + strHistorique + "\n" + "<br>Je sais j'ai trouvé en " + nombreEssai + " essais, la solution est :  " + strProposition + "</html>";



                    }
                    logger.debug("Résolution par l'ordinateur : " + strHistorique + " en " + nombreEssai + " boucle(s)");
                    fenetre.setVisible(false);
                    EndGame endGame = new EndGame();
                    endGame.finDeJeu("rechercheDeffenseur",strFinJeu);



            }catch (Exception z){
                logger.warn("Mauvaise saisie du joueur");
                jOP.showMessageDialog(null, "Saisissez un entier entre à " + nombreDeChiffre + " chiffres.", "Attention", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
