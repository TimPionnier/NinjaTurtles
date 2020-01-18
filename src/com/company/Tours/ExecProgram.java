package com.company.Tours;

import com.company.Cartes;
import com.company.Joueur;
import com.company.Plateau;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.HashMap;

public class ExecProgram extends Tour {
    private Joueur joueur;
    private HashMap<Character, Image> list_cartes;

    public ExecProgram(int state) throws SlickException {
        super(state);
        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();
    }


    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        g.drawImage(dammier, 150, 241);
        g.drawImage(btnEnd, 250, 560);

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
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void setTour(Joueur joueur, Plateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
    }
}
