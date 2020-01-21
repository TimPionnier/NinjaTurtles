package com.company.Tours;

import com.company.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

public class ExecProgram extends Tour {
    private Joueur joueur;
    private HashMap<Character, Image> list_cartes;
    private static Winner winner;
    private static ArrayList<Joueur> joueurs = new ArrayList<>();

    public ExecProgram(int state) throws SlickException {
        super(state);
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

    private boolean colisionTortues() {
        if (this.joueur.getFrontCase(this.plateau).getEtat() == '1' ||
                this.joueur.getFrontCase(this.plateau).getEtat() == '2' ||
                this.joueur.getFrontCase(this.plateau).getEtat() == '3' ||
                this.joueur.getFrontCase(this.plateau).getEtat() == '4' ) {
        }
        return true;
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
            btnEnd = new Image("map/btnEnd1.png");
        }

        if (this.joueur.getDeck().getFileInstruction().size()>0) {
            char instruction = this.joueur.getDeck().getFileInstruction().remove();


            if (instruction == 'B') {

                this.plateau.getCase(this.joueur.getPosition(0), this.joueur.getPosition(1)).setEtat(' '); //on reset la valeur de la case pour afficher la tortue uniquement sur sa nvl position

                /*if (this.joueur.getFrontCase(this.plateau).getEtat() == '1' ||
                        this.joueur.getFrontCase(this.plateau).getEtat() == '2' ||
                        this.joueur.getFrontCase(this.plateau).getEtat() == '3' ||
                        this.joueur.getFrontCase(this.plateau).getEtat() == '4' ) {
                    //renvoie le joueur qui joue et celui dans lequel il est rentré à leur position de départ
                    Partie.makeJoueurReturnStart(this.joueur.getFrontCase(this.plateau).getEtat());
                    resetRotation(this.joueur.getDirection());
                    this.joueur.returnStart();

                }*/
                if (this.joueur.getDirection() == 'N' ) {
                    if (this.joueur.getPosition(0) == 0) {
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == ' ') {
                        this.joueur.setPosition(0, this.joueur.getPosition(0) - 1);
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == '?') {
                        this.winner.addToWinners(this.joueur);
                    } else if (colisionTortues()) {
                        makeJoueurReturnStart(this.joueur.getFrontCase(this.plateau).getEtat());
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    }

                } else if (this.joueur.getDirection() == 'E' ) {
                    if (this.joueur.getPosition(1) == 7) {
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == ' '){
                        this.joueur.setPosition(1, this.joueur.getPosition(1) + 1);
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == '?') {
                        this.winner.addToWinners(this.joueur);
                    } else if (colisionTortues()) {
                        makeJoueurReturnStart(this.joueur.getFrontCase(this.plateau).getEtat());
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    }

                } else if (this.joueur.getDirection() == 'S') {
                    if (this.joueur.getPosition(0) == 7) {
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == ' '){
                        this.joueur.setPosition(0, this.joueur.getPosition(0) + 1);
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == '?') {
                        this.winner.addToWinners(this.joueur);
                    } else if (colisionTortues()) {
                        makeJoueurReturnStart(this.joueur.getFrontCase(this.plateau).getEtat());
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    }

                } else if (this.joueur.getDirection() == 'O' ) {
                    if (this.joueur.getPosition(1) == 0) {
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == ' ') {
                        this.joueur.setPosition(1, this.joueur.getPosition(1) - 1);
                    } else if (this.joueur.getFrontCase(this.plateau).getEtat() == '?') {
                        this.winner.addToWinners(this.joueur);
                    } else if (colisionTortues()) {
                        makeJoueurReturnStart(this.joueur.getFrontCase(this.plateau).getEtat());
                        resetRotation(this.joueur.getDirection());
                        this.joueur.returnStart();
                    }

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
                //cas où le laser touche un mur de glace
                if (this.joueur.getFrontCase(plateau).getEtat() == 'G') {
                    this.joueur.getFrontCase(plateau).setEtat(' ');

                } else if (this.joueur.getFrontCase(this.plateau).getEtat() == '1' || //cas où le laser touche une tortue
                        this.joueur.getFrontCase(this.plateau).getEtat() == '2' ||
                        this.joueur.getFrontCase(this.plateau).getEtat() == '3' ||
                        this.joueur.getFrontCase(this.plateau).getEtat() == '4' ) {
                    if(this.nbrJoueur > 2 ) {
                        switch (this.joueur.getDirection()){
                            case 'N':
                                this.joueur.setDirection('S');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                            case 'E':
                                this.joueur.setDirection('0');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                            case 'O':
                                this.joueur.setDirection('E');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                            case 'S':
                                this.joueur.setDirection('N');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                        }
                    } else if (this.nbrJoueur == 2){
                        makeJoueurReturnStart(this.joueur.getFrontCase(this.plateau).getEtat());
                    }

                } else if(this.joueur.getFrontCase(this.plateau).getEtat() == '?'){
                    //cas où le laser touche le joyau
                    if(this.nbrJoueur > 2 ) {
                        switch (this.joueur.getDirection()){
                            case 'N':
                                this.joueur.setDirection('S');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                            case 'E':
                                this.joueur.setDirection('0');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                            case 'O':
                                this.joueur.setDirection('E');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                            case 'S':
                                this.joueur.setDirection('N');
                                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                                break;
                        }
                    } else if (this.nbrJoueur == 2){
                        resetRotation(this.joueur.getDirection());
                        plateau.getCase(this.joueur.getPosition(0), this.joueur.getPosition(1)).setEtat(' ');
                        this.joueur.returnStart();
                    }
                }else {
                    System.out.println("Action impossible ici");
                }
            }
            Partie.waitForClick();
        }
        else {
            stateBasedGame.enterState(2);
        }
    }

    public void makeJoueurReturnStart(char numJoueur) {
        //renvoie à sa position de depart le joueur dont numJoueur == le parametre de cette fontion
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getNumJoueur() == numJoueur) {
                this.plateau.getCase(joueurs.get(i).getPosition(0), joueurs.get(i).getPosition(1)).setEtat(' ');
                resetRotation(joueurs.get(i).getDirection());
                joueurs.get(i).returnStart();
            }
        }

    }

    public void resetRotation(char previousDirection){
        //reset rotation of image linked to player when he has to return to his startPosition
        switch (previousDirection){
            case 'E':
                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(90);
                this.joueur.setDirection('S');
                break;
            case 'S':
                //do nothing
                break;
            case 'O':
                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(-90);
                break;
            case 'N':
                this.list_cartes.get(this.joueur.getNumJoueur()).rotate(180);
                break;
        }
    }

    public void setTour(Joueur joueur, Plateau plateau, HashMap<Character,Image> list_cartes, ArrayList<Joueur> joueurs) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.list_cartes = list_cartes;
        this.joueurs = joueurs;
    }

    public static void setWinner(Winner winner) {
        ExecProgram.winner = winner;
    }
}
