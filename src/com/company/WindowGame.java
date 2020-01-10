package com.company;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class WindowGame extends BasicGameState {

    private GameContainer gc;
    private Image background;
    private Image playButton;
    int xpos = Mouse.getX();
    int ypos = Mouse.getY();
    String mouse = "No input yet!";
    //private int menu = 1;

    public WindowGame(int i) {

    }

    /*public static void main(String[] args) throws SlickException {
        //new AppGameContainer(new WindowGame(), 620, 802, false).start();
    }*/

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;

        background = new Image("map/background.jpeg");
        playButton = new Image("map/boutonPlay.png");
        Music mixtape = new Music("sound/mixtape.ogg");
        mixtape.loop();

    }


    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            gc.exit();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //g.scale(Display.getWidth()/620, Display.getHeight()/802);
        g.drawImage(background, 0, 0);
        g.drawImage(playButton, 250, 450);
        g.drawString(mouse,125,50);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;

        if ((xpos>260 && xpos<380) && (ypos<340 && ypos>297)){
            //if (input.isMouseButtonDown(0)) {
            playButton = new Image("map/boutonPlayClicked.png");//}
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(2);
            }
        }
        if ((xpos<260 || xpos>380) || (ypos<297 || ypos>340)){
            playButton = new Image("map/boutonPlay.png");

        }

        if(getID() == 2 ) {
            background = null;
            //Partie.askNbrJoueur(key);
        }

    }

    @Override
    public int getID() {
        return 1;
    }

}