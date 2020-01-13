package com.company.Tours;

import com.company.Cartes;
import com.company.Joueur;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

public class AddToProgram extends Tour {

    private Joueur joueur;
    private HashMap<Character, Image> list_cartes;
    private ArrayList<Character> main;

    public AddToProgram(int state) throws SlickException {
        super(state);
        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        this.main = joueur.getDeck().getMain();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString(mouse,150,50);
        g.drawImage(dammier, 150, 241);

        int u = 20;
        int v = 620;
        for (int i=0 ; i<5; i++){
            g.drawImage(this.list_cartes.get(this.main.get(i)),u ,v );
            u += 120;
        }
    }
}
