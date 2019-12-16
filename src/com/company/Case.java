package com.company;

public class Case {
    private int[] position = new int[2];
    private char etat;

    public Case(int i, int j, char etat) {
        this.position[0] = i;
        this.position[1] = j;
        this.etat = etat;
    }


    public int getPosition(int i) {
        return position[i];
    }

    public char getEtat() {
        return etat;
    }

    public void setEtat(char etat) {
        this.etat = etat;
    }
}