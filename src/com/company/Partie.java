package com.company;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Partie  extends BasicGameState {
    private GameContainer gc;
    private Image background;
    String mouse = "No input yet!";
    private Image dammier;


    public Partie(int i) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;
        //AppGameContainer gameContainer = (AppGameContainer) gc; // function resizing the window, do not work
        //gameContainer.setDisplayMode(1100, 620, false);
        background = new Image("map/background.jpeg");
        dammier = new Image("map/dammier.png");
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //g.drawImage(background, 0, 0);
        g.drawImage(dammier, 150, 241);
        g.drawString(mouse,150,50);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;
    }

    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            gc.exit();
        }
    }

    public int getID() {
        return 2;
    }
}
