package com.company.Tours;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Tour extends BasicGameState {

    private int state;

    protected GameContainer gameContainer;
    protected StateBasedGame stateBasedGame;
    protected Graphics g;

    protected String mouse = "";
    protected Image dammier;

    public Tour(int state) {
        this.state = state;
    }

    @Override
    public int getID() {
        return this.state;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        dammier = new Image("map/dammier.png");
        this.gameContainer = gameContainer;
        this.stateBasedGame = stateBasedGame;
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;

    }
}
