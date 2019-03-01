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



    int choix;
    boolean rejouer = true;
    boolean quiter = false;
    boolean changer = false;


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
    JLabel labCorpTitre = new JLabel("Touvez la combinaison à " + nombreDeChiffre + " chiffres !");
    JLabel labFinJeu = new JLabel("Fin de jeu");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Commencer");
    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

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
            System.out.println("Jeu de recherche version challenger");
            System.out.println("Mode Développeur activé !");
            System.out.print("La solution est : " + solutionStr);

        }

        // parametrage de la fenetre
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

        //boutonRejouer.addActionListener((ActionListener) new EndGame(game));
        boutonRejouer.addActionListener(new Rejouer());
        boutonChanger.addActionListener(new Changer());
        boutonQuiter.addActionListener(new Quiter());


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
                        if (win && vie>=1) {
                            String strSolution = "";
                            for (int i = 0; i < nombreDeChiffre; i++) {
                                strSolution = strSolution + solution[i];
                            }
                            labFinJeu.setText("Bravo vous avez GAGNEZ ! La solution était : " + strSolution);
                            logger.info("Le joueur gagne il lui reste " + vie + " vie(s)");
                            fenetre.setContentPane(panFinJeu);
                        }else if (vie < 1){
                            String strSolution = "";
                            for (int i = 0; i < nombreDeChiffre; i++) {
                                strSolution = strSolution + solution[i];
                            }
                            labFinJeu.setText("Domage vous avez perdu la solution était : " + strSolution);
                            logger.info("Le joueur à perdu");
                            fenetre.setContentPane(panFinJeu);
                        }
                    }
                champText.setText(null);
                fenetre.setVisible(true);
            }
            catch (Exception z){
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez un entier à " + nombreDeChiffre + " chiffres.", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Traite l'interface de fin de jeu
     *
     * @see Accueil
     */
    class Rejouer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Le joueur choisi de rejouer");
            RechercheChallenger recherche = new RechercheChallenger();
        }
    }

    class Changer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            Accueil accueil = new Accueil();
            logger.trace("le joueur veut changer de jeu");
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