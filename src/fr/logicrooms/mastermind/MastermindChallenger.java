package fr.logicrooms.mastermind;

import fr.logicrooms.main.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Joue le jeu de mastermind en mode Challenger<br>
 *     Algorithme utilisé : Knuth (recherche de sous chaine)
 *
 * @author BarbuDev
 * @version 1.0
 */
public class MastermindChallenger{

    // Ajout du logger
    public static final Logger logger = Logger.getLogger(MastermindChallenger.class);

    // Variables à récupérées dans config.properties
    private CallConfig config = new CallConfig();
    private Boolean modeDev = config.CallConfigBoolean("modeDev");
    private int colorNumbers = config.CallConfig("couleurMastermind");
    int life = config.CallConfig("vie");

    // autres variables
    int choix = 0;
    String strSolution = "";
    int combinaisonAleatoire[] = new int[4];
    int score=0;
    String historique = "";
    int tabPropositionJoueur = 0;

    // création de la fenetre
    Fenetre fenetre = new Fenetre();

    // création des panels
    JPanel mainPan = new JPanel();
    JPanel gamePan = new JPanel();
    JPanel entriesPan = new JPanel();
    JPanel endGamePan = new JPanel();
    JPanel endGameButtonPan = new JPanel();

    // création des labels
    JLabel mainLab = new JLabel("Mastermind");
    JLabel solutionLab = new JLabel("Trouvé la combinaison de 4 chiffres compris entre 0 et " + (colorNumbers -1));
    JLabel entriesLab = new JLabel("Faites votre proposition :");
    JLabel endGame = new JLabel("Fin de jeu par ici");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Valider");
    JButton boutonRejouer = new JButton("Rejouer");
    JButton boutonChanger = new JButton("Changer de jeu");
    JButton boutonQuiter = new JButton("Quiter");

    // ajout champ de text
    JTextField champText = new JTextField();

    // objet listener
    Jouer jouer = new Jouer();

    JOptionPane jOP = new JOptionPane();


    /**
     * Construit l'IHM et selectionne la combinaison aléatoire
     */
    public MastermindChallenger(){
        // création de la fenetre
        fenetre.setSize(580, 350);

        // parametrage du champ text
        champText.setPreferredSize(new Dimension(200, 50));

        // ajout des layout
        mainPan.setLayout(new BorderLayout());
        gamePan.setLayout(new BorderLayout());
        entriesPan.setLayout(new BorderLayout());
        endGamePan.setLayout(new BorderLayout());

        // ajout des panels et autre éléments
        fenetre.setContentPane(mainPan);
        mainPan.add(gamePan, BorderLayout.NORTH);
        mainPan.add(entriesPan, BorderLayout.SOUTH);
        gamePan.add(solutionLab, BorderLayout.CENTER);
        entriesPan.add(entriesLab, BorderLayout.WEST);
        entriesPan.add(champText,BorderLayout.CENTER);
        entriesPan.add(boutonValider, BorderLayout.EAST);
        endGamePan.add(endGame, BorderLayout.CENTER);
        endGamePan.add(endGameButtonPan, BorderLayout.SOUTH);
        endGameButtonPan.add(boutonRejouer);
        endGameButtonPan.add(boutonChanger);
        endGameButtonPan.add(boutonQuiter);

        // Ajout des listeners
        boutonValider.addActionListener(jouer);
        boutonRejouer.addActionListener(new Rejouer());
        boutonChanger.addActionListener(new Changer());
        boutonQuiter.addActionListener(new Quiter());

        // affichage de la fenetre
        fenetre.setVisible(true);

        // choix de la combinaison aléatoire
        for (int i = 0; i < 4; i++) {
            //combinaisonAleatoire[i] = 9;
            combinaisonAleatoire[i] = (int) (Math.random() * (0-colorNumbers)*-1);
        }

        if(modeDev){
            // affichage de la combinaison
            for (int i = 0; i < 4; i++) {
                System.out.print(combinaisonAleatoire[i]);
            }
        }
    }

    /**
     * Lance le jeu et execute l'algorithme
     */
    class Jouer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if (Integer.parseInt(champText.getText())<0){
                    champText.setText("a");

                }else if (Integer.parseInt(champText.getText())>((colorNumbers-1)*1111)){
                    champText.setText("a");
                }

                tabPropositionJoueur = Integer.parseInt(champText.getText());
                CalculScore calcul = new CalculScore();
                score = calcul.calculer(tabPropositionJoueur,combinaisonAleatoire,colorNumbers);

                // sortie de la boucle de jeu lorsque la combinaison est trouvée
                if(score == 40){
                    endGame.setText("Bravo vous avez GAGNEZ !");
                    logger.info("Le joueur gagne il lui reste " + life + " vie(s)");
                    fenetre.setContentPane(endGamePan);
                    fenetre.setSize(580, 200);
                    fenetre.setVisible(true);
                }
                if (life <= 1){

                    for (int i = 0; i < 4; i++) {
                        strSolution = strSolution + combinaisonAleatoire[i];
                    }
                    endGame.setText("Perdu ! La solution était : " + strSolution);
                    logger.info("Le joueur perd");
                    fenetre.setContentPane(endGamePan);
                    fenetre.setVisible(true);
                }

                if(tabPropositionJoueur == 0){
                    historique = historique + "0000";
                }else if(tabPropositionJoueur <=9){
                    historique = historique + "000" + tabPropositionJoueur;
                }else if(tabPropositionJoueur <=99){
                    historique = historique + "00" + tabPropositionJoueur;
                }else if(tabPropositionJoueur<=999){
                    historique = historique + "0" + tabPropositionJoueur;
                }else{
                    historique = historique + tabPropositionJoueur;
                }

                life--;
                historique = historique + " " + score/10 + " bien placé(s)" + score%10 + " mal placé(s). Ils vous reste " + life + " vie(s)" + "<br>";


                solutionLab.setText("<html>" + historique + "</html>");
                champText.setText("");

            }catch (Exception z){
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à 4 chiffres compris entre 0 et " + (colorNumbers-1), "Attention", JOptionPane.WARNING_MESSAGE);
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
            MastermindChallenger mastermind = new MastermindChallenger();
        }
    }
    class Changer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.setVisible(false);
            logger.trace("Le joueur choisi de changer de jeu");
            Accueil accueil = new Accueil();
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
