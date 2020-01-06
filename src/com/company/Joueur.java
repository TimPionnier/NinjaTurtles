package com.company;

import java.util.ArrayDeque;
import java.util.Random;

public class Joueur  {
    private static Character[] main  = new Character[5];
    private static Character[] deck = new Character[37];
    private static Character[] defausse = new Character[0];
    private static int[] position = new int[2];

    public Joueur(int[] position, Character[] deck, Character[] defausse, Character[] main) {
        this.position = position;
        this.deck = deck;
        this.defausse = defausse;
        this.main = main;
    }

    public int getPosition(int i) {
        return this.position[i];
    }

    public Character[] getMain() {
        return main;
    }

    public static void setMain(Character[] main) {
        Joueur.main = main;
    }

    public static void setDefausse(Character[] defausse) {
        Joueur.defausse = defausse;
    }

    public static Character[] setDeck(Character[] deck) {
        Joueur.deck = deck;
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<deck.length; i++) {
            int randomPosition = rgen.nextInt(deck.length);
            Character temp = deck[i];
            deck[i] = deck[randomPosition];
            deck[randomPosition] = temp;
        }

        return deck;
    }

    public void tourJoueur(Joueur joueur) {

    }
}
