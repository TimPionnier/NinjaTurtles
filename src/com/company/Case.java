package com.company;

public class Case {
    private final Object Case = null;
    public int[] position = new int[2];
    public String etat;

    public Case(int i, int j, String etat) {
        this.position[0] = i;
        this.position[1] = j;
        this.etat = etat;

    }

    public Object getCase(){
        return Case;
    }

    public static void setPlateau(){
        Case[][] plateau = new Case[8][8];
        for (int i = 0; i< 7; i++){
            for(int j = 0; j < 7; j++ ){
                plateau[i][j] = new Case(i, j, "a") ;
                //plateau[i][j] = newCase;
                System.out.println(plateau[i][j]);
            }
        }

    }

}