package com.company.Objects.Plateau;

import java.util.ArrayList;

public class Plateau {
    private static ArrayList<Case> cases;

    public Plateau() {

        cases = new ArrayList<>();
        int k = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                cases.add(k, new Case(i, j, ' '));
                k++;
            }
        }

    }

    //Retourne la case en fonction de sa position sur le plateau
    public static Case getCase(int x, int y) {
        for (Case current : cases) {
            if (current.getPosition(0) == x && current.getPosition(1) == y) {
                return current;
            }
        }
        return null;
    }

    public static ArrayList<Case> getCases() {
        return cases;
    }

    public static void setPlateau(int nbrJoueur) {
        switch (nbrJoueur) {
            case 2:
                //Affichage du joyau
                getCase(7, 3).setEtat('?');

                //Affichage des caisses en bois
                for (int i = 0; i < 8; i++) {
                    getCase(i, 7).setEtat('C');
                }
                break;
            case 3:
                //Affichage du joyau
                getCase(7, 0).setEtat('?');
                getCase(7, 3).setEtat('?');
                getCase(7, 6).setEtat('?');

                //Affichage des caisses en bois
                for (int i = 0; i < 8; i++) {
                    getCase(i, 7).setEtat('C');
                }
                break;
            case 4:
                //Affichage du joyau
                getCase(7, 1).setEtat('?');
                getCase(7, 6).setEtat('?');

        }

    }


}
