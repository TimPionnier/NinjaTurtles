package com.company;

import java.util.ArrayDeque;

public class Joueur {
    private static Character[] main = new Character[5];
    private Deck deck = new Deck();
    private static Character[] defausse = new Character[0];
    private int[] position;
    private char numJoueur;

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

    public int[] getPosition() {
        return this.position;
    }

    public char getNumJoueur() {
        return this.numJoueur;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setNumJoueur(char numJoueur) {
        this.numJoueur = numJoueur;
    }

    public Deck getDeck() {
        return deck;
    }
}
