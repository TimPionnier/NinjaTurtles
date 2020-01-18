package com.company;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.HashMap;

public class Plateau {
    private final int NB_CASES = 64;

    public static char[][] plateau;
    private static int nbrJoueur = 2;
    private static ArrayList<Case> cases;

    public Plateau() throws SlickException {

        this.cases = new ArrayList<>();
        int k = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                this.cases.add(k, new Case(i, j, ' '));
                k++;
            }
        }

    }

    public static Case getCase(int i) {
        return cases.get(i);
    }

    public static ArrayList<Case> getCases() {
        return cases;
    }

    public void setPlateau() {

        for (int i = 0; i < this.cases.size(); i++) {
            if ((this.cases.get(i).getPosition(0) == 0 && this.cases.get(i).getPosition(1) == 1) ||
                    (this.cases.get(i).getPosition(0) == 0 && this.cases.get(i).getPosition(1) == 5)) {
                //this.cases.get(i).setEtat('1');
            }
            if (this.cases.get(i).getPosition(0) == 7 && this.cases.get(i).getPosition(1) == 3) {
                this.cases.get(i).setEtat('?');
            }
            if (this.cases.get(i).getPosition(1) == 7) {
                this.cases.get(i).setEtat('C');
            }
        }
        /*
        if(nbrJoueur == 2){
            plateau[0][1] = Carte.carteTortue;
            plateau[0][5] = Carte.carteTortue;
            plateau[7][3] = Carte.carteJoyau;
            for(int i = 0; i <= 7; i++){
                plateau[i][7] = Carte.caisseBois;
            }
        }else if(nbrJoueur == 3){
            plateau[0][0] = Carte.carteTortue;
            plateau[0][3] = Carte.carteTortue;
            plateau[0][6] = Carte.carteTortue;
            plateau[7][0] = Carte.carteJoyau;
            plateau[7][3] = Carte.carteJoyau;
            plateau[7][6] = Carte.carteJoyau;
            for(int i = 0; i <= 7; i++){
                plateau[i][7] = Carte.caisseBois;
            }
        }*/
    }


}
