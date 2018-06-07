package fr.logicrooms.recherche;
import fr.ligicrooms.main.Accueil;
import fr.ligicrooms.main.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class RechercheChallenger {

    // Déclaration et initialisation des variables récupérées dans config.properties
    int min =0;
    int max=99;
    int vie = 10;
    boolean modeDev = true;

    // Déclaration initialmisation des variables propres au jeu
    int choix;
    boolean rejouer = true;
    boolean quiter = false;
    boolean changer = false;

    // Génération du nombre aléatoire
    int solution = (int) (Math.random() * ( min - max ))*-1;

    // création de la fenetre
    Fenetre fenetre = new Fenetre();

    // Création des JPenel
    JPanel panPrincipal = new JPanel();
    JPanel panProposition = new JPanel();
    JPanel panResolution = new JPanel();
    JPanel panFinJeu = new JPanel();
    JPanel panBoutonFinJeu = new JPanel();

    // Création des Labels
    JLabel labProposition = new JLabel();
    JLabel labResolution = new JLabel();
    JLabel labFinJeu = new JLabel("Fin de jeu");


    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Valider");
    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    // ajhout champ de text
    JFormattedTextField champText = new JFormattedTextField(NumberFormat.getIntegerInstance());


    public RechercheChallenger(){

        // parametrage de la fenetre
        fenetre.setSize(480,200);

        champText.setPreferredSize(new Dimension(100,50));


        // parametrage des label par défaut
        labProposition.setText("Faites une proposition il vous reste " + vie + " chances :");
        labResolution.setText("Nombre entre " + min + " et " + max);

        // mise en place des Layout
        panProposition.setLayout(new BorderLayout());
        panResolution.setLayout(new BorderLayout());
        panPrincipal.setLayout(new GridLayout(2,1));
        panFinJeu.setLayout(new BorderLayout());

        // adds des panels
        panPrincipal.add(panProposition, BorderLayout.NORTH);
        panPrincipal.add(panResolution, BorderLayout.SOUTH);
        panProposition.add(labProposition, BorderLayout.WEST);
        panProposition.add(champText, BorderLayout.EAST);
        panResolution.add(labResolution, BorderLayout.CENTER);
        panResolution.add(boutonValider, BorderLayout.NORTH);
        panFinJeu.add(labFinJeu, BorderLayout.CENTER);
        panFinJeu.add(panBoutonFinJeu, BorderLayout.SOUTH);
        panBoutonFinJeu.add(boutonRejouer);
        panBoutonFinJeu.add(boutonChanger);
        panBoutonFinJeu.add(boutonQuiter);

        // configuration des police d'écriture
        Font font = new Font("Georgia",Font.CENTER_BASELINE,25);
        Font font2 = new Font("Georgia",Font.CENTER_BASELINE,15);
        Font fontChampText = new Font("Georgia",Font.CENTER_BASELINE,35);
        champText.setForeground(Color.blue);
        champText.setFont(fontChampText);
        labResolution.setFont(font);
        labProposition.setFont(font2);
        fenetre.setContentPane(panPrincipal);
        labFinJeu.setFont(font);

        // Ajout des listeners
        boutonValider.addActionListener(new Verification());
        boutonRejouer.addActionListener(new Rejouer());
        boutonChanger.addActionListener(new Changer());
        boutonQuiter.addActionListener(new Quiter());


        fenetre.setVisible(true);

        // valeur par défaut du champ text
        champText.setText("0");


        // activation du mode développeur
        if (modeDev) {
            System.out.println("Solution  : " +solution);
        }

        // Quiter rejouer ou retour à l'accueil
        if(rejouer){


        }else
        if (quiter){
            System.exit(0);
        }else {
            fenetre.setVisible(false);
        }



    }

    // listener
    class Verification implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            choix = Integer.parseInt(champText.getText());

            // Début du jeu
            if(vie > 1 && choix != solution) {
                if (solution != choix && solution < choix) {
                    vie--;
                    labProposition.setText("Raté ouvelle proposition il vous reste " + vie + " vie(s)");
                    labResolution.setForeground(Color.orange);
                    labResolution.setText("Le nombre est plus petit");
                } else {
                    vie--;
                    labProposition.setText("Raté nouvelle proposition il vous reste " + vie + " vie(s)");
                    labResolution.setForeground(Color.blue);
                    labResolution.setText("Le nombre est plus grand");
                }
            }else if (vie > 1) {
                labFinJeu.setForeground(Color.green);
                labFinJeu.setText("Bravo la solution était : " + solution);
                fenetre.setContentPane(panFinJeu);
                fenetre.setVisible(true);
            }else{
                labFinJeu.setForeground(Color.red);
                labFinJeu.setText("Perdu la solution était "+solution);
                fenetre.setContentPane(panFinJeu);
                fenetre.setVisible(true);
            }

        }


    }
    class Rejouer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            RechercheChallenger recherche = new RechercheChallenger();
        }
    }
    class Changer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            Accueil accueil = new Accueil();
        }
    }
    class Quiter implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }














    class FinDeJeu{
        public void FinDeJeu() {

            // Quiter rejouer ou retour à l'accueil
            if (rejouer) {

            } else if (quiter) {

            } else if (changer){
                fenetre.setVisible(false);
            }
        }
    }

}