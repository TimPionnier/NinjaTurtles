package com.company.Tours;

import com.company.Cartes;
import com.company.Joueur;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AddToProgram extends Tour {

    private Joueur joueur;
    private HashMap<Character, Image> list_cartes;

    public AddToProgram(int state) throws SlickException {
        super(state);
        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString(mouse,150,50);
        g.drawImage(dammier, 150, 241);

        //Affichage des éléments du plateau
        int x = 190;
        int y = 200;


        //Affichage des cases en fonction de leur état
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

        //Main joueur
        int u = 20;
        int v = 620;
        for (int i=0 ; i<this.joueur.getDeck().getMain().size(); i++){
            g.drawImage(this.list_cartes.get(this.joueur.getDeck().getCarteMain(i)),u ,v );
            u += 120;
        }

        //File joueur (face cachée)
        u = 20;
        v = 100;
        for (int i = 0; i < this.joueur.getDeck().getFileInstruction().size(); i++) {
            //R pour carte face cachée
            g.drawImage(this.list_cartes.get('R'),u,v);
            u += 80;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException{
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xposs: " + xpos + " ; ypos: " + ypos;

        if ((xpos > 20 && xpos < 130) && (ypos>30 && ypos<180)) {
            if (input.isMouseButtonDown(0)) {
                this.joueur.getDeck().addFileInstruction(0);
                this.joueur.getDeck().suppCarteMain(0);
                //Wait for click
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if ((xpos > 140 && xpos < 250) && (ypos>30 && ypos<180)) {
            if (input.isMouseButtonDown(0)) {
                this.joueur.getDeck().addFileInstruction(1);
                this.joueur.getDeck().suppCarteMain(1);
                //Wait for click
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if ((xpos > 260 && xpos < 370) && (ypos>30 && ypos<180)) {
            if (input.isMouseButtonDown(0)) {
                this.joueur.getDeck().addFileInstruction(2);
                this.joueur.getDeck().suppCarteMain(2);
                //Wait for click
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if ((xpos > 380 && xpos < 490) && (ypos>30 && ypos<180)) {
            if (input.isMouseButtonDown(0)) {
                this.joueur.getDeck().addFileInstruction(3);
                this.joueur.getDeck().suppCarteMain(3);
                //Wait for click
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if ((xpos > 500 && xpos < 610) && (ypos>30 && ypos<180)) {
            if (input.isMouseButtonDown(0)) {
                this.joueur.getDeck().addFileInstruction(4);
                this.joueur.getDeck().suppCarteMain(4);
                //Wait for click
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getID() {
        return 3;
    }
}
