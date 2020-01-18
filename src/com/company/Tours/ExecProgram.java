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
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString(mouse,150,50);
        g.drawImage(dammier, 150, 241);
        g.drawString("Tour du Joueur " + this.joueur.getNumJoueur(),225,10);


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
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (this.joueur.getDeck().getFileInstruction().size()>0) {
            char instruction = this.joueur.getDeck().getFileInstruction().remove();

            
            if (instruction == 'B') {
                if ((this.joueur.getDirection() == 'N') ) {
                    this.joueur.setPosition(0,this.joueur.getPosition(0) - 1);
                } else if (this.joueur.getDirection() == 'E') {
                    this.joueur.setPosition(1,this.joueur.getPosition(1) + 1);
                } else if (this.joueur.getDirection() == 'S') {
                    this.joueur.setPosition(0,this.joueur.getPosition(0) + 1);
                } else if (this.joueur.getDirection() == 'O') {
                    this.joueur.setPosition(1,this.joueur.getPosition(1) - 1);
                }

            } else if (instruction == 'J') {
                if (this.joueur.getDirection() == 'N') {
                    this.joueur.setDirection('O');
                } else if (this.joueur.getDirection() == 'E') {
                    this.joueur.setDirection('N');
                } else if (this.joueur.getDirection() == 'S') {
                    this.joueur.setDirection('E');
                } else if (this.joueur.getDirection() == 'O') {
                    this.joueur.setDirection('S');
                }
            } else if (instruction == 'V') {
                if (this.joueur.getDirection() == 'N') {
                    this.joueur.setDirection('E');
                } else if (this.joueur.getDirection() == 'E') {
                    this.joueur.setDirection('S');
                } else if (this.joueur.getDirection() == 'S') {
                    this.joueur.setDirection('O');
                } else if (this.joueur.getDirection() == 'O') {
                    this.joueur.setDirection('N');
                }
            } else if (instruction == 'L') {
                if (this.joueur.getDirection() == 'N') {
                    //useLaser('N');
                } else if (this.joueur.getDirection() == 'E') {
                    //useLaser('E');
                } else if (this.joueur.getDirection() == 'S') {
                    //useLaser('S');
                } else if (this.joueur.getDirection() == 'O') {
                    //useLaser('O');
                }
            }
        }
        else {
            stateBasedGame.enterState(2);
        }
    }

    public void setTour(Joueur joueur, Plateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
    }
}
