package com.company;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Partie  extends BasicGameState {
    private GameContainer gc;
    private Image background;
    String mouse = "No input yet!";
    static String demande = "";
    public static int nbrJoueur;
    private Image dammier;
    public static Image woodBox;
    public static char[][] plateaux;


    public Partie() {
        Plateau plateau = new Plateau();
        plateau.setPlateau();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;
        //AppGameContainer gameContainer = (AppGameContainer) gc; // function resizing the window, do not work
        //gameContainer.setDisplayMode(1100, 620, false);
        background = new Image("map/background.jpeg");
       // woodBox = new Image("map/woodBox.png");
        dammier = new Image("map/dammier.png");
        dammier = new Image("map/dammier.png");
        dammier = new Image("map/dammier.png");
        dammier = new Image("map/dammier.png");
        dammier = new Image("map/dammier.png");
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //g.drawImage(background, 0, 0);
        g.drawImage(dammier, 150, 241);
        g.drawString(mouse,150,50);
        g.drawString(demande, 200, 700 );
        //g.drawImage(plateau,150,241);




        for (int i=0 ; i<=7 ; i++) {
            for (int j=0 ; j<=7 ; j++) {
                int x = 150;
                int y = 241;
                if (plateau[i][j] == 'C') {
                    g.drawImage(woodBox,x,y);
                }
                x += 40;
                y += 40;
            }
        }
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

    public static void askNbrJoueur(int key){
        boolean condition = true; // on empeche le changement du nombre de joueur
        demande = "A combien voulez vous jouer ?";
        if (condition && (Input.KEY_1 == key || Input.KEY_2 == key || Input.KEY_3 == key)) {
            Partie.nbrJoueur = key - 1;
            demande = "";
            condition = false;

        }
    }





    public int getID() {
        return 2;
    }
}
