package fr.logicrooms.recherche;


import fr.logicrooms.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

/**
 * Joue le jeu de recherche en mode Challenger<br>
 *     Algorithme utilisé : intervale qui se réduit fonction de la saisie joueur
 *
 * @author BarbuDev
 * @version 1.0
 */
public class RechercheChallenger {

    public static final Logger logger = Logger.getLogger(RechercheChallenger.class);
    // variable à récupérer dans config.properties

    CallConfig config = new CallConfig();
    int vie = config.CallConfig("vie");
    int nombreDeChiffre = config.CallConfig("nombreChiffreCombinaison");
    boolean modeDev = config.CallConfigBoolean("modeDev");

    // autre variables
    int solution[] = new int[nombreDeChiffre];
    int proposition[] = new int[nombreDeChiffre];
    char indice[] = new char[nombreDeChiffre];
    boolean win = false;
    String solutionStr = "";
    String strFinJeu;



    int choix;

    // création de la fenetre
    Fenetre fenetre = new Fenetre();

    // Création des JPenel
    // panel général
    JPanel panPrincipal = new JPanel();

    //panel composant le corp du jeu
    JPanel panCorpJeu = new JPanel();



    // Création des Labels
    JLabel labCorpJeu = new JLabel();
    JLabel labCorpTitre = new JLabel("Touvez la combinaison à " + nombreDeChiffre + " chiffres !");


    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Commencer");


    // ajout champ de text
    JTextField champText = new JTextField();

    // Boite pour message d'erreur
    JOptionPane jOP = new JOptionPane();

    // objet listener
    Jouer jouer = new Jouer();
    Commencer commencer = new Commencer();


    /**
     * Construit l'IHM et selectionne la combinaison aléatoire
     */
    public RechercheChallenger() {

        // choix de la combinaison aléatoire
        for (int i = 0; i < nombreDeChiffre; i++) {
            solution[i] = (int) (Math.random() * (0 - 10)) * -1;
        }

        // écriture de la solution
        for (int i = 0; i < nombreDeChiffre; i++) {
            solutionStr += solution[i];
        }

        logger.debug("La solution aléatoire est : " + solutionStr);

        if (modeDev) {
            // affichage de la combinaison
            fenetre.setTitle(fenetre.getTitle()+" => Mode Développeur activé solution du joueur : "+solutionStr);
        }

        // parametrage de la fenetre
        fenetre.setSize(580, 200);

        // parametrage du champ text
        champText.setPreferredSize(new Dimension(200, 50));


        // mise en place des Layout
        panCorpJeu.setLayout(new BorderLayout());
        panPrincipal.setLayout(new GridLayout(2, 1));


        // adds des panels et éléments
        panPrincipal.add(panCorpJeu, BorderLayout.NORTH);
        panCorpJeu.add(labCorpJeu, BorderLayout.WEST);

        panCorpJeu.add(boutonValider, BorderLayout.SOUTH);
        panCorpJeu.add(labCorpTitre, BorderLayout.NORTH);



        // configuration des police d'écriture
        Font font = new Font("Georgia", Font.CENTER_BASELINE, 15);
        Font fontChampText = new Font("Georgia", Font.CENTER_BASELINE, 25);
        champText.setForeground(Color.blue);
        champText.setFont(fontChampText);
        labCorpJeu.setFont(font);
        fenetre.setContentPane(panPrincipal);




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
            boutonValider.setText("Valider");
            boutonValider.removeActionListener(commencer);
            boutonValider.addActionListener(jouer);
        }
    }

    /**
     * Traite les informations et lance l'algorithme
     */
    class Jouer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            try{

                choix = Integer.parseInt(champText.getText());
                logger.debug("Saisie du joueur : " + choix);

                for(int i = nombreDeChiffre-1; i >= 0; i--){
                    proposition[i] = choix % 10;
                    choix/=10;
                }

                    if(!win && vie>=1) {

                        for (int i = 0; i < nombreDeChiffre; i++) {
                            if (solution[i] < proposition[i]) {
                                indice[i] = '-';
                            } else if (solution[i] > proposition[i]) {
                                indice[i] = '+';
                            } else {
                                indice[i] = '=';
                            }
                        }
                        for (int i = 0; i < nombreDeChiffre; i++) {
                            if (indice[i] != '='){
                                win = false;
                                break;
                            }else{
                                win = true;
                            }
                        }

                        String str = "";
                        String str2 = "";
                        for (int i = 0; i<nombreDeChiffre;i++){
                            str = str + proposition[i];
                        }
                        for (int i = 0; i < nombreDeChiffre; i++) {
                            str2 = str2 + indice[i];
                        }
                        vie--;
                        labCorpTitre.setText("Vous proposez : " + str + " ==> Voici vos indices : " + str2 + " Il vous rete " + vie + " chance(s).");

                        for (int i = 0; i < nombreDeChiffre; i++) {
                            if (indice[i] != '='){
                                win = false;
                                break;
                            }else{
                                win = true;
                            }
                        }
                        champText.setText(null);
                        fenetre.setVisible(true);
                        if (win && vie>=1) {

                            String strSolution = "";
                            for (int i = 0; i < nombreDeChiffre; i++) {
                                strSolution = strSolution + solution[i];
                            }
                            strFinJeu = "Bravo vous avez gagner la solution était : " + strSolution;
                            logger.info("Le joueur gagne il lui reste " + vie + " vie(s)");
                            fenetre.setVisible(false);
                            EndGame endGame = new EndGame();
                            endGame.finDeJeu("rechercheChallenger",strFinJeu);


                        }else if (vie < 1){
                            String strSolution = "";
                            for (int i = 0; i < nombreDeChiffre; i++) {
                                strSolution = strSolution + solution[i];
                            }
                            EndGame endGame = new EndGame();
                            endGame.finDeJeu("rechercheChallenger","Domage vous avez perdu la solution était : " + strSolution);
                            logger.info("Le joueur à perdu");
                            fenetre.setVisible(false);
                        }
                    }

            }
            catch (Exception z){
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez un entier à " + nombreDeChiffre + " chiffres.", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }




}