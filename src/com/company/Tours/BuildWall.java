package com.company.Tours;

import com.company.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildWall extends Tour {
    int murX = -1;
    int murY = -1;
    char etatMur =' ';
    String murPos = " ";

    private Joueur joueur;
    private HashMap<Character, Image> list_cartes;
    private static ArrayList<Case> cases;

    public BuildWall(int state) throws SlickException{
        super(state);
        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();
    }


    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString(mouse,150,50);
        g.drawImage(dammier, 150, 200);
        g.drawString(murPos, 200, 100);
        g.drawImage(btnEnd, 250, 560);

        //Murs joueur
        int u = 20;
        int v = 620;
        for (int i=0 ; i<this.joueur.getDeck().getMurs().size(); i++) {
            g.drawImage(this.list_cartes.get(this.joueur.getDeck().getMur(i)), u, v);
            u += 120;
        }

        //Affichage des cases en fonction de leur état
        int x = 150;
        int y = 200;
        for (int i=0 ; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.plateau.getCase(i,j).getEtat() != ' ') { //Si la case n'est pas vide, il affiche l'image correspondant à l'état
                    g.drawImage(this.list_cartes.get(this.plateau.getCase(i,j).getEtat()), x, y);
                }
                x += 40;
            }
            x = 150 ;
            y+=40;
        }


        /*for (int i=0 ; i<this.joueur.getDeck().getMain().size(); i++){
            g.drawImage(this.list_cartes.get(this.joueur.getDeck().getCarteMain(i)),u ,v );
            u += 120;
        }*/
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();

        this.cases = this.plateau.getCases();

        mouse = "xposs: " + xpos + " ; ypos: " + ypos;

        if ((xpos >250 && xpos < 400) && (ypos < 240 && ypos > 187)) {
            btnEnd = new Image("map/btnEnd-clicked.png");
            if (input.isMouseButtonDown(0)) {
                if (this.joueur.getDeck().getMain().size() < 5) {
                    this.joueur.getDeck().remplirMain();
                }
                sbg.enterState(2);
                Partie.waitForClick();
            }
        }else if ((xpos <250 || xpos > 400) || (ypos > 240 || ypos < 187)) {
            btnEnd = new Image("map/btnEnd.png");
        }

        System.out.println(this.joueur.getDeck().getMurs().size());
        //getEtatMur
        if (xpos > 20 && xpos < 60 && ypos > 140 && ypos < 180) {
            if (input.isMouseButtonDown(0)) {
                etatMur = this.joueur.getDeck().getMur(0);
                this.joueur.getDeck().suppMur(0);
                Partie.waitForClick();
            }
        } else if (xpos > 140 && xpos < 180 && ypos > 140 && ypos < 180) {
            if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 1) {
                etatMur = this.joueur.getDeck().getMur(1);
                this.joueur.getDeck().suppMur(1);
                Partie.waitForClick();
            }
        } else if (xpos > 260 && xpos < 300 && ypos > 140 && ypos < 180) {
            if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 2) {
                etatMur = this.joueur.getDeck().getMur(2);
                this.joueur.getDeck().suppMur(2);
                Partie.waitForClick();
            }
        } else if (xpos > 380 && xpos < 420 && ypos > 140 && ypos < 180) {
            if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 3) {
                etatMur = this.joueur.getDeck().getMur(3);
                this.joueur.getDeck().suppMur(3);
                Partie.waitForClick();
            }
        } else if (xpos > 500 && xpos < 540 && ypos > 140 && ypos < 180) {
            if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 4) {
                etatMur = this.joueur.getDeck().getMur(4);
                this.joueur.getDeck().suppMur(4);
                Partie.waitForClick();
            }
        }

        //get MurX
        if (xpos > 150 && xpos < 190 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 0;
            }
        } else  if (xpos > 190 && xpos < 230 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 1;
            }
        } else  if (xpos > 230 && xpos < 270 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 2;
            }
        } else  if (xpos > 270 && xpos < 310 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 3;
            }
        } else  if (xpos > 310 && xpos < 350 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 4;
            }
        } else  if (xpos > 350 && xpos < 390 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 5;
            }
        } else  if (xpos > 390 && xpos < 430 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 6;
            }
        } else  if (xpos > 430 && xpos < 470 && ypos < 560 && ypos > 240) {
            if (input.isMouseButtonDown(0)) {
                murX = 7;
            }
        }

        //get MurY

        if (ypos > 560 && ypos < 600 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 0;
            }
        } else  if (ypos > 520 && ypos < 560 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 1;
            }
        } else  if (ypos > 480 && ypos < 520 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 2;
            }
        } else  if (ypos > 440 && ypos < 480 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 3;
            }
        } else  if (ypos > 400 && ypos < 440 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 4;
            }
        } else  if (ypos > 360 && ypos < 400 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 5;
            }
        } else  if (ypos > 320 && ypos < 360 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 6;
            }
        } else  if (ypos > 280 && ypos < 320 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 7;
            }
        }

        murPos = "mur : " + murX + " " + murY + " etat " + etatMur;

        if ((murX != -1) && (murY != -1) && (etatMur != ' ')) {
            addMur(murY, murX, etatMur);
            murX = -1; murY = -1; etatMur = ' ';
        }
    }

    @Override
    public int getID() {
        return 5;
    }

    public void addMur(int murX, int murY, char etatMur) {
        for (int i = 0; i < this.cases.size(); i++) {
            if (this.cases.get(i).getPosition(0) == murX && this.cases.get(i).getPosition(1) == murY) {
                if(this.cases.get(i).getEtat() == ' ') {
                    this.cases.get(i).setEtat(etatMur);
                    System.out.println(murPos);
                    stateBasedGame.enterState(2);
                    Partie.waitForClick();
                }
            }
        }

        System.out.println(murPos);
    }

    public void setTour(Joueur joueur, Plateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
    }


}
