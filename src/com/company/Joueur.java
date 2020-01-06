package com.company;

import java.util.ArrayDeque;

public class Joueur {
    private static Character[] main = new Character[5];
    private static Character[] deck = new Character[37];
    private static Character[] defausse = new Character[0];
    private static int[] position;
    private static char numJoueur;

    public Joueur(int[] position, char numJoueur) {
        this.numJoueur = numJoueur;
        this.position = position;
    }

    public void updateJoueur(Plateau plateau) {
        for (int i = 0; i < plateau.getCases().size(); i++) {
            if ((plateau.getCase(i).getPosition(0) == this.position[0] && plateau.getCase(i).getPosition(1) == this.position[1])) {
                plateau.getCase(i).setEtat(this.numJoueur);
            }
        }
    }

    public static int[] getPosition() {
        return position;
    }

    public static char getNumJoueur() {
        return numJoueur;
    }

    public static void setPosition(int[] position) {
        Joueur.position = position;
    }

    public static void setNumJoueur(char numJoueur) {
        Joueur.numJoueur = numJoueur;
    }
}
