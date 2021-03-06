package mastermind;


import main.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Joue le jeu de mastermind en mode Challenger<br>
 *     Algorithme utilisé : Knuth (Jeu.recherche de sous chaine)
 *
 * @author BarbuDev
 * @version 1.0
 */
public class MastermindChallenger{

    // Ajout du logger
    public static final Logger logger = Logger.getLogger(MastermindChallenger.class);

    // Variables à récupérées dans config.properties
    private CallConfig config = new CallConfig();
    private int colorNumbers = config.CallConfig("couleurMastermind");
    int life = config.CallConfig("vie");
    int longueurCombinaison = config.CallConfig("nombreChiffreCombinaison");
    boolean modeDev = config.CallConfigBoolean("modeDev");

    // autres variables
    int choix = 0;
    String strSolution = "";
    int combinaisonAleatoire[] = new int[longueurCombinaison];
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
    JLabel solutionLab = new JLabel("Trouvé la combinaison de "+ longueurCombinaison +" chiffres compris entre 0 et " + (colorNumbers -1));
    JLabel entriesLab = new JLabel("Faites votre proposition :");

    // ajout du bouton pour valider la proposition
    JButton boutonValider = new JButton("Valider");

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
        endGamePan.add(endGameButtonPan, BorderLayout.SOUTH);

        // Ajout des listeners
        boutonValider.addActionListener(jouer);

        // affichage de la fenetre
        fenetre.setVisible(true);

        // choix de la combinaison aléatoire
        for (int i = 0; i < longueurCombinaison; i++) {
            //combinaisonAleatoire[i] = 9;
            combinaisonAleatoire[i] = (int) (Math.random() * (0-colorNumbers)*-1);
        }

        if(Main.modeDev){
            // affichage de la combinaison
            fenetre.setTitle(fenetre.getTitle()+" => Mode Développeur activé solution du joueur : ");
            for (int i = 0; i < longueurCombinaison; i++) {
                fenetre.setTitle(fenetre.getTitle()+combinaisonAleatoire[i]);
            }
        }else if(modeDev){
            // affichage de la combinaison
            fenetre.setTitle(fenetre.getTitle()+" => Mode Développeur activé solution du joueur : ");
            for (int i = 0; i < longueurCombinaison; i++) {
                fenetre.setTitle(fenetre.getTitle()+combinaisonAleatoire[i]);
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
                String z="";
                for(int i = 0; i < longueurCombinaison;i++){
                    z = z +"1";
                }
                if (Integer.parseInt(champText.getText())<0){
                    champText.setText("a");

                }else if (Integer.parseInt(champText.getText())>((colorNumbers-1)*Integer.parseInt(z))){
                    champText.setText("a");
                }

                tabPropositionJoueur = Integer.parseInt(champText.getText());
                CalculScore calcul = new CalculScore();
                score = calcul.calculer(tabPropositionJoueur,combinaisonAleatoire,colorNumbers,longueurCombinaison);

                // sortie de la boucle de jeu lorsque la combinaison est trouvée
                if(score == (longueurCombinaison*10)){
                    EndGame endGame = new EndGame();
                    endGame.finDeJeu("mastermindChallenger","Bravo vous avez GAGNEZ !");
                    logger.info("Le joueur gagne il lui reste " + life + " vie(s)");
                    fenetre.setVisible(false);
                }
                if (life <= 1){

                    for (int i = 0; i < longueurCombinaison; i++) {
                        strSolution = strSolution + combinaisonAleatoire[i];
                    }
                    EndGame endGame = new EndGame();
                    endGame.finDeJeu("mastermindChallenger","Perdu ! La solution était : " + strSolution);
                    logger.info("Le joueur perd");
                    fenetre.setVisible(false);
                }

                int traitementZero = tabPropositionJoueur;


                for(int i = 0; i < longueurCombinaison;i++){
                    if(traitementZero==0){
                        historique = historique + "0";
                    }
                    traitementZero = traitementZero/10;
                }
                if(tabPropositionJoueur != 0){
                    historique = historique + tabPropositionJoueur;
                }






                life--;
                historique = historique + " " + score/10 + " bien placé(s)" + score%10 + " mal placé(s). Ils vous reste " + life + " vie(s)" + "<br>";


                solutionLab.setText("<html>" + historique + "</html>");
                champText.setText("");

            }catch (Exception z){
                logger.warn("Mauvaise saisie du joueur");
                logger.warn(z);
                jOP.showMessageDialog(null, "Saisissez une combinaison à " + longueurCombinaison + " chiffres compris entre 0 et " + (colorNumbers-1), "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
