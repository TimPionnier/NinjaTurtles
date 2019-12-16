package com.company;

public class Plateau {
    public static char[][] plateau;
    private static int nbrJoueur = 2;

    public static char[][] getPlateau() {
        return plateau;
    }


    public static void setPlateau(char[][]plateau) {
        Plateau.plateau = plateau;

        for (int i=0 ; i<=7 ; i++) {
            for (int j=0 ; j<=7 ; j++) {
                plateau[i][j] = ' ';
            }
        }

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
        }
    }

}
