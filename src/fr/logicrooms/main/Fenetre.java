package fr.logicrooms.main;

import javax.swing.*;

/**
 * Crée la base de l'IHM avec ses paramètres par défaut
 *
 * @author BarbuDev
 * @version 1.0
 */
public class Fenetre extends JFrame {

        public Fenetre(){
            this.setTitle("LogicRooms");
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(true);
        }
}
