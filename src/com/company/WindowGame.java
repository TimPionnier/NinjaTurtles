package com.company;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {

    private GameContainer container;
    private Image land;

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 640, 480, false).start();
    }

    public WindowGame() {
        super("Robot_Turtles :: windowGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        land = new Image("map/background.jpeg");

    }

    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
       //this.map.render(0, 0);
        g.scale(Display.getWidth()/640, Display.getHeight()/480);
        g.drawImage(land, 0, 0);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

}