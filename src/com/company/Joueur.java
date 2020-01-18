package com.company;

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

    public void updateJoueur(Plateau plateau) {
        for (int i = 0; i < plateau.getCases().size(); i++) {
            if (plateau.getCase(i).getEtat() == this.numJoueur){
                plateau.getCase(i).setEtat(' ');
            }
            if ((plateau.getCase(i).getPosition(0) == this.position[0] && plateau.getCase(i).getPosition(1) == this.position[1])) {
                plateau.getCase(i).setEtat(this.numJoueur);
            }
        }
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

    public void addInstruction(Character instruction) {
        this.instructions.add(instruction);
    }

    /*public void executerFile () {
        this.instruction = Deck.getFileInstruction().getFirst();

        if (instruction == 'B') {
            if ((direction == 'N') ) {
                this.position[1]--;
            } else if (direction == 'E') {
                this.position[0]++;
            } else if (direction == 'S') {
                this.position[1]++;
            } else if (direction == 'O') {
                this.position[0]--;
            }

        } else if (instruction == 'J') {
            if (direction == 'N') {
                setDirection('O');
            } else if (direction == 'E') {
                setDirection('N');
            } else if (direction == 'S') {
                setDirection('E');
            } else if (direction == 'O') {
                setDirection('S');
            }
        } else if (instruction == 'V') {
            if (direction == 'N') {
                setDirection('E');
            } else if (direction == 'E') {
                setDirection('S');
            } else if (direction == 'S') {
                setDirection('O');
            } else if (direction == 'O') {
                setDirection('N');
            }
        } else if (instruction == 'L') {
            if (direction == 'N') {
                //useLaser('N');
            } else if (direction == 'E') {
                //useLaser('E');
            } else if (direction == 'S') {
                //useLaser('S');
            } else if (direction == 'O') {
                //useLaser('O');
            }
        }
    }
*/
    public void buildMur(char typeMur, int i, int j) {

        if (i < 8 && j < 8) {
            int k = i + (j - 1) * 8; //position dans la liste cases
            if (typeMur == 'G' || typeMur == 'P') {
                Plateau.getCase(k).setEtat(typeMur);
            } else {
                System.out.println("erreur entree type mur");
            }
        } else {
            System.out.println("erreur entree des positions du mur");
        }


    }
}
