package com.company;

import com.company.Tours.AddToProgram;
import com.company.Tours.BuildWall;
import com.company.Tours.ExecProgram;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class Partie  extends BasicGameState {
    private int ID;
    String mouse = "No input yet!";
    static String demande = "";
    private String txt = "";
    private GameContainer gc;

    private static int nbrJoueur;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private int currentPlayer = 0;

    //Recuperation des BasicGameState
    private AddToProgram addToProgram;
    private BuildWall buildWall;
    private ExecProgram execProgram;

    private Image dammier;
    private Image btnWalls;
    private Image btnExe;
    private Image btnAdd;
    private static Plateau plateau;
    private ArrayList<Character> main;
    private HashMap<Character,Image> list_cartes;

    private boolean nouveauTour = true;

    private int currentTour = 0;


    public Partie(int state, int nbrJoueur) {
        this.ID = state;
        this.nbrJoueur = nbrJoueur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;

        System.out.println("Création");
        this.plateau = new Plateau();
        this.plateau.setPlateau();


        Joueur joueur1 = new Joueur(new int[]{0, 1},'1');
        this.joueurs.add(joueur1);
        Joueur joueur2 = new Joueur(new int[]{0, 5},'2');
        this.joueurs.add(joueur2);
        //System.out.println(this.joueurs.size());

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
        g.drawImage(dammier, 150, 200);
        g.drawImage(btnWalls, 90, 560);
        g.drawImage(btnExe, 250, 560);
        g.drawImage(btnAdd, 410, 560);
        g.drawString(mouse,150,50);
        g.drawString(demande, 200, 700 );
        g.drawString(txt,130,600);
        g.drawString("Tour du Joueur " + this.currentPlayer,225,10);


        //Affichage des éléments du plateau
        int x = 150;
        int y = 200;
        //Affichage des cases en fonction de leur état
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


       //Main du joueur
        int u = 20;
        int v = 620;
        for (int i=0 ; i<this.joueurs.get(this.currentPlayer-1).getDeck().getMain().size(); i++){
            g.drawImage(this.list_cartes.get(this.joueurs.get(this.currentPlayer-1).getDeck().getCarteMain(i)),u ,v );
            u += 120;
        }

        //File joueur
        u = 20;
        v = 100;
        for (int i = 0; i < this.joueurs.get(this.currentPlayer-1).getDeck().getFileInstruction().size(); i++) {
            //R pour carte face cachée
            g.drawImage(this.list_cartes.get('R'),u,v);
            u += 80;
        }
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "xpos: " + xpos + " ; ypos: " + ypos;

        //Joueur en cours
        if (nouveauTour){
            this.currentTour++;
            this.currentPlayer = this.currentTour%this.nbrJoueur;
            if (this.currentPlayer == 0){
                this.currentPlayer = this.nbrJoueur;
            }
            System.out.println("Tour: "+currentTour);
            System.out.println("Tour du joueur " + this.currentPlayer);
            this.nouveauTour = false;
        }


        //Check for button Add
        if ((xpos>410 && xpos<560) && (ypos<240 && ypos>187)){
            btnAdd = new Image("map/ADD-clicked.png");
            if (input.isMouseButtonDown(0)) {
                this.addToProgram.setTour(this.joueurs.get(this.currentPlayer-1),this.plateau);
                this.nouveauTour = true;
                sbg.enterState(3);
                waitForClick();
            }
        }
        if ((xpos<410 || xpos>560) || (ypos>240 || ypos<187)){
            btnAdd = new Image("map/ADD.png");
        }

        //Check for button exe
        if ((xpos>250 && xpos<400) && (ypos<240 && ypos>187)){
            btnExe = new Image("map/EXE-clicked.png");
            if (input.isMouseButtonDown(0)) {
                this.execProgram.setTour(this.joueurs.get(this.currentPlayer-1),this.plateau);
                this.nouveauTour = true;
                sbg.enterState(4);
                waitForClick();
            }
        }
        if ((xpos<250 || xpos>400) || (ypos>240 || ypos<187)){
            btnExe = new Image("map/EXE.png");
        }

        //Check for button Walls
        if ((xpos>90 && xpos<240) && (ypos<240 && ypos>187)){
            btnWalls = new Image("map/Walls-clicked.png");
            if (input.isMouseButtonDown(0)) {
                this.buildWall.setTour(this.joueurs.get(this.currentPlayer-1),this.plateau);
                this.nouveauTour = true;
                sbg.enterState(5);
                waitForClick();
            }
        }
        if ((xpos<90 || xpos>240) || (ypos>240 || ypos<187)){
            btnWalls = new Image("map/Walls.png");
        }


        for (Joueur joueur : this.joueurs){
            joueur.updateJoueur(this.plateau);
        }
    }


    public static void waitForClick() {
        //Wait for click
        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public static Plateau getPlateau() {
        return plateau;
    }

    public void setAddToProgram(AddToProgram addToProgram) {
        this.addToProgram = addToProgram;
    }

    public void setBuildWall(BuildWall buildWall) {
        this.buildWall = buildWall;
    }

    public void setExecProgram(ExecProgram execProgram) {
        this.execProgram = execProgram;
    }
}
