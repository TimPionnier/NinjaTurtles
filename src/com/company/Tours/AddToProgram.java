package com.company.Tours;

import com.company.Cartes;
import com.company.Joueur;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

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

        int u = 20;
        int v = 620;
        for (int i=0 ; i<5; i++){
            g.drawImage(this.list_cartes.get(joueur.getDeck().getCarteMain(i)),u ,v );
            u += 120;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException{
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xposs: " + xpos + " ; ypos: " + ypos;

        if ((xpos>20 && xpos<140) && (ypos<620 && ypos>770)){
            if (input.isMouseButtonDown(0)) {
                this.joueur.addInstruction(joueur.getDeck().getCarteMain(0));
                joueur.getDeck().suppCarteMain(0);
            }
        }
    }
}
