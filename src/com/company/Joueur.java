package com.company;

import java.util.ArrayDeque;

public class Joueur  {
    public Joueur(Object tortue, int[] position, ArrayDeque<Character> fileInstructions, char[] main, char[] deck, char[] defausse) {
        this.tortue = tortue;
        this.position = position;
        this.fileInstructions = new ArrayDeque<Character>();
        this.main = new char[5];
        this.deck = new char[52];
        this.defausse = new char[52];
    }

    public Object tortue;
    public int[] position;
    public ArrayDeque<Character> fileInstructions;
    public char[] main;
    public char[] deck;
    public char[] defausse;


}
