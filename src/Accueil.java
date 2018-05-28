import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame {
    // Création de la première page du jeu

    // Création des conteneurs un par jeu
    JPanel panPrincipal = new JPanel();
    JPanel panRecherche = new JPanel();
    JPanel panMastermind = new JPanel();

    // Création des Labels de chaque panel
    JLabel labRecherche = new JLabel("La Recherche +/-");
    JLabel labMastermind = new JLabel("Le MasterMind");

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
        this.setSize(480,560);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        panPrincipal.setLayout(new GridLayout(2,1));

        // ajout des panels seondaires au principal
        panPrincipal.add(panRecherche);
        panPrincipal.add(panMastermind);

        // Déclaration du contentPanel
        this.setContentPane(panPrincipal);
        panRecherche.add(labRecherche);
        panMastermind.add(labMastermind);
        panRecherche.add(boutonRechercheChallenger);
        panRecherche.add(boutonRechercheDefender);
        panRecherche.add(boutonRechercheDuel);
        panMastermind.add(boutonMastermindChallenger);
        panMastermind.add(boutonMastermindDefender);
        panMastermind.add(boutonMastermindDuel);


    }


}
