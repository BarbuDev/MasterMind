package fr.logicrooms.recherche;

import fr.logicrooms.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
/**
 * Joue le jeu de recherche en mode Duel<br>
 *     Algorithme utilisé : intervale interval divisé par deux à chaque proposition IA
 *
 * @author BarbuDev
 * @version 1.0
 */
public class RechercheDuel {

    public static final Logger logger = Logger.getLogger(RechercheDuel.class);

    // variable à récupérer dans config.properties
    CallConfig config = new CallConfig();
    int nombreDeChiffre = config.CallConfig("nombreChiffreCombinaison");
    boolean modeDev = config.CallConfigBoolean("modeDev");

    // autre variables
    int solutionJoueur[] = new int[nombreDeChiffre];
    int solutionOrdi[] = new int[nombreDeChiffre];

    int propositionJoueur[] = new int[nombreDeChiffre];
    int propositionOrdi[] = new int[nombreDeChiffre];

    int intervalMin[] = new int[nombreDeChiffre];
    int intervalMax[] = new int[nombreDeChiffre];

    boolean win = false;
    boolean winIA = false;

    char indiceJoueur[] = new char[nombreDeChiffre];
    char indiceOrdi[] = new char[nombreDeChiffre];

    int boucle = 0;
    int boucleJoueur = 0;
    int choix = 0;

    String strIndice = "";
    String strSolutionJoueur = "";
    String strSolutionOrdi = "";

    // création de la fenetre
    Fenetre fenetre = new Fenetre();

    // En cas de mauvaise saisie
    JOptionPane jOP = new JOptionPane();

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
    JLabel labCorpTitre = new JLabel("Choisissez une combinaison secrète à " + nombreDeChiffre + " chiffres pour l'ordinateur !");
    JLabel labFinJeu = new JLabel("Fin de jeu");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Valider");


    // ajout champ de text
    JTextField champText = new JTextField();


    // objet listener
    Jouer jouer = new Jouer();
    Commencer commencer = new Commencer();
    TourJoueur tourJoueur = new TourJoueur();

    /**
     * Construit l'IHM
     */
    public RechercheDuel() {


        // choix de la combinaison aléatoire
        for (int i = 0; i < nombreDeChiffre; i++) {
            solutionJoueur[i] = (int) (Math.random() * (0 - 10)) * -1;
        }

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
        panFinJeu.add(labFinJeu, BorderLayout.CENTER);
        panFinJeu.add(panBoutonFinJeu, BorderLayout.SOUTH);

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



        // initialisation intervales pour l'ordinateur
        for (int i = 0; i < nombreDeChiffre; i++){
            intervalMin[i] = 0;
            intervalMax[i] = 10;
        }

    }

    class Commencer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // choix de la combinaison pour l'ordinateur
            boutonValider.removeActionListener(commencer);
            boutonValider.addActionListener(jouer);
            boutonValider.setText("Valider");
            panCorpJeu.add(champText, BorderLayout.EAST);
            fenetre.setVisible(true);
        }
    }

    /**
     * Récupère les information pour le lancement de la boucle de jeu
     */
    class Jouer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                choix = Integer.parseInt(champText.getText());
                logger.debug("le joueur choisi la combinaison suivante pour l'IA : " + choix);
                for(int i = nombreDeChiffre-1;i>=0;i--){
                    solutionOrdi[i] = choix%10;
                    choix/=10;
                }
                champText.setText("");
                labCorpTitre.setText("");



                    for (int i = 0; i < nombreDeChiffre; i++) {
                        strSolutionOrdi = strSolutionOrdi + solutionOrdi[i];
                    }
                    for (int i = 0; i < nombreDeChiffre; i++) {
                        strSolutionJoueur = strSolutionJoueur + solutionJoueur[i];
                    }
                    logger.debug("La solution aléatoire du joueur est : " + strSolutionJoueur);
                    if (modeDev) {
                        fenetre.setTitle(fenetre.getTitle()+" => Mode Dev activé solution joueur : " + strSolutionJoueur);
                    }

                    labCorpJeu.setText("Essayez de trouver la combinaison à " + nombreDeChiffre + " chiffres");
                    boutonValider.removeActionListener(jouer);
                    boutonValider.addActionListener(tourJoueur);

            }catch (Exception z){
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez un entier à " + nombreDeChiffre + " chiffres.", "Attention", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
    /**
     * Traite les informations du joueur et lance l'algorithme
     */
    class TourJoueur implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                    choix = Integer.parseInt(champText.getText());
                    for(int i = nombreDeChiffre-1;i>=0;i--){
                        propositionJoueur[i] = choix%10;
                        choix/=10;
                    }

                    champText.setText("");
                    strIndice = "";
                    for (int i = 0; i < nombreDeChiffre; i++) {
                        if (solutionJoueur[i] < propositionJoueur[i]) {
                            indiceJoueur[i] = '-';
                        } else if (solutionJoueur[i] > propositionJoueur[i]) {
                            indiceJoueur[i] = '+';
                        } else {
                            indiceJoueur[i] = '=';
                        }
                        strIndice = strIndice + indiceJoueur[i];
                        boucleJoueur = 0;
                    }

                    for (int i = 0; i < nombreDeChiffre; i++) {
                        if (indiceJoueur[i] != '=') {
                            win = false;
                            break;
                        } else {
                            win = true;
                        }
                    }



                    for (int i = 0; i < nombreDeChiffre; i++) {
                        propositionOrdi[i] = (intervalMax[i] - intervalMin[i]) / 2 + intervalMin[i];
                    }

                    String strPropositionOrdi = "";

                    for (int i = 0; i < nombreDeChiffre; i++) {
                        strPropositionOrdi = strPropositionOrdi + propositionOrdi[i];
                    }

                    for (int i = 0; i < nombreDeChiffre; i++) {
                        if (solutionOrdi[i] < propositionOrdi[i]) {
                            indiceOrdi[i] = '-';
                            intervalMax[i] = propositionOrdi[i];
                        } else if (solutionOrdi[i] > propositionOrdi[i]) {
                            indiceOrdi[i] = '-';
                            intervalMin[i] = propositionOrdi[i];
                        } else {
                            indiceOrdi[i] = '=';
                        }
                    }
                    for (int i = 0; i < nombreDeChiffre; i++) {
                        if (indiceOrdi[i] != '=') {
                            winIA = false;
                            break;
                        } else {
                            winIA = true;

                        }
                    }
                if (win) {
                        EndGame endGame = new EndGame();
                        endGame.finDeJeu("rechercheDuel","Bravo vous avez gagné");
                    fenetre.setVisible(false);
                    logger.trace("Le joueur gagne");
                }else if (winIA) {
                        EndGame endGame = new EndGame();
                        endGame.finDeJeu("rechercheDuel","L'ordinateur gagne votre solution était : " + strSolutionJoueur);
                        fenetre.setVisible(false);
                        logger.trace("L'IA gagne");
                    }

                    labCorpTitre.setText("L'ordinateur propose : " + strPropositionOrdi);
                    boucleJoueur = 0;
                    labCorpJeu.setText("Faite votre proposition suivante " + strIndice);


            }catch(Exception z){
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez un entier entre 0 et 9", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}