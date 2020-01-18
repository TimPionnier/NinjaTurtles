package com.company.Tours;

import com.company.Cartes;
import com.company.Case;
import com.company.Joueur;
import com.company.Plateau;
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
        g.drawImage(dammier, 150, 241);
        g.drawString(murPos, 200, 100);

        //Murs joueur
        int u = 20;
        int v = 620;
        for (int i=0 ; i<this.joueur.getDeck().getMurs().size(); i++) {
            g.drawImage(this.list_cartes.get(this.joueur.getDeck().getMur(i)), u, v);
            u += 120;
        }

        //Affichage des cases en fonction de leur état
        int x = 190;
        int y = 200;
        for (int i=0 ; i<64; i++) {
            if (this.plateau.getCase(i).getEtat() != ' ') { //Si la case n'est pas vide, il affiche l'image correspondant à l'état
                g.drawImage(this.list_cartes.get(this.plateau.getCase(i).getEtat()), x, y);
            }
            x += 40;
            if (i%8==0){
                x = 190 ;
                y+=40;
            }
        }


        /*for (int i=0 ; i<this.joueur.getDeck().getMain().size(); i++){
            g.drawImage(this.list_cartes.get(this.joueur.getDeck().getCarteMain(i)),u ,v );
            u += 120;
        }*/
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();

        this.cases = this.plateau.getCases();

        mouse = "xposs: " + xpos + " ; ypos: " + ypos;



        //getEtatMur
        if (xpos > 20 && xpos < 60) {
            if (input.isMouseButtonDown(0)) {

                etatMur = this.joueur.getDeck().getMur(0);
                System.out.println("etat mur = " + etatMur);
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

        if (ypos > 520 && ypos < 560 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 0;
            }
        } else  if (ypos > 480 && ypos < 520 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 1;
            }
        } else  if (ypos > 440 && ypos < 480 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 2;
            }
        } else  if (ypos > 400 && ypos < 440 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 3;
            }
        } else  if (ypos > 360 && ypos < 400 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 4;
            }
        } else  if (ypos > 320 && ypos < 360 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 5;
            }
        } else  if (ypos > 280 && ypos < 320 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 6;
            }
        } else  if (ypos > 240 && ypos < 280 && xpos < 470 && xpos > 150) {
            if (input.isMouseButtonDown(0)) {
                murY = 7;
            }
        }

        murPos = "mur : " + murX + " " + murY + " etat " + etatMur;

        if ((murX != -1) && (murY != -1) && (etatMur != ' ')) {
            addMur(murX, murY, etatMur);
            murX = -1; murY = -1; etatMur = ' ';
        }
    }

    @Override
    public int getID() {
        return 5;
    }

    public void addMur(int murX, int murY, char etatMur) {
        for (int i = 0; i < this.cases.size(); i++) {
            if ((this.cases.get(i).getPosition(0) == murX && this.cases.get(i).getPosition(1) == murY)) {
                this.cases.get(i).setEtat(etatMur);
                System.out.println(murPos);
            }
        }

        System.out.println(murPos);
    }

    public void setTour(Joueur joueur, Plateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
    }


}
