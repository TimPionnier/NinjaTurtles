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
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString(mouse,150,50);
        g.drawImage(dammier, 150, 241);

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

        if ((xpos > 20 && xpos < 140) && (ypos>30 && ypos<180)) {
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
        }
    }
}
