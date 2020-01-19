package com.company.Tours;

import com.company.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
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
        g.drawImage(btnEnd, 250, 560);
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
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
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
            Partie.waitForClick();
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
