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
    private String txt = "";

    private static int nbrJoueur;
    private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

    private Image dammier;
    private Image btnWalls;
    private Image btnExe;
    private Image btnAdd;
    private Plateau plateau;
    private ArrayList<Character> main;
    private HashMap<Character,Image> list_cartes;

    private boolean tourEnCours = false;


    public Partie(int i, int nbrJoueur) {
        this.nbrJoueur = nbrJoueur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.plateau = new Plateau();
        this.plateau.setPlateau();

        this.main = Deck.getMain();



        Joueur joueur1 = new Joueur(new int[]{0, 1},'1');
        this.joueurs.add(joueur1);
        Joueur joueur2 = new Joueur(new int[]{0, 5},'2');
        this.joueurs.add(joueur2);
        System.out.println(this.joueurs.size());

        Cartes cartes = new Cartes();
        this.list_cartes = cartes.getCartes();


        this.gc = gc;
        //AppGameContainer gameContainer = (AppGameContainer) gc; // function resizing the window, do not work
        //gameContainer.setDisplayMode(1100, 620, false);
        background = new Image("map/background.jpeg");
        dammier = new Image("map/dammier.png");
        btnWalls = new Image("map/Walls.png");
        btnExe = new Image("map/EXE.png");
        btnAdd = new Image("map/Add.png");


    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(dammier, 150, 241);
        g.drawImage(btnWalls, 90, 560);
        g.drawImage(btnExe, 250, 560);
        g.drawImage(btnAdd, 410, 560);
        g.drawString(mouse,150,50);
        g.drawString(demande, 200, 700 );
        //g.drawString(txt,130,600);


        //Affichage des éléments du plateau
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


        for (Joueur joueur : this.joueurs){
            this.tourJoueur(joueur.getNumJoueur());
            joueur.updateJoueur(this.plateau);
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

    public void tourJoueur(Character joueur){
        this.tourEnCours = true;
        
            this.txt = "Quelle action souhaitez-vous effectuer ?\n" +
                    "A- Compléter votre programme\n" +
                    "B- Construir un mur\n" +
                    "C- Executer votre programme";

    }

    public void construireMur(){

    }

    public void completerProgramme(){
        
    }

    public void executerProgramme(){

    }





    public int getID() {
        return 2;
    }
}
