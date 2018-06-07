package fr.logicrooms.recherche;
import fr.ligicrooms.main.Accueil;
import fr.ligicrooms.main.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class RechercheDuel {

    // Variables a importer de config.properties
    int intervalMin = 0;
    int intervalMax = 99;
    boolean modeDev = true;

    // autres variables
    String indice = "";
    int solutionOrdi = 0;
    int solutionJoueur = (int) (Math.random() * (intervalMin - intervalMax)) * -1;
    int choixJoueur = 0;
    int choixOrdi = 0;
    boolean tourJoueur = true;

    int intervalMinJoueur = intervalMin;
    int intervalMinOrdi = intervalMin;
    int intervalMaxJoueur = intervalMax;
    int intervalMaxOrdi = intervalMax;

    // création Fenetre Jpanel JButton, JLabel
    Fenetre fenetre = new Fenetre();
    JPanel panInitial = new JPanel();
    JPanel panInitialHaut = new JPanel();
    JPanel panJoueur = new JPanel();
    JPanel panJoueurHaut = new JPanel();
    JPanel panOrdi = new JPanel();
    JPanel panOrdiBas = new JPanel();
    JPanel panFinJeu = new JPanel();
    JPanel panBoutonFin = new JPanel();


    JButton boutonValiderNombre = new JButton("Valider");
    JButton boutonVerifier = new JButton("Vérifier");
    JButton boutonIndiceMoins = new JButton("-");
    JButton boutonIndicePlus = new JButton("+");
    JButton boutonIndiceEgal = new JButton("=");

    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    JLabel labPrincipal = new JLabel("Choisissez un nombre entre "+intervalMin+" et "+intervalMax +" pour l'ordinateur :");
    JLabel labJoueur = new JLabel("Faites un choix entre " + intervalMin + " et " + intervalMax);
    JLabel labChoixOrdi = new JLabel();
    JLabel labFinJeu = new JLabel("Fin de jeu");

    // ajout champs text formaté Int
    JFormattedTextField champText = new JFormattedTextField(NumberFormat.getIntegerInstance());
    JFormattedTextField champTextJoueur = new JFormattedTextField(NumberFormat.getIntegerInstance());


    public RechercheDuel(){

        fenetre.setSize(480,150);
        fenetre.setContentPane(panInitial);

        panInitial.setLayout(new BorderLayout());
        panJoueur.setLayout(new BorderLayout());
        panOrdi.setLayout(new BorderLayout());
        panFinJeu.setLayout(new BorderLayout());

        panInitial.add(panInitialHaut, BorderLayout.NORTH);

        panInitialHaut.add(labPrincipal);
        panInitialHaut.add(champText);

        panInitial.add(boutonValiderNombre, BorderLayout.SOUTH);
        champText.setPreferredSize(new Dimension(40,40));
        champTextJoueur.setPreferredSize(new Dimension(40,40));

        panJoueur.add(panJoueurHaut, BorderLayout.NORTH);
        panJoueurHaut.add(labJoueur);
        panJoueurHaut.add(champTextJoueur);
        panJoueur.add(boutonVerifier, BorderLayout.SOUTH);

        panOrdi.add(labChoixOrdi, BorderLayout.CENTER);
        panOrdi.add(panOrdiBas, BorderLayout.SOUTH);
        panOrdiBas.add(boutonIndicePlus);
        panOrdiBas.add(boutonIndiceMoins);
        panOrdiBas.add(boutonIndiceEgal);





        champTextJoueur.setText("0");



        boutonValiderNombre.addActionListener(new ValidationNombre());
        boutonVerifier.addActionListener(new Verifier());
        boutonIndicePlus.addActionListener(new IndicePlus());
        boutonIndiceMoins.addActionListener(new IndiceMoins());
        boutonIndiceEgal.addActionListener(new IndiceEgal());
        boutonRejouer.addActionListener(new Rejouer());
        boutonChanger.addActionListener(new Changer());
        boutonQuiter.addActionListener(new Quiter());


        choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;






        fenetre.setVisible(true);

        System.out.println(solutionJoueur);





        /*
            System.out.println("Choisir votre solution pour l'ordinateur entre " + intervalMin + " et " + intervalMax + " :");
            solutionOrdi = sc.nextInt();
            choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;


            while(solutionJoueur != choixJoueur && solutionOrdi != choixOrdi) {
                if (tourJoueur) {
                    System.out.println("Au tour du joueur votre proposition :");

                    if (choixJoueur != solutionJoueur) {
                        choixJoueur = sc.nextInt();
                        if (solutionJoueur != choixJoueur && solutionJoueur < choixJoueur) {
                            System.out.println("Raté le chiffre est plus petit !");
                        } else if(solutionJoueur != choixJoueur && solutionJoueur > choixJoueur){
                            System.out.println("Raté le chiffre est plus grand !");
                        }
                    }
                    tourJoueur = false;


                } else {
                    System.out.println("Au tour de l'ordinateur");
                    tourJoueur = true;
                    if (!indice.equals("=")) {

                        if (indice.equals("+")) {
                            intervalMin = choixOrdi;
                            choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;
                        } else if (indice.equals("-")) {
                            intervalMax = choixOrdi;
                            choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;
                        }

                        System.out.println("L'ordinateur propose " + choixOrdi + " comme solution.");
                        System.out.println("Vous avez choisi au dépat : " + solutionOrdi);
                        indice = sc.nextLine();
                        indice = sc.nextLine();

                    }
                }

                if(modeDev) {
                    System.out.println(solutionJoueur + "    " + solutionOrdi);
                    System.out.println(choixJoueur + "    " + choixOrdi);
                }
            }
            if (solutionJoueur == choixJoueur){
                System.out.println("Le joueur a gagné !");
            }else{
                System.out.println("L'ordinateur a gagné !");
            }*/

    }
    class ValidationNombre implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            solutionOrdi = Integer.parseInt(champText.getText());

            if(solutionJoueur != choixJoueur && solutionOrdi != choixOrdi) {
                fenetre.setContentPane(panJoueur);
                labJoueur.setText("Choisissez entre " + intervalMinJoueur + " et " + intervalMaxJoueur);
                choixJoueur = Integer.parseInt(champTextJoueur.getText());

                if (choixJoueur < solutionJoueur){
                    labJoueur.setText("Votre solution est entre " + intervalMinJoueur + " et " + intervalMaxJoueur);
                    intervalMinJoueur = choixJoueur;
                }
                else{
                    labJoueur.setText("Votre solution est entre " + intervalMinJoueur + " et " + intervalMaxJoueur);
                    intervalMaxJoueur = choixJoueur;
                }

            }else{
                // ajout d'une class interne de fin de jeu
            }
        }
    }
    class Verifier implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            fenetre.setContentPane(panOrdi);
            fenetre.setVisible(true);
            choixJoueur = Integer.parseInt(champTextJoueur.getText());

            labChoixOrdi.setText("L'ordinateur porpose la solution suivante : " + choixOrdi);

            if(solutionJoueur != choixJoueur) {
                if (choixJoueur<solutionJoueur) {
                    intervalMinJoueur = choixJoueur;
                } else if (choixJoueur > solutionJoueur) {
                    intervalMaxJoueur = choixJoueur;
                }
            }else{
                FinDeJeu fin = new FinDeJeu("Vous avez gagné");
            }
            labJoueur.setText("Votre solution est entre " + intervalMinJoueur + " et " + intervalMaxJoueur);

        }
    }

    class IndicePlus implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            intervalMin = choixOrdi;
            choixOrdi = (intervalMax + 1 - intervalMin) / 2 + intervalMin ;
            labChoixOrdi.setText("L'ordinateur propose " + choixOrdi + " comme solution.");
            fenetre.setContentPane(panJoueur);
        }
    }
    class IndiceMoins implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            intervalMax = choixOrdi;
            choixOrdi = (intervalMax - intervalMin) / 2 + intervalMin;
            labChoixOrdi.setText("L'ordinateur propose " + choixOrdi + " comme solution.");

            fenetre.setContentPane(panJoueur);
        }
    }
    class IndiceEgal implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FinDeJeu fin = new FinDeJeu("L'ordinateur gagne !");
        }
    }
    class FinDeJeu{
        private String vainceur;

        public FinDeJeu(String vainceur){
            this.vainceur = vainceur;
            fenetre.setContentPane(panFinJeu);
            panFinJeu.add(labFinJeu, BorderLayout.CENTER);
            panFinJeu.add(panBoutonFin, BorderLayout.SOUTH);
            panBoutonFin.add(boutonRejouer);
            panBoutonFin.add(boutonChanger);
            panBoutonFin.add(boutonQuiter);
            Font font = new Font("Georgia", Font.CENTER_BASELINE, 25);
            labFinJeu.setFont(font);
            labFinJeu.setText(vainceur);

            fenetre.setVisible(true);

        }
    }
    class Rejouer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            RechercheDuel recherche = new RechercheDuel();
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

}
