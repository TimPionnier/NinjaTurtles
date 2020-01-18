package com.company.Tours;

import com.company.Cartes;
import com.company.Partie;
import com.company.Plateau;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.HashMap;

public abstract class Tour extends BasicGameState {

    private int state;
    private HashMap<Character, Image> list_cartes;

    protected GameContainer gameContainer;
    protected StateBasedGame stateBasedGame;
    protected Graphics g;

    protected String mouse = "";
    protected Image dammier;
    protected Plateau plateau;

    public Tour(int state, Plateau plateau)
    {
        this.state = state;
        this.plateau = plateau;
    }

    @Override
    public int getID() {
        return this.state;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stateBasedGame) throws SlickException {
        dammier = new Image("map/dammier.png");
        this.gameContainer = gc;
        this.stateBasedGame = stateBasedGame;
        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();
    }

    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        //Affichage des éléments du plateau
        int x = 190;
        int y = 200;


        //Affichage des cases en fonction de leur état
        for (int i=0 ; i<64; i++) {
            if (this.plateau.getCase(i).getEtat() != ' ') { //Si la case n'est pas vide, il affiche l'image correspondant à l'état
                g.drawImage(this.list_cartes.get(this.plateau.getCase(i).getEtat()), x, y);
            }
            x += 40;
            if (i%8==0){
                x = 190 ;
                y+=40;
            }
        }
    }

}
