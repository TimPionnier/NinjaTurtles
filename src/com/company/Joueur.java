package com.company;

import com.company.Tours.Tour;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Joueur {
    private Deck deck = new Deck();

    private int[] position;
    private char direction = 'S';
    private char numJoueur;



    private ArrayDeque<Character> instructions = new ArrayDeque<>();

    public Joueur(int[] position, char numJoueur) {
        this.numJoueur = numJoueur;
        this.position = position;
    }

    /*public void updateJoueur(Plateau plateau) {
        for (int i = 0; i < plateau.getCases().size(); i++) {
            if (plateau.getCase(i).getEtat() == this.numJoueur){
                plateau.getCase(i).setEtat(' ');
            }
            if ((plateau.getCase(i).getPosition(0) == this.position[0] && plateau.getCase(i).getPosition(1) == this.position[1])) {
                plateau.getCase(i).setEtat(this.numJoueur);
            }
        }
    }*/

    public void updateJoueur(Plateau plateau){
        plateau.getCase(this.position[0],this.position[1]).setEtat(this.numJoueur);
       // Cartes.updateDirectionImage();
        //System.out.println("update direction" + Partie.getCurrentPlayer());
    }

    public void setDirection(char nvllDirection) {this.direction = nvllDirection;}

    public char getDirection() { return this.direction; }

    public int getPosition(int axis) {
        return this.position[axis];
    }

    public char getNumJoueur() {
        return this.numJoueur;
    }

    public void setPosition(int axis, int position) {
        this.position[axis] = position;
    }

    public void setNumJoueur(char numJoueur) {
        this.numJoueur = numJoueur;
    }

    public Deck getDeck() {
        return deck;
    }

    public Case getFrontCase(Plateau plateau){
        Case front;
        switch (this.direction){
            case 'N':
                front = plateau.getCase(this.position[0]-1,this.position[1]);
                break;
            case 'E':
                front = plateau.getCase(this.position[0],this.position[1]+1);
                break;
            case 'O':
                front = plateau.getCase(this.position[0],this.position[1]-1);
                break;
            case 'S':
                front = plateau.getCase(this.position[0]+1,this.position[1]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.direction);
        }
        return front;
    }

    public void returnStart() {
        //ajouter tous les cas en fonction du nbr de joueur
        switch (this.numJoueur) {
            case '1':
                this.position = new int[]{0, 1};
                break;
            case '2':
                this.position = new int[]{0, 5};
                break;
            case '3':

        }

        this.direction = 'S';
    }
}
