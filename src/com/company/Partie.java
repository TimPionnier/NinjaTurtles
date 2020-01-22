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


public class Partie extends BasicGameState {
    //Logique de tour
    public static int nbrJoueur;
    static String demande = "";
    private static int ID;
    private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private static Plateau plateau;
    private GameContainer gc;
    private int currentPlayer = 0;
    private boolean nouveauTour = true;
    private int currentTour = 0;
    private ArrayList<Character> main;
    private HashMap<Character, Image> list_cartes;
    private boolean partieSet = true;
    //Recuperation des BasicGameState
    private AddToProgram addToProgram;
    private BuildWall buildWall;
    private ExecProgram execProgram;
    private Winner winner;
    //Affichage
    private Image dammier;
    private Image btnWalls;
    private Image btnExe;
    private Image btnAdd;
    private String txt = "";


    public Partie(int state) {
        ID = state;
    }

    public static void setNbrJoueur(int i) {
        nbrJoueur = i;
    }

    public static ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public static void waitForClick() {
        //Wait for click
        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void makeJoueurReturnStart(char numJoueur) {
        //renvoie à sa position de depart le joueur dont numJoueur == le parametre de cette fontion
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getNumJoueur() == numJoueur) {
                Plateau.getCase(joueurs.get(i).getPosition(0), joueurs.get(i).getPosition(1)).setEtat(' ');
                joueurs.get(i).returnStart();
            }
        }

    }

    public static Plateau getPlateau() {
        return plateau;
    }

    public int getID() {
        return ID;
    }

    public void setPartie(int nbrJoueur) throws SlickException {
        plateau = new Plateau();
        Joueur joueur1;
        Joueur joueur2;
        Joueur joueur3;
        Joueur joueur4;
        switch (nbrJoueur) {
            case 2:
                Plateau.setPlateau(nbrJoueur);
                joueur1 = new Joueur(new int[]{0, 1}, '1');
                joueur2 = new Joueur(new int[]{7, 5}, '2');
                joueurs.add(joueur1);
                joueurs.add(joueur2);
                break;
            case 3:
                Plateau.setPlateau(nbrJoueur);
                joueur1 = new Joueur(new int[]{6, 0}, '1');
                joueur2 = new Joueur(new int[]{0, 3}, '2');
                joueur3 = new Joueur(new int[]{0, 6}, '3');
                joueurs.add(joueur1);
                joueurs.add(joueur2);
                joueurs.add(joueur3);
                break;
            case 4:
                Plateau.setPlateau(nbrJoueur);
                joueur1 = new Joueur(new int[]{7, 0}, '1');
                joueur2 = new Joueur(new int[]{7, 2}, '2');
                joueur3 = new Joueur(new int[]{7, 5}, '3');
                joueur4 = new Joueur(new int[]{7, 7}, '4');
                joueurs.add(joueur1);
                joueurs.add(joueur2);
                joueurs.add(joueur3);
                joueurs.add(joueur4);
                break;
        }

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;


        setPartie(nbrJoueur);

        //Récupération de la HashMap reliant les états des cases aux images à afficher
        Cartes cartes = new Cartes();
        this.list_cartes = Cartes.getCartes();


        dammier = new Image("map/dammier.png");
        btnWalls = new Image("map/Walls.png");
        btnExe = new Image("map/EXE.png");
        btnAdd = new Image("map/Add.png");


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);

        g.drawImage(dammier, 150, 200);
        g.drawImage(btnWalls, 90, 560);
        g.drawImage(btnExe, 250, 560);
        g.drawImage(btnAdd, 410, 560);
        g.drawString(demande, 200, 700);
        g.drawString(txt, 130, 600);
        g.drawString("Tour du Joueur " + this.currentPlayer, 225, 10);
        int k = 0;
        //int y = 0;
        for (int i = 0; i < 9; i++) {
            g.drawLine(150, 520 - k, 470, 520 - k);
            g.drawLine(150 + k, 520, 150 + k, 200);
            k += 40;

        }


        //Affichage des éléments du plateau
        int x = 150;
        int y = 200;
        //Affichage des cases en fonction de leur état
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


        //Main du joueur
        int u = 20;
        int v = 620;
        for (int i = 0; i < joueurs.get(this.currentPlayer - 1).getDeck().getMain().size(); i++) {
            g.drawImage(this.list_cartes.get(joueurs.get(this.currentPlayer - 1).getDeck().getCarteMain(i)), u, v);
            u += 120;
        }

        //File joueur
        u = 20;
        v = 100;
        for (int i = 0; i < joueurs.get(this.currentPlayer - 1).getDeck().getFileInstruction().size(); i++) {
            //R pour carte face cachée
            g.drawImage(this.list_cartes.get('R'), u, v);
            u += 80;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();

        //reset partie
        if (partieSet) {
            setPartie(nbrJoueur);
            partieSet = false;
        }

        if (Winner.getWinnersInt().size() > 0) {
            System.out.println(Winner.getWinnersInt().get(0));
        }

        //Joueur en cours
        if (nouveauTour) {
            this.currentTour++;
            this.currentPlayer = this.currentTour % Partie.nbrJoueur;
            while (Winner.getWinnersInt().contains(this.currentPlayer)) {
                this.currentTour++;
                this.currentPlayer++;
            }
            if (this.currentPlayer == 0) {
                this.currentPlayer = Partie.nbrJoueur;
            }
            System.out.println("Tour: " + currentTour);
            System.out.println("Tour du joueur " + this.currentPlayer);
            this.nouveauTour = false;
        }


        //Check for button Add
        if ((xpos > 410 && xpos < 560) && (ypos < 240 && ypos > 187)) {
            btnAdd = new Image("map/ADD-clicked.png");
            if (input.isMouseButtonDown(0)) {
                this.addToProgram.setTour(joueurs.get(this.currentPlayer - 1), plateau, this.list_cartes);
                this.nouveauTour = true;
                sbg.enterState(3);
                waitForClick();
            }
        }
        if ((xpos < 410 || xpos > 560) || (ypos > 240 || ypos < 187)) {
            btnAdd = new Image("map/ADD1.png");
        }

        //Check for button exe
        if ((xpos > 250 && xpos < 400) && (ypos < 240 && ypos > 187)) {
            btnExe = new Image("map/EXE-clicked.png");
            if (input.isMouseButtonDown(0)) {
                this.execProgram.setTour(joueurs.get(this.currentPlayer - 1), plateau, this.list_cartes, joueurs);
                this.nouveauTour = true;
                sbg.enterState(4);
                waitForClick();
            }
        }
        if ((xpos < 250 || xpos > 400) || (ypos > 240 || ypos < 187)) {
            btnExe = new Image("map/EXE1.png");
        }

        //Check for button Walls
        if ((xpos > 90 && xpos < 240) && (ypos < 240 && ypos > 187)) {
            btnWalls = new Image("map/Walls-clicked.png");
            if (input.isMouseButtonDown(0)) {
                this.buildWall.setTour(joueurs.get(this.currentPlayer - 1), plateau, this.list_cartes);
                this.nouveauTour = true;
                sbg.enterState(5);
                waitForClick();
            }
        }
        if ((xpos < 90 || xpos > 240) || (ypos > 240 || ypos < 187)) {
            btnWalls = new Image("map/Walls1.png");
        }


        //Mise à jour des positions des joueurs qui n'ont pas encore atteint le joyau
        for (Joueur joueur : joueurs) {
            if (!Winner.getWinners().contains(joueur)) {
                joueur.updateJoueur(plateau);
            }
        }

        //checkFinDePartie
        if (Winner.getWinners().size() == Partie.nbrJoueur - 1) {
            for (Joueur joueur : joueurs) {
                if (!Winner.getWinners().contains(joueur)) {
                    Winner.addToWinners(joueur);
                }
            }
            Winner.updateWinnerList();
            sbg.enterState(6);
        }
    }

    public void keyReleased(int key, char c) {

        if (Input.KEY_ESCAPE == key) {
            gc.exit();
        }

        switch (key) {
            case Input.KEY_2:
                nbrJoueur = 2;
                break;
            case Input.KEY_3:
                nbrJoueur = 3;
                break;
            case Input.KEY_4:
                nbrJoueur = 4;
                break;
        }


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

    public void setWinner(Winner winner) {
        this.winner = winner;
        ExecProgram.setWinner(winner);
    }


}
