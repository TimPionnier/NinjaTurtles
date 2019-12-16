package com.company;

import java.util.ArrayList;

public class Plateau {
    private final int NB_CASES = 64;

    public static char[][] plateau;
    private static int nbrJoueur = 2;
    private static ArrayList<Case> cases = new ArrayList<>();


    public Plateau() {
        for (int k = 0; k<NB_CASES; k++) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                     this.cases.set(k, new Case(i, j, ' '));
                }
            }
        }
    }

    public static ArrayList<Case> getCases() {
        return cases;
    }

    public static void setPlateau() {

        for (int i = 0;i < cases.size();i++){
            if ((cases.get(i).getPosition(0) == 0 && cases.get(i).getPosition(1) == 1) ||
                    (cases.get(i).getPosition(0) == 0 && cases.get(i).getPosition(1) == 5)){
                cases.get(i).setEtat('T');
            }
            if (cases.get(i).getPosition(0) == 7 && cases.get(i).getPosition(1) == 3){
                cases.get(i).setEtat('?');
            }
            if (cases.get(i).getPosition(1) == 7){
                cases.get(i).setEtat('C');
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
