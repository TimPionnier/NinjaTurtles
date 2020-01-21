package com.company;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Winner  extends BasicGameState {

    private static int ID;

    private Joueur joueur;

    private static ArrayList<Joueur> winners = new ArrayList<>();
    private static ArrayList<Integer> winnersInt = new ArrayList<>();

    private static String displayWinners = "";

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Winner(int state) {
        ID = state;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        int x = 150;
        int y = 200;
        char current;
        graphics.drawString("Classement:",x,y);
        graphics.drawString(displayWinners,x,y+20);


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public static void addToWinners(Joueur joueur){
        winners.add(joueur);
        int numJoueur = joueur.getNumJoueur();
        switch (numJoueur){
            case 49:
                numJoueur = 1;
                break;
            case 50:
                numJoueur = 2;
                break;
            case 51:
                numJoueur = 3;
                break;
            case 52:
                numJoueur = 4;
                break;
        }
        winnersInt.add(numJoueur);
        Plateau.getCase(joueur.getPosition(0),joueur.getPosition(1)).setEtat(' ');
    }

    public static void updateWinnerList(){
        for (int j = 0; j < winners.size(); j++) {
            displayWinners += ((j+1)+". Joueur " + winners.get(j).getNumJoueur() + "\n");
        }
    }

    public static ArrayList<Joueur> getWinners() {
        return winners;
    }

    public static ArrayList<Integer> getWinnersInt() {
        return winnersInt;
    }
}
