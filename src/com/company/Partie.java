package com.company;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;


public class Partie  extends BasicGameState {
    private GameContainer gc;
    private Image background;
    String mouse = "No input yet!";
    static String demande = "";

    private boolean etatPartie;

    private static int nbrJoueur;
    private static ArrayList<Joueur> joueurs = new ArrayList<>();

    private Image dammier;
    private Plateau plateau;
    private HashMap<Character,Image> list_cartes;
    private Character[] deck;


    public Partie(int i, int nbrJoueur) {
        this.nbrJoueur = nbrJoueur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.plateau = new Plateau();
        this.plateau.setPlateau();

        etatPartie = true;

        Joueur joueur1 = new Joueur(new int[]{0, 2}, new Character[37], new Character[0] , new Character[5]);
        Joueur joueur2 = new Joueur(new int[]{0, 5}, new Character[37], new Character[0] , new Character[5]);
        this.joueurs.add(joueur1);
        this.joueurs.add(joueur2);

        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();

        this.gc = gc;
        //AppGameContainer gameContainer = (AppGameContainer) gc; // function resizing the window, do not work
        //gameContainer.setDisplayMode(1100, 620, false);
        background = new Image("map/background.jpeg");
        dammier = new Image("map/dammier.png");

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(dammier, 150, 241);
        g.drawString(mouse,150,50);
        g.drawString(demande, 200, 700 );


        //Affichage des éléments du plateau
        int x = 190;
        int y = 200;


       for (int i=0 ; i<64; i++) {
           if (this.plateau.getCase(i).getEtat() != ' ') { //Si la case n'est pas vide,
               // il affiche l'image correspondant à l'état
               g.drawImage(this.list_cartes.get(this.plateau.getCase(i).getEtat()), x, y);
           }
           x += 40;
           if (i%8==0){
               x = 190 ;
               y+=40;
           }
        }
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //finPartie();
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;
    }




    public void keyReleased(int key, char c) {

        if (Input.KEY_ESCAPE == key) {
            gc.exit();
        }
    }

    /*public static void askNbrJoueur(int key){
        boolean condition = true; // on empeche le changement du nombre de joueur
        demande = "A combien voulez vous jouer ?";
        if (condition && (Input.KEY_1 == key || Input.KEY_2 == key || Input.KEY_3 == key)) {
            Partie.nbrJoueur = key - 1;
            demande = "";
            condition = false;

        }
    }*/

    public void tourJoueur(){

    }

    public Joueur getJoueur(int i) {
        return this.joueurs.get(i);
    }

    public boolean finPartie(){

        while (etatPartie) {
            for (int i = 0; i < joueurs.size(); i++)
                if ((this.joueurs.get(i).getPosition(0) == 7 && this.joueurs.get(i).getPosition(1) == 3)) {
                    etatPartie = false;
                }


        }
        return etatPartie;
    }





    public int getID() {
        return 2;
    }
}
