package com.company;

import com.company.States.Menu;
import com.company.States.Partie;
import com.company.States.Tours.AddToProgram;
import com.company.States.Tours.BuildWall;
import com.company.States.Tours.ExecProgram;
import com.company.States.Winner;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame {
    private static String gameName = "NinjaTurtles :: Game";
    private static int menuState = 1;
    private static int playState = 2;
    private static int addToProgramState = 3;
    private static int execProgramState = 4;
    private static int buildWallState = 5;
    private static int winnerState = 6;

    public Main() {
        super(gameName);
    }

    public static void main(String[] args) {
        AppGameContainer app;
        try {
            app = new AppGameContainer(new Main());
            app.setDisplayMode(620, 802, false);
            app.setTargetFrameRate(60);
            app.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        Menu select = new Menu(menuState);
        Partie partie = new Partie(playState);
        AddToProgram addToProgram = new AddToProgram(addToProgramState);
        ExecProgram execProgram = new ExecProgram(execProgramState);
        BuildWall buildWall = new BuildWall(buildWallState);
        Winner winner = new Winner(winnerState);

        partie.setAddToProgram(addToProgram);
        partie.setBuildWall(buildWall);
        partie.setExecProgram(execProgram);
        partie.setWinner(winner);

        this.addState(select);
        this.addState(partie);
        this.addState(addToProgram);
        this.addState(execProgram);
        this.addState(buildWall);
        this.addState(winner);
        this.enterState(menuState);
    }

}
