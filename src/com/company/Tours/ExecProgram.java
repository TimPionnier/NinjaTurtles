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
    private static Winner winner;

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
        g.drawImage(dammier, 150, 200);
        g.drawImage(btnEnd, 250, 560);
        g.drawString("Tour du Joueur " + this.joueur.getNumJoueur(),225,10);


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
                this.plateau.getCase(this.joueur.getPosition(0),this.joueur.getPosition(1)).setEtat(' ');
                if (this.joueur.getDirection() == 'N' && this.joueur.getFrontCase(this.plateau).getEtat() == ' ') {
                    this.joueur.setPosition(0,this.joueur.getPosition(0) - 1);
                } else if (this.joueur.getDirection() == 'E' && this.joueur.getFrontCase(this.plateau).getEtat() == ' ') {
                    this.joueur.setPosition(1,this.joueur.getPosition(1) + 1);
                } else if (this.joueur.getDirection() == 'S' && this.joueur.getFrontCase(this.plateau).getEtat() == ' ') {
                    this.joueur.setPosition(0,this.joueur.getPosition(0) + 1);
                } else if (this.joueur.getDirection() == 'O' && this.joueur.getFrontCase(this.plateau).getEtat() == ' ') {
                    this.joueur.setPosition(1,this.joueur.getPosition(1) - 1);
                } else if (this.joueur.getFrontCase(this.plateau).getEtat() == '?'){
                    this.winner.setJoueur(this.joueur);
                    sbg.enterState(6);
                } else if (this.joueur.getFrontCase(this.plateau).getEtat() == 'G' ||
                           this.joueur.getFrontCase(this.plateau).getEtat() == 'P' ||
                           this.joueur.getFrontCase(this.plateau).getEtat() == 'C'){
                    System.out.println("Attention ! Un mur bloque le passage. Vous faites demi-tour");
                    switch (this.joueur.getDirection()){
                        case 'N':
                            this.joueur.setDirection('S');
                            break;
                        case 'S':
                            this.joueur.setDirection('N');
                            break;
                        case 'E':
                            this.joueur.setDirection('O');
                            break;
                        case 'O':
                            this.joueur.setDirection('E');
                            break;
                        default:
                            System.out.println("Error");
                    }
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
                if (this.joueur.getFrontCase(plateau).getEtat() == 'G') {
                    this.joueur.getFrontCase(plateau).setEtat(' ');
                }
                else {
                    System.out.println("Action impossible ici");
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

    public static void setWinner(Winner winner) {
        ExecProgram.winner = winner;
    }

}
