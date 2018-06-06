package fr.logicrooms.recherche;
import fr.ligicrooms.main.Accueil;
import fr.ligicrooms.main.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class RechercheDeffenseur {

    // récupération des variables dans config.properties
    int intervalMin = 0;
    int intervalMax = 99;
    boolean modeDev = true;

    // déclaration autre variables
    String indice="";
    int choix=0;

    // Ajout JFrame, JPanel, JButton et JLabel
    Fenetre fenetre = new Fenetre();

    JPanel panPrincipal = new JPanel();
    JPanel panChoixNombreHaut = new JPanel();

    JPanel panJeu = new JPanel();
    JPanel panchoixOrdi = new JPanel();
    JPanel panIndice = new JPanel();

    JPanel panFinJeu = new JPanel();
    JPanel panBoutonFinJeu = new JPanel();

    JButton boutonValiderNombre = new JButton("Valider");
    JButton boutonIndiceMoins = new JButton("-");
    JButton boutonIndicePlus = new JButton("+");
    JButton boutonIndiceEgal = new JButton("=");

    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    JLabel labPrincipal = new JLabel("Choisissez un nombre entre "+intervalMin+" et "+intervalMax +" :");
    JLabel labChoixOrdi = new JLabel();
    JLabel labFinJeu = new JLabel("Fin de jeu");

    // ajout champs text formaté Int
    JFormattedTextField champText = new JFormattedTextField(NumberFormat.getIntegerInstance());


    public RechercheDeffenseur() {

        // Initialisation de la fenêtre
        fenetre.setSize(480,120);
        fenetre.setContentPane(panPrincipal);

        champText.setPreferredSize(new Dimension(40,40));

        panPrincipal.setLayout(new BorderLayout());
        panJeu.setLayout(new BorderLayout());
        panFinJeu.setLayout(new BorderLayout());

        panPrincipal.add(panChoixNombreHaut, BorderLayout.CENTER);
        panChoixNombreHaut.add(labPrincipal);
        panChoixNombreHaut.add(champText);
        panPrincipal.add(boutonValiderNombre, BorderLayout.SOUTH);
        panJeu.add(panchoixOrdi,BorderLayout.CENTER);
        panJeu.add(panIndice, BorderLayout.SOUTH);
        panchoixOrdi.add(labChoixOrdi);
        panIndice.add(boutonIndicePlus);
        panIndice.add(boutonIndiceMoins);
        panIndice.add(boutonIndiceEgal);
        panFinJeu.add(labFinJeu, BorderLayout.CENTER);
        panFinJeu.add(panBoutonFinJeu, BorderLayout.SOUTH);
        panBoutonFinJeu.add(boutonRejouer);
        panBoutonFinJeu.add(boutonChanger);
        panBoutonFinJeu.add(boutonQuiter);

        boutonValiderNombre.addActionListener(new ValiderNombre());
        boutonIndicePlus.addActionListener(new IndicePlus());
        boutonIndiceMoins.addActionListener(new IndiceMoins());
        boutonIndiceEgal.addActionListener(new IndiceEgal());
        boutonRejouer.addActionListener(new Rejouer());
        boutonChanger.addActionListener(new Changer());
        boutonQuiter.addActionListener(new Quiter());

        Font font = new Font("Georgia",Font.CENTER_BASELINE,15);
        labFinJeu.setFont(font);



        fenetre.setVisible(true);












        /*String indice = "";
        int choix = 0;
        String solution = "";


        choix = (intervalMax - intervalMin) / 2 + intervalMin;

        Scanner sc = new Scanner(System.in);

        System.out.println("Choisissez un nombre entre " + intervalMin + " et " + intervalMax + " :");
        solution = sc.nextLine();

        if (!indice.equals("=")) {

                if (indice.equals("+")) {
                    intervalMin = choix;
                    choix = (intervalMax - intervalMin) / 2 + intervalMin;
                } else if (indice.equals("-")) {
                    intervalMax = choix;
                    choix = (intervalMax - intervalMin) / 2 + intervalMin;
                }

                labChoixOrdi.setText("L'ordinateur propose " + choix + " comme solution.");
                indice = "+";

            }


        System.out.println("Je suis un ordinateur fou et je résous toutes les solutions que je veux hahahaaaa!");*/


    }
    class ValiderNombre implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            choix = Integer.parseInt(champText.getText());
            if(modeDev){
                System.out.println(choix);
            }
            fenetre.setContentPane(panJeu);
            fenetre.setVisible(true);

            choix = (intervalMax - intervalMin) / 2 + intervalMin;
            labChoixOrdi.setText("L'ordinateur propose " + choix + " comme solution.");

        }
    }
    class IndicePlus implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            intervalMin = choix;
            choix = (intervalMax - intervalMin) / 2 + intervalMin;
            labChoixOrdi.setText("L'ordinateur propose " + choix + " comme solution.");
        }
    }
    class IndiceMoins implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            intervalMax = choix;
            choix = (intervalMax - intervalMin) / 2 + intervalMin;
            labChoixOrdi.setText("L'ordinateur propose " + choix + " comme solution.");
        }
    }
    class IndiceEgal implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setContentPane(panFinJeu);
            labFinJeu.setText("J'ai trouvé tu as plus difficile ?!");
            fenetre.setVisible(true);
        }
    }
    class Rejouer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            RechercheDeffenseur recherche = new RechercheDeffenseur();
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
