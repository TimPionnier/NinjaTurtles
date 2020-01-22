package com.company;

import com.company.Tours.AddToProgram;
import com.company.Tours.BuildWall;
import com.company.Tours.ExecProgram;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame {
    private static String gameName = "NinjaTurtles :: Game";
    private static int menuState = 1;
    private static int playState = 2;
    private static int addToProgramState = 3;
    private static int execProgramState = 4;
    private static int buildWallState = 5;
    private static int winnerState = 6;
    private static int game = 0;

    public Main(String name) throws SlickException {
        super(gameName);
        // new AppGameContainer(new WindowGame(), 620, 802, false).start();

    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        WindowGame menu = new WindowGame(menuState);
        //Partie partie = new Partie(playState, 4);
        Partie partie = new Partie(playState);
        AddToProgram addToProgram = new AddToProgram(addToProgramState);
        ExecProgram execProgram = new ExecProgram(execProgramState);
        BuildWall buildWall = new BuildWall(buildWallState);
        Winner winner = new Winner(winnerState);

        partie.setAddToProgram(addToProgram);
        partie.setBuildWall(buildWall);
        partie.setExecProgram(execProgram);
        partie.setWinner(winner);

        this.addState(menu);
        this.addState(partie);
        this.addState(addToProgram);
        this.addState(execProgram);
        this.addState(buildWall);
        this.addState(winner);
        this.enterState(menuState);
    }

    public static void main(String[] args) {
        AppGameContainer app;
        try {
            app = new AppGameContainer(new Main(gameName));
            app.setDisplayMode(620, 802, false);
            app.setTargetFrameRate(60);
            app.start();
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

}
