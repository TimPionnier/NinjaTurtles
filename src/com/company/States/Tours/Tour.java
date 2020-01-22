package com.company.States.Tours;

import com.company.Objects.Cartes;
import com.company.Objects.Plateau.Plateau;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.HashMap;

public abstract class Tour extends BasicGameState {
    protected Image btnEnd;
    protected GameContainer gameContainer;
    protected StateBasedGame stateBasedGame;
    protected Image dammier;
    protected Plateau plateau;
    protected HashMap<Character, Image> list_cartes;
    private int state;


    public Tour(int state) {
        this.state = state;
    }

    @Override
    public int getID() {
        return this.state;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stateBasedGame) throws SlickException {
        dammier = new Image("map/dammier.png");
        btnEnd = new Image("map/btnEnd.png");
        this.gameContainer = gc;
        this.stateBasedGame = stateBasedGame;
        this.list_cartes = Cartes.getCartes();
    }
}
