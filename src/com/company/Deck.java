package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Character> pioche = new ArrayList<>();
    private ArrayList<Character> murs = new ArrayList<>();
    private ArrayList<Character> defausse = new ArrayList<>();
    private static ArrayList<Character> main = new ArrayList<>();
    private static ArrayDeque<Character> fileInstruction = new ArrayDeque<>();



    public Deck() {
        //Remplissage de la pioche
        for (int i=0;i<18;i++){
            this.pioche.add('B'); //Carte Bleue
        }
        for (int i=0;i<8;i++){
            this.pioche.add('J'); //Carte Jaune
            this.pioche.add('V'); //Carte Violette
        }
        for (int i=0;i<3;i++){
            this.pioche.add('L'); //Carte Laser
            this.murs.add('P');   //Mur de pierre
        }
        for (int i=0;i<2;i++){
            this.murs.add('G');   //Mur de glace
        }

        //Mélange des cartes
        this.pioche = shuffle(this.pioche);

        //Distribution main
        for (int i=0;i<5;i++){
            this.main.add(this.pioche.get(0));
            this.pioche.remove(0);
        }
    }

    public static ArrayList<Character> getMain() {
        return main;
    }

    public char getCarteMain(int i){
        return this.main.get(i);
    }

    public static ArrayDeque<Character> getFileInstruction() {
        return fileInstruction;
    }

    public int getNbrMurs(){
        return this.murs.size();
    }

    public static ArrayList<Character> shuffle (ArrayList<Character> liste){
        Random rand = new Random();
        for (int i=0;i<liste.size();i++){
            int randomIndex = rand.nextInt(liste.size());
            Character temp = liste.get(randomIndex);
            liste.set(randomIndex,liste.get(i));
            liste.set(i,temp);
        }
        return liste;
    }
}
