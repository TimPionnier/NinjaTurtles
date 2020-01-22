package com.company.States.Tours;

import com.company.Players.Joueur;
import com.company.States.Partie;
import com.company.Objects.Plateau.Plateau;
import com.company.States.Winner;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

public class ExecProgram extends Tour {
    private Joueur joueur;

    public ExecProgram(int state) {
        super(state);
    }


    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawImage(dammier, 150, 200);
        g.drawImage(btnEnd, 250, 560);
        g.drawString("Défausser ses cartes",225,775);
        g.drawString("Tour du Joueur " + this.joueur.getNumJoueur(), 225, 10);


        //Affichage des cases en fonction de leur état
        int x = 150;
        int y = 200;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Plateau.getCase(i, j).getEtat() != ' ') { //Si la case n'est pas vide, il affiche l'image correspondant à l'état
                    g.drawImage(this.list_cartes.get(Plateau.getCase(i, j).getEtat()), x, y);
                }
                x += 40;
            }
            x = 150;
            y += 40;
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
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();

        if ((xpos > 250 && xpos < 400) && (ypos < 240 && ypos > 187)) {
            btnEnd = new Image("map/btnEnd-clicked.png");
            if (input.isMouseButtonDown(0)) {
                if (this.joueur.getDeck().getMain().size() < 5) {
                    this.joueur.getDeck().remplirMain();
                }
                sbg.enterState(2);
                Partie.waitForClick();
            }
        } else {
            btnEnd = new Image("map/btnEnd1.png");
        }
        if ((xpos > 226 && xpos < 407) && (ypos > 5 && ypos < 23)) {
            if (input.isMouseButtonDown(0)) {
                System.out.println("defausse");
                this.joueur.getDeck().remplirDefausse();
                this.joueur.getDeck().remplirMain();
                stateBasedGame.enterState(2);
                Partie.waitForClick();
            }
        }

        if (this.joueur.getDeck().getFileInstruction().size() > 0) {
            char instruction = this.joueur.getDeck().getFileInstruction().remove();


            if (instruction == 'B') {

                Plateau.getCase(this.joueur.getPosition(0), this.joueur.getPosition(1)).setEtat(' '); //on reset la valeur de la case pour afficher la tortue uniquement sur sa nvl position
                if (this.joueur.getDirection() == 'N') {
                    if (this.joueur.getPosition(0) == 0) {
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase().getEtat() == ' ') {
                        this.joueur.setPosition(0, this.joueur.getPosition(0) - 1);
                    } else if (this.joueur.getFrontCase().getEtat() == '?') {
                        Winner.addToWinners(this.joueur);
                    }

                } else if (this.joueur.getDirection() == 'E') {
                    if (this.joueur.getPosition(1) == 7) {
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase().getEtat() == ' ') {
                        this.joueur.setPosition(1, this.joueur.getPosition(1) + 1);
                    } else if (this.joueur.getFrontCase().getEtat() == '?') {
                        Winner.addToWinners(this.joueur);
                    }

                } else if (this.joueur.getDirection() == 'S') {
                    if (this.joueur.getPosition(0) == 7) {
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase().getEtat() == ' ') {
                        this.joueur.setPosition(0, this.joueur.getPosition(0) + 1);
                    } else if (this.joueur.getFrontCase().getEtat() == '?') {
                        Winner.addToWinners(this.joueur);
                    }

                } else if (this.joueur.getDirection() == 'O') {
                    if (this.joueur.getPosition(1) == 0) {
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase().getEtat() == ' ') {
                        this.joueur.setPosition(1, this.joueur.getPosition(1) - 1);
                    } else if (this.joueur.getFrontCase().getEtat() == '?') {
                        Winner.addToWinners(this.joueur);
                    }

                } else if (this.joueur.getFrontCase().getEtat() == '1' ||
                        this.joueur.getFrontCase().getEtat() == '2' ||
                        this.joueur.getFrontCase().getEtat() == '3' ||
                        this.joueur.getFrontCase().getEtat() == '4') {
                    //renvoie le joueur qui joue et celui dans lequel il est rentré à leur position de départ
                    Partie.makeJoueurReturnStart(this.joueur.getFrontCase().getEtat());
                    this.joueur.returnStart();

                } else if (this.joueur.getFrontCase().getEtat() == 'G' ||
                        this.joueur.getFrontCase().getEtat() == 'P' ||
                        this.joueur.getFrontCase().getEtat() == 'C') {
                    System.out.println("Attention ! Un mur bloque le passage. Vous faites demi-tour");
                    switch (this.joueur.getDirection()) {
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
                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(-90);
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
                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(90);
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
                if (this.joueur.getFrontCase().getEtat() == 'G') {
                    this.joueur.getFrontCase().setEtat(' ');
                } else {
                    System.out.println("Action impossible ici");
                }
            }
            if (!Winner.getWinners().contains(this.joueur)) {
                joueur.updateJoueur();
            }
            Partie.waitForClick();
        }
    }

    public void setTour(Joueur joueur, Plateau plateau, HashMap<Character, Image> list_cartes, ArrayList<Joueur> joueurs) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.list_cartes = list_cartes;
    }
}
