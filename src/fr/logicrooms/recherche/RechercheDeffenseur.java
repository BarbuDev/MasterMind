package fr.logicrooms.recherche;
import fr.ligicrooms.main.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Scanner;

public class RechercheDeffenseur {

    // récupération des variables dans config.properties
    int intervalMin = 0;
    int intervalMax = 99;

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

    JButton boutonValiderNombre = new JButton("Valider");
    JButton boutonIndiceMoins = new JButton("-");
    JButton boutonIndicePlus = new JButton("+");
    JButton boutonIndiceEgal = new JButton("=");

    JLabel labPrincipal = new JLabel("Choisissez un nombre entre "+intervalMin+" et "+intervalMax +" :");
    JLabel labChoixOrdi = new JLabel();

    // ajout champs text formaté Int
    JFormattedTextField champText = new JFormattedTextField(NumberFormat.getIntegerInstance());


    public RechercheDeffenseur() {

        // Initialisation de la fenêtre
        fenetre.setSize(480,120);
        fenetre.setContentPane(panPrincipal);

        champText.setPreferredSize(new Dimension(40,40));

        panPrincipal.setLayout(new BorderLayout());
        panJeu.setLayout(new BorderLayout());

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

        boutonValiderNombre.addActionListener(new ValiderNombre());
        boutonIndicePlus.addActionListener(new IndicePlus());
        boutonIndiceMoins.addActionListener(new IndiceMoins());
        boutonIndiceEgal.addActionListener(new IndiceEgal());


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

        }
    }

}
