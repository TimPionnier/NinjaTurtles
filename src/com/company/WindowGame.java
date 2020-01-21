package com.company;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class WindowGame extends BasicGameState {

    private static int nbrJoueur;

    public static int getNbrJoueur() {
        return nbrJoueur;
    }

    private Image backGroundNinjaTurtles;
    private GameContainer gc;
    //private Image background;
    private Image playButton;
    private Image two;
    private Image three;
    private Image four;
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
        backGroundNinjaTurtles = new Image("map/ninjaTurtlesBackground.jpg");
        //background = new Image("map/background.jpeg");
        two = new Image("map/btnTwo.png");
        three = new Image("map/btnThree.png");
        four = new Image("map/btnFour.png");
        Music mixtape = new Music("sound/mixtape.ogg");
        //mixtape.loop();

    }


    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            gc.exit();
        }

        switch (key){
            case Input.KEY_2:
                nbrJoueur = 2;
                break;
            case  Input.KEY_3:
                nbrJoueur = 3;
                break;
            case Input.KEY_4:
                nbrJoueur = 4;
                break;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //g.scale(Display.getWidth()/620, Display.getHeight()/802);
        g.setColor(Color.white);
        g.drawImage(backGroundNinjaTurtles, 0, 0);
        //g.drawImage(background, 0, 0);
        g.drawImage(two, 80, 450);
        g.drawImage(three, 250, 450);
        g.drawImage(four, 420, 450);
        g.drawString(mouse,125,50);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;
       // System.out.println("nbrJoueur = " + nbrJoueur);

        if ((xpos>250 && xpos<400) && (ypos<350 && ypos>295)){
            three = new Image("map/btnThreeClicked.png");
            if (input.isMouseButtonDown(0)) {
                this.nbrJoueur = 3;
                //Main.setNbrJoueur(2);
                Partie.setNbrJoueur(3);
                sbg.enterState(2);
            }
        }
        if ((xpos>420 && xpos<570) && (ypos<350 && ypos>295)){
            four = new Image("map/btnFourClicked.png");
            if (input.isMouseButtonDown(0)) {
                this.nbrJoueur = 4;
                Partie.setNbrJoueur(4);
               // Main.setNbrJoueur(4);
                sbg.enterState(2);
            }
        }
        if ((xpos>80 && xpos<230) && (ypos<350 && ypos>295)){
            two = new Image("map/btnTwoClicked.png");
            if (input.isMouseButtonDown(0)) {
                this.nbrJoueur = 2;
               // Main.setNbrJoueur(2);
                Partie.setNbrJoueur(2);
                sbg.enterState(2);
            }
        }
        if ((xpos<250 || xpos>400) || (ypos<295 || ypos>350)){
            three = new Image("map/btnThree.png");
        }
        if ((xpos>570 || xpos<420) || (ypos<295 || ypos>350)){
            four = new Image("map/btnFour.png");
        }
        if ((xpos>230 || xpos<80) || (ypos<295 || ypos>350)){
            two = new Image("map/btnTwo.png");
        }

        if(getID() == 2 ) {
            //background = null;
            //Partie.askNbrJoueur(key);
        }

    }

    @Override
    public int getID() {
        return 1;
    }

}