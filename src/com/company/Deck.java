package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Character> pioche = new ArrayList<>();
    private ArrayList<Character> murs = new ArrayList<>();
    private ArrayList<Character> defausse = new ArrayList<>();
    private ArrayList<Character> main = new ArrayList<>();
    private ArrayDeque<Character> fileInstruction = new ArrayDeque<>();


    public Deck() {
        //Remplissage de la pioche
        for (int i = 0; i < 18; i++) {
            this.pioche.add('B'); //Carte Bleue
        }
        for (int i = 0; i < 8; i++) {
            this.pioche.add('J'); //Carte Jaune
            this.pioche.add('V'); //Carte Violette
        }
        for (int i = 0; i < 3; i++) {
            this.pioche.add('L'); //Carte Laser
            this.murs.add('P');   //Mur de pierre
        }
        for (int i = 0; i < 2; i++) {
            this.murs.add('G');   //Mur de glace
        }

        //Mélange des cartes
        this.pioche = shuffle(this.pioche);

        //Distribution main
        this.remplirMain();
    }

    public static ArrayList<Character> shuffle(ArrayList<Character> liste) {
        Random rand = new Random();
        for (int i = 0; i < liste.size(); i++) {
            int randomIndex = rand.nextInt(liste.size());
            Character temp = liste.get(randomIndex);
            liste.set(randomIndex, liste.get(i));
            liste.set(i, temp);
        }
        return liste;
    }

    public ArrayList<Character> getMurs() {
        return murs;
    }

    public char getMur(int i) {
        return this.murs.get(i);
    }

    public ArrayList<Character> getMain() {
        return main;
    }

    public char getCarteMain(int i) {
        return this.main.get(i);
    }

    public void suppCarteMain(int i) {
        this.main.remove(i);
        System.out.println("Carte supprimé");
        System.out.print("Main : ");
        for (Character carte :
                this.main) {
            System.out.print(carte + " ");
        }
        System.out.println();
    }

    public void suppMur(int i) {
        this.murs.remove(i);
    }

    public ArrayDeque<Character> getFileInstruction() {
        return this.fileInstruction;
    }

    public void addFileInstruction(int i) {
        this.fileInstruction.add(this.main.get(i));
        System.out.print("File d'instruction : ");
        for (Character carte :
                this.fileInstruction) {
            System.out.print(carte + " ");
        }
    }

    public void remplirMain() {
        while (this.main.size() < 5) {
            this.main.add(this.pioche.get(0));
            this.pioche.remove(0);
        }
    }

    public void remplirDefausse() {
        while (this.main.size() > 0) {
            this.defausse.add(this.main.get(0));
            this.main.remove(0);
        }
    }
}
