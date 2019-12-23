package com.company;

import java.util.ArrayDeque;

public class Joueur  {
    private static Character[] main  = new Character[5];
    private static Character[] deck = new Character[37];
    private static Character[] defausse = new Character[0];
    private static int[] position;

    public Joueur(int[] position) {
        this.position = position;
    }
}
