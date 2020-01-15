package com.company;

import com.company.Tours.AddToProgram;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;


public class Partie  extends BasicGameState {
    private int ID;
    String mouse = "No input yet!";
    static String demande = "";
    private String txt = "";
    private GameContainer gc;

    private static int nbrJoueur;
    private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

    //Recuperation des BasicGameState
    private AddToProgram addToProgram;

    private Image dammier;
    private Image btnWalls;
    private Image btnExe;
    private Image btnAdd;
    private Plateau plateau;
    private ArrayList<Character> main;
    private HashMap<Character,Image> list_cartes;


    public Partie(int state, int nbrJoueur) {
        this.ID = state;
        this.nbrJoueur = nbrJoueur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;

        this.plateau = new Plateau();
        this.plateau.setPlateau();

        this.main = Deck.getMain();

        Joueur joueur1 = new Joueur(new int[]{0, 1},'1');
        this.joueurs.add(joueur1);
        Joueur joueur2 = new Joueur(new int[]{0, 5},'2');
        this.joueurs.add(joueur2);
        System.out.println(this.joueurs.size());

        //Récupération de la HashMap reliant les états des cases aux images à afficher
        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();


        dammier = new Image("map/dammier.png");
        btnWalls = new Image("map/Walls.png");
        btnExe = new Image("map/EXE.png");
        btnAdd = new Image("map/Add.png");
    }


    public ArrayList<Character> getMain() {
        return main;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(dammier, 150, 241);
        g.drawImage(btnWalls, 90, 560);
        g.drawImage(btnExe, 250, 560);
        g.drawImage(btnAdd, 410, 560);
        g.drawString(mouse,150,50);
        g.drawString(demande, 200, 700 );
        g.drawString(txt,130,600);


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


       //Main du joueur
       int u = 20;
       int v = 620;
       char[] arrayMain = new char[5];
        for (int i=0 ; i<5; i++){
           arrayMain[i] = this.main.get(i);
            System.out.println(arrayMain[i]);
           g.drawImage(this.list_cartes.get(this.main.get(i)),u ,v );
           u += 120;
       }
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;


        //Check for button Add
        if ((xpos>410 && xpos<560) && (ypos<240 && ypos>187)){
            btnAdd = new Image("map/ADD-clicked.png");
            if (input.isMouseButtonDown(0)) {
                this.addToProgram.setJoueur(joueurs.get(0));
                sbg.enterState(3);
            }
        }
        if ((xpos<410 || xpos>560) || (ypos>240 || ypos<187)){
            btnAdd = new Image("map/ADD.png");
        }

        //Check for button exe
        if ((xpos>250 && xpos<400) && (ypos<240 && ypos>187)){
            btnExe = new Image("map/EXE-clicked.png");
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(2);
            }
        }
        if ((xpos<250 || xpos>400) || (ypos>240 || ypos<187)){
            btnExe = new Image("map/EXE.png");
        }

        //Check for button Walls
        if ((xpos>90 && xpos<240) && (ypos<240 && ypos>187)){
            btnWalls = new Image("map/Walls-clicked.png");
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(2);
            }
        }
        if ((xpos<90 || xpos>240) || (ypos>240 || ypos<187)){
            btnWalls = new Image("map/Walls.png");
        }


        for (Joueur joueur : this.joueurs){
            joueur.updateJoueur(this.plateau);
        }

        //recuperer valeur carte
        if ((xpos > 20)&&(xpos < 140)) {
            if (input.isMouseButtonDown(0)) {
                System.out.println("salut mon pote ta carte est : " + this.main.get(0));
            }
        }
    }




    public void keyReleased(int key, char c) {

        if (Input.KEY_ESCAPE == key) {
            gc.exit();
        }


    }

    public static void askNbrJoueur(int key){
        boolean condition = true; // on empeche le changement du nombre de joueur
        demande = "A combien voulez vous jouer ?";
        if (condition && (Input.KEY_1 == key || Input.KEY_2 == key || Input.KEY_3 == key)) {
            Partie.nbrJoueur = key - 1;
            demande = "";
            condition = false;
        }
    }


    public int getID() {
        return 2;
    }

    public void setAddToProgram(AddToProgram addToProgram) {
        this.addToProgram = addToProgram;
    }
}
