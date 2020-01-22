package com.company.States.Tours;

import com.company.Objects.Plateau.Case;
import com.company.Objects.Plateau.Plateau;
import com.company.Players.Joueur;
import com.company.States.Partie;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildWall extends Tour {
    private static ArrayList<Case> cases;
    private static boolean buildOK = true;
    int murX = -1;
    int murY = -1;
    char etatMur = ' ';
    String murPos = " ";
    private ArrayList<Case> visited = new ArrayList<>();
    private Joueur joueur;

    public BuildWall(int state) {
        super(state);
    }

    public static void setBuildOK(boolean buildOK) {
        BuildWall.buildOK = buildOK;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawImage(dammier, 150, 200);
        g.drawString(murPos, 200, 100);
        g.drawImage(btnEnd, 250, 560);
        g.drawString("Défausser ses cartes", 225, 775);

        //Murs joueur
        int u = 20;
        int v = 620;
        for (int i = 0; i < this.joueur.getDeck().getMurs().size(); i++) {
            g.drawImage(this.list_cartes.get(this.joueur.getDeck().getMur(i)), u, v);
            u += 120;
        }


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

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();

        cases = Plateau.getCases();


        if ((xpos > 250 && xpos < 400) && (ypos < 240 && ypos > 187)) {
            btnEnd = new Image("map/btnEnd-clicked.png");
            if (input.isMouseButtonDown(0)) {
                if (this.joueur.getDeck().getMain().size() < 5) {
                    this.joueur.getDeck().remplirMain();
                }
                sbg.enterState(2);
                Partie.waitForClick();
            }
        } else if ((xpos < 250 || xpos > 400) || (ypos > 240 || ypos < 187)) {
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

        if (buildOK) {
            //getEtatMur
            if (xpos > 20 && xpos < 60 && ypos > 140 && ypos < 180) {
                if (input.isMouseButtonDown(0)) {
                    etatMur = this.joueur.getDeck().getMur(0);
                    this.joueur.getDeck().suppMur(0);
                    Partie.waitForClick();
                }
            } else if (xpos > 140 && xpos < 180 && ypos > 140 && ypos < 180) {
                if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 1) {
                    etatMur = this.joueur.getDeck().getMur(1);
                    this.joueur.getDeck().suppMur(1);
                    Partie.waitForClick();
                }
            } else if (xpos > 260 && xpos < 300 && ypos > 140 && ypos < 180) {
                if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 2) {
                    etatMur = this.joueur.getDeck().getMur(2);
                    this.joueur.getDeck().suppMur(2);
                    Partie.waitForClick();
                }
            } else if (xpos > 380 && xpos < 420 && ypos > 140 && ypos < 180) {
                if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 3) {
                    etatMur = this.joueur.getDeck().getMur(3);
                    this.joueur.getDeck().suppMur(3);
                    Partie.waitForClick();
                }
            } else if (xpos > 500 && xpos < 540 && ypos > 140 && ypos < 180) {
                if (input.isMouseButtonDown(0) && this.joueur.getDeck().getMurs().size() > 4) {
                    etatMur = this.joueur.getDeck().getMur(4);
                    this.joueur.getDeck().suppMur(4);
                    Partie.waitForClick();
                }
            }

            //get MurX
            if (xpos > 150 && xpos < 190 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 0;
                }
            } else if (xpos > 190 && xpos < 230 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 1;
                }
            } else if (xpos > 230 && xpos < 270 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 2;
                }
            } else if (xpos > 270 && xpos < 310 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 3;
                }
            } else if (xpos > 310 && xpos < 350 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 4;
                }
            } else if (xpos > 350 && xpos < 390 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 5;
                }
            } else if (xpos > 390 && xpos < 430 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 6;
                }
            } else if (xpos > 430 && xpos < 470 && ypos < 600 && ypos > 240) {
                if (input.isMouseButtonDown(0)) {
                    murY = 7;
                }
            }

            //get MurY

            if (ypos > 560 && ypos < 600 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 0;
                }
            } else if (ypos > 520 && ypos < 560 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 1;
                }
            } else if (ypos > 480 && ypos < 520 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 2;
                }
            } else if (ypos > 440 && ypos < 480 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 3;
                }
            } else if (ypos > 400 && ypos < 440 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 4;
                }
            } else if (ypos > 360 && ypos < 400 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 5;
                }
            } else if (ypos > 320 && ypos < 360 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 6;
                }
            } else if (ypos > 280 && ypos < 320 && xpos < 470 && xpos > 150) {
                if (input.isMouseButtonDown(0)) {
                    murX = 7;
                }
            }

            murPos = "mur : " + murX + " " + murY + " etat " + etatMur;

            if ((murX != -1) && (murY != -1) && (etatMur != ' ') && Plateau.getCase(murX, murY).getEtat() == ' ') {
                addMur(murX, murY, etatMur);
                murX = -1;
                murY = -1;
                etatMur = ' ';
                buildOK = false;
            } else if ((murX != -1) && (murY != -1) && Plateau.getCase(murX, murY).getEtat() != ' ') {
                System.out.println("impossible de placer un mur ici");
                System.out.println(Plateau.getCase(murX, murY).getEtat());
            }
        }

    }

    @Override
    public int getID() {
        return 5;
    }

    public void addMur(int murX, int murY, char etatMur) {
        Plateau.getCase(murX, murY).setEtat(etatMur);

        if (autoriseMur(Partie.nbrJoueur)) {
            this.visited.clear();
        } else {
            this.visited.clear();
            System.out.println("block");
            this.joueur.getDeck().getMurs().add(etatMur);
            Plateau.getCase(murX, murY).setEtat(' ');
            buildOK = true;
            stateBasedGame.enterState(5);
            Partie.waitForClick();
        }

    }

    public void setTour(Joueur joueur, Plateau plateau, HashMap<Character, Image> list_cartes) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.list_cartes = list_cartes;
    }

    public boolean autoriseMur(int nbrJoueur) {
        switch (nbrJoueur) {
            case 2:
                if (checkPath(Plateau.getCase(7, 3))) {
                    return true;
                }
            case 3:
                if (checkPath(Plateau.getCase(7, 0)) && checkPath(Plateau.getCase(7, 3))
                        && checkPath(Plateau.getCase(7, 6))) {
                    return true;
                }
            case 4:
                if (checkPath(Plateau.getCase(7, 1)) && checkPath(Plateau.getCase(7, 6))) {
                    return true;
                }
        }
        return false;
    }

    public boolean checkPath(Case current) {
        this.visited.add(current);
        System.out.println(this.visited.get(visited.size() - 1).getPosition(0) + " " + this.visited.get(visited.size() - 1).getPosition(1));
        System.out.println(this.visited.get(visited.size() - 1).getEtat());
        int x = current.getPosition(0);
        int y = current.getPosition(1);


        if (x > 7 || y > 7 || x < -1 || y < -1) {
            return false;
        }

        if (current.getEtat() == 'P' || current.getEtat() == 'C') {
            return false;
        }


        if (Plateau.getCase(x, y).getEtat() == '1' ||
                Plateau.getCase(x, y).getEtat() == '2' ||
                Plateau.getCase(x, y).getEtat() == '3' ||
                Plateau.getCase(x, y).getEtat() == '4') {
            return true;
        }

        if ((x + 1 < 8) && !this.visited.contains(Plateau.getCase(x + 1, y)) && checkPath(Plateau.getCase(x + 1, y))) {
            return true;
        }

        if ((x - 1 > -1) && !this.visited.contains(Plateau.getCase(x - 1, y)) && checkPath(Plateau.getCase(x - 1, y))) {
            return true;
        }

        if ((y + 1 < 8) && !this.visited.contains(Plateau.getCase(x, y + 1)) && checkPath(Plateau.getCase(x, y + 1))) {
            return true;
        }

        if ((y - 1 > -1) && !this.visited.contains(Plateau.getCase(x, y - 1)) && checkPath(Plateau.getCase(x, y - 1))){
            return true;
        }

        return false;
    }
}
