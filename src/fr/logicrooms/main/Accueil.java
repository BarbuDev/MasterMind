package fr.logicrooms.main;

import fr.logicrooms.recherche.*;
import fr.logicrooms.mastermind.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

/**
 * Génère l'accueil du jeu : choix du type et mode
 *
 * @see Recherche
 * @see Mastermind
 *
 * @author BarbuDev
 * @version 1.0
 */

public class Accueil extends JFrame {

    public static final Logger logger = Logger.getLogger(Accueil.class);

    // Création de la première page du jeu
    Fenetre fenetre = new Fenetre();

    // Création des conteneurs
    JPanel panPrincipal = new JPanel();
    JPanel panRecherche = new JPanel();
    JPanel panMastermind = new JPanel();
    JPanel panRechercheTitre = new JPanel();
    JPanel panRechercheBouton = new JPanel();
    JPanel panMastermindTitre = new JPanel();
    JPanel panMastermindBouton = new JPanel();


    // Création des Labels de chaque panel
    JLabel labRecherche = new JLabel("RECHERCHE");
    JLabel labMastermind = new JLabel("MASTERMIND");

    // Création des boutons pour les 3 modes de jeu
    JButton boutonRechercheChallenger = new JButton("Mode Challenger");
    JButton boutonRechercheDefender = new JButton("Mode défenseur");
    JButton boutonRechercheDuel = new JButton("Mode Duel");
    JButton boutonMastermindChallenger = new JButton("Mode Challenger");
    JButton boutonMastermindDefender = new JButton("Mode défenseur");
    JButton boutonMastermindDuel = new JButton("Mode Duel");


    // Constructeur par défaut

    /**
     * Génère l'IHM et renvoi vers le bon jeu
     */
    public Accueil(){

        // configuration de la fenetre
        fenetre.setSize(480,300);
        fenetre.setVisible(true);
        // changement police des Labels


        // Ajout des layout
        panPrincipal.setLayout(new GridLayout(2,1));
        panRecherche.setLayout(new GridLayout(2,1));
        panMastermind.setLayout(new GridLayout(2,1));
        panRechercheBouton.setLayout(new GridLayout(1,3));
        panMastermindBouton.setLayout(new GridLayout(1,3));


        // Ajout des labels et panels
        panRechercheTitre.add(labRecherche);
        panMastermindTitre.add(labMastermind);
        panRechercheBouton.add(boutonRechercheChallenger);
        panRechercheBouton.add(boutonRechercheDefender);
        panRechercheBouton.add(boutonRechercheDuel);
        panMastermindBouton.add(boutonMastermindChallenger);
        panMastermindBouton.add(boutonMastermindDefender);
        panMastermindBouton.add(boutonMastermindDuel);

        panRecherche.add(panRechercheTitre);
        panRecherche.add(panRechercheBouton);
        panMastermind.add(panMastermindTitre);
        panMastermind.add(panMastermindBouton);

        panPrincipal.add(panRecherche);
        panPrincipal.add(panMastermind);

        // Déclaration du contentPanel
        fenetre.setContentPane(panPrincipal);

        Font font = new Font("Georgia",Font.CENTER_BASELINE,40);
        labMastermind.setFont(font);
        labRecherche.setFont(font);
        labRecherche.setForeground(Color.red);
        labMastermind.setForeground(Color.blue);

        // déclaration des écoutes de boutons
        boutonRechercheChallenger.addActionListener(new boutonRechercheChallengerListener());
        boutonRechercheDefender.addActionListener(new boutonRechercheDeffenseurListener());
        boutonRechercheDuel.addActionListener(new boutonRechercheDuelListener());
        boutonMastermindChallenger.addActionListener(new boutonMastermindChallengerListener());
        boutonMastermindDefender.addActionListener(new boutonMastermindDeffenseurListener());
        boutonMastermindDuel.addActionListener(new boutonMastermindDuelListener());


    }

    // Classe d'écoute Recherche mode Challenger
    class boutonRechercheChallengerListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Lancement du jeu recherche");
            Recherche recherche = new Recherche(boutonRechercheChallenger.getText());
        }
    }
    class boutonRechercheDeffenseurListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Lancement du jeu recherche");
            Recherche recherche = new Recherche(boutonRechercheDefender.getText());
        }
    }
    class boutonRechercheDuelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Lancement du jeu recherche");
            Recherche recherche = new Recherche(boutonRechercheDuel.getText());
        }
    }
    // Classe d'écoute Mastermind mode Challenger
    class boutonMastermindChallengerListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Lancement du Mastermind");
            Mastermind mastermind = new Mastermind(boutonMastermindChallenger.getText());
        }
    }
    class boutonMastermindDeffenseurListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Lancement du Mastermind");
            Mastermind mastermind = new Mastermind(boutonMastermindDefender.getText());
        }
    }
    class boutonMastermindDuelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Lancement du jeu recherche");
            Mastermind mastermind = new Mastermind(boutonMastermindDuel.getText());
        }
    }
}