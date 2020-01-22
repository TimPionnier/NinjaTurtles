package com.company.Players;

import com.company.Objects.Plateau.Case;
import com.company.Objects.Plateau.Plateau;
import com.company.States.Partie;

public class Joueur {
    private Deck deck = new Deck();

    private int[] position;
    private char direction = 'S';
    private char numJoueur;


    public Joueur(int[] position, char numJoueur) {
        this.numJoueur = numJoueur;
        this.position = position;
    }

    //MAJ de l'état de la case occupée par le joueur (run à chaque itération de la méthode update de Partie)
    public void updateJoueur() {
        Plateau.getCase(this.position[0], this.position[1]).setEtat(this.numJoueur);
    }

    public char getDirection() {
        return this.direction;
    }

    public void setDirection(char nvllDirection) {
        this.direction = nvllDirection;
    }

    public int getPosition(int axis) {
        return this.position[axis];
    }

    public char getNumJoueur() {
        return this.numJoueur;
    }


    public void setPosition(int axis, int position) {
        this.position[axis] = position;
    }

    public Deck getDeck() {
        return deck;
    }


    public Case getFrontCase() {
        Case front;
        switch (this.direction) {
            case 'N':
                front = Plateau.getCase(this.position[0] - 1, this.position[1]);
                break;
            case 'E':
                front = Plateau.getCase(this.position[0], this.position[1] + 1);
                break;
            case 'O':
                front = Plateau.getCase(this.position[0], this.position[1] - 1);
                break;
            case 'S':
                front = Plateau.getCase(this.position[0] + 1, this.position[1]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.direction);
        }
        return front;
    }

    public void returnStart() {
        //ajouter tous les cas en fonction du nbr de joueur
        switch (Partie.getJoueurs().size()) {
            case 2:
                switch (this.numJoueur) {
                    case '1':
                        this.position = new int[]{0, 1};
                        break;
                    case '2':
                        this.position = new int[]{0, 5};
                        break;
                }
                break;

            case 3:
                switch (this.numJoueur) {
                    case '1':
                        this.position = new int[]{0, 0};
                        break;
                    case '2':
                        this.position = new int[]{0, 3};
                        break;
                    case '3':
                        this.position = new int[]{0, 6};
                        break;
                }
                break;

            case 4:
                switch (this.numJoueur) {
                    case '1':
                        this.position = new int[]{0, 0};
                        break;
                    case '2':
                        this.position = new int[]{0, 2};
                        break;
                    case '3':
                        this.position = new int[]{0, 5};
                        break;
                    case '4':
                        this.position = new int[]{0, 7};
                        break;

                }
                break;
        }


        this.direction = 'S';
    }
}
