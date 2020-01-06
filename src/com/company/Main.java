package com.company;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame {
    public static String gameName = "NinjaTurtles :: Game";
    public static int menu = 1;
    public static int play = 2;
    public static int game = 0;

    public Main(String name) throws SlickException {
        super(gameName);
        // new AppGameContainer(new WindowGame(), 620, 802, false).start();

    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new WindowGame(menu));
        this.addState(new Partie(play,2));
        this.enterState(menu);
    }

    public static void main(String[] args) {
        AppGameContainer app;
        try {
            app = new AppGameContainer(new Main(gameName));
            app.setDisplayMode(620, 802, false);
            app.setTargetFrameRate(60);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

}
