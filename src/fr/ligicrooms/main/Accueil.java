package fr.ligicrooms.main;

import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame {
    // Création de la première page du jeu
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
    public Accueil(){
        this.setTitle("LogicRooms");
        this.setSize(480,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

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
        this.setContentPane(panPrincipal);

        Font font = new Font("Georgia",Font.CENTER_BASELINE,40);
        labMastermind.setFont(font);
        labRecherche.setFont(font);
    }




}
