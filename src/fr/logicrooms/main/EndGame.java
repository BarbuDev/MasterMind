package fr.logicrooms.main;

import fr.logicrooms.mastermind.MastermindChallenger;
import fr.logicrooms.mastermind.MastermindDeffenseur;
import fr.logicrooms.mastermind.MastermindDuel;
import fr.logicrooms.recherche.RechercheChallenger;
import fr.logicrooms.recherche.RechercheDeffenseur;
import fr.logicrooms.recherche.RechercheDuel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gère la fin de jeu
 * @see RechercheChallenger
 * @see RechercheDeffenseur
 * @see RechercheDuel
 * @see MastermindChallenger
 * @see MastermindDeffenseur
 * @see MastermindDuel
 * @see Accueil
 *
 *
 * @author BarbuDev
 * @version 1.0
 */

public class EndGame {

    public static final Logger logger = Logger.getLogger(EndGame.class);

    Fenetre fenetre = new Fenetre();
    // panel de fin de jeu
    JPanel panFinJeu = new JPanel();
    JLabel labFinJeu = new JLabel("Fin de jeu");

    JPanel panBoutonFinJeu = new JPanel();


    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    String classeRejouer;

    /**
     *
     * @param classeRejouer
     * @param strFinJeu
     */
    public void finDeJeu (String classeRejouer,String strFinJeu){

        this.classeRejouer = classeRejouer;
        panFinJeu.setLayout(new BorderLayout());
        panFinJeu.add(labFinJeu, BorderLayout.CENTER);
        panFinJeu.add(panBoutonFinJeu, BorderLayout.SOUTH);
        panBoutonFinJeu.add(boutonRejouer);
        panBoutonFinJeu.add(boutonChanger);
        panBoutonFinJeu.add(boutonQuiter);

        Font font = new Font("Georgia", Font.CENTER_BASELINE, 15);
        labFinJeu.setFont(font);
        boutonRejouer.addActionListener(new Rejouer());
        boutonChanger.addActionListener(new Changer());
        boutonQuiter.addActionListener(new Quiter());

        labFinJeu.setText(strFinJeu);
        fenetre.setContentPane(panFinJeu);
        fenetre.setSize(580, 200);
        fenetre.setVisible(true);
    }

    class Rejouer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (classeRejouer) {
                case "rechercheChallenger":
                    RechercheChallenger challenger = new RechercheChallenger();
                    break;
                case "rechercheDeffenseur":
                    RechercheDeffenseur deffenseur = new RechercheDeffenseur();
                    break;
                case "rechercheDuel":
                    RechercheDuel duel = new RechercheDuel();
                    break;
                case "mastermindChallenger":
                    MastermindChallenger challengerM = new MastermindChallenger();
                    break;
                case "mastermindDeffenseur":
                    MastermindDeffenseur deffenseurM = new MastermindDeffenseur();
                    break;
                case "mastermindDuel":
                    MastermindDuel duelM = new MastermindDuel();
                    break;
            }

            fenetre.setVisible(false);
            logger.trace("Le joueur choisi de rejouer");



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
