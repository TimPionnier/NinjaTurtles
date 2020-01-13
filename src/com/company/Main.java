package com.company;

import com.company.Tours.AddToProgram;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame {
    public static String gameName = "NinjaTurtles :: Game";
    public static int menuState = 1;
    public static int playState = 2;
    public static int addToProgramState = 3;
    public static int execProgram = 4;
    public static int buildWalls = 5;
    public static int game = 0;

    public Main(String name) throws SlickException {
        super(gameName);
        // new AppGameContainer(new WindowGame(), 620, 802, false).start();

    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        WindowGame menu = new WindowGame(menuState);
        Partie partie = new Partie(playState,2);
        AddToProgram addToProgram = new AddToProgram(addToProgramState);

        partie.setAddToProgram(addToProgram);

        this.addState(menu);
        this.addState(partie);
        this.addState(addToProgram);
        this.enterState(menuState);
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
