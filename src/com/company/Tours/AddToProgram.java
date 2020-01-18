package com.company.Tours;

import com.company.Cartes;
import com.company.Joueur;
import com.company.Partie;
import com.company.Plateau;
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


    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString(mouse,150,50);
        g.drawImage(dammier, 150, 241);
        g.drawString("Défausser ses cartes",225,775);
        g.drawString("Tour du Joueur " + this.joueur.getNumJoueur(),225,10);
        g.drawImage(btnEnd, 250, 560);


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
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException{
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
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

        if(this.joueur.getDeck().getMain().size()>0) {
            //récupération des clics sur les cartes = ajout à la file d'instruction
            if ((xpos > 20 && xpos < 130) && (ypos > 30 && ypos < 180)) {
                if (input.isMouseButtonDown(0)) {
                    this.joueur.getDeck().addFileInstruction(0);
                    this.joueur.getDeck().suppCarteMain(0);
                    Partie.waitForClick();
                }
            } else if ((xpos > 140 && xpos < 250) && (ypos > 30 && ypos < 180)) {
                if (input.isMouseButtonDown(0)) {
                    this.joueur.getDeck().addFileInstruction(1);
                    this.joueur.getDeck().suppCarteMain(1);
                    Partie.waitForClick();
                }
            } else if ((xpos > 260 && xpos < 370) && (ypos > 30 && ypos < 180)) {
                if (input.isMouseButtonDown(0)) {
                    this.joueur.getDeck().addFileInstruction(2);
                    this.joueur.getDeck().suppCarteMain(2);
                    Partie.waitForClick();
                }
            } else if ((xpos > 380 && xpos < 490) && (ypos > 30 && ypos < 180)) {
                if (input.isMouseButtonDown(0)) {
                    this.joueur.getDeck().addFileInstruction(3);
                    this.joueur.getDeck().suppCarteMain(3);
                    Partie.waitForClick();
                }
            } else if ((xpos > 500 && xpos < 610) && (ypos > 30 && ypos < 180)) {
                if (input.isMouseButtonDown(0)) {
                    this.joueur.getDeck().addFileInstruction(4);
                    this.joueur.getDeck().suppCarteMain(4);
                    Partie.waitForClick();
                }
            } else if ((xpos > 226 && xpos < 407) && (ypos > 5 && ypos < 23)) {
                if (input.isMouseButtonDown(0)) {
                    System.out.println("defausse");
                    this.joueur.getDeck().remplirDefausse();
                    this.joueur.getDeck().remplirMain();
                    //stateBasedGame.enterState(2);
                    Partie.waitForClick();
                }
            }
        }
    }

    public void setTour(Joueur joueur, Plateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
    }

    @Override
    public int getID() {
        return 3;
    }
}
