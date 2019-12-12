package com.company;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {

    private GameContainer container;
    private Image background;
    private Image playButton;
    int xpos = Mouse.getX();
    int ypos = Mouse.getY();
    String mouse = "No input yet!";

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 620, 802, false).start();
    }



    public WindowGame() {
        super("Robot_Turtles :: Menu");

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        container.setTargetFrameRate(60);
        background = new Image("map/background.jpeg");
        playButton = new Image("map/boutonPlay.png");
        Music mixtape = new Music("sound/mixtape.ogg");
        mixtape.loop();

    }


    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        //g.scale(Display.getWidth()/620, Display.getHeight()/802);
        g.drawImage(background, 0, 0);
        g.drawImage(playButton, 250, 450);
        g.drawString(mouse,125,50);

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;

        if ((xpos>260 && xpos<380) && (ypos<340 && ypos>297)){
            //if (input.isMouseButtonDown(0)) {
                playButton = new Image("map/boutonPlayClicked.png");//}
        }
        if ((xpos<260 || xpos>380) || (ypos<297 || ypos>340)){
            playButton = new Image("map/boutonPlay.png");

        }

    }

}