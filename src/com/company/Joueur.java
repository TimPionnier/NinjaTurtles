package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Joueur {
    private static Character[] main = new Character[5];
    private Deck deck = new Deck();
    private static Character[] defausse = new Character[0];
    private int[] position;
    private char direction = 'N';
    private char numJoueur;
    private char instruction;

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

    public void setDirection(char nvllDirection) {this.direction = nvllDirection;}

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

    public void addInstruction(int nbrInstructions) {
        if (0 < nbrInstructions && nbrInstructions < 6) {

            for (int i = 0; i < nbrInstructions; i++) {
                this.instruction = ' ';//recuperer choix joueur: cartebleu, jaune, etc

                if (Deck.getMain().contains(instruction)) {
                    Deck.getFileInstruction().add(instruction);

                } else if (!(Deck.getMain().contains(instruction))) {
                    System.out.println("Tu ne possedes pas cette carte petit filou.");

                } else {
                    System.out.println("autre error dsl mon bro");
                }
            }
        }

    }

    public void executerFile () {
        this.instruction = Deck.getFileInstruction().getFirst();

        if (instruction == 'B') {
            if ((direction == 'N') ) {
                //positionY --
            } else if (direction == 'E') {
                //positionY ++
            } else if (direction == 'S') {
                //positionY ++
            } else if (direction == 'O') {
                //positionX --
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
