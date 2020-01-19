package com.company;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

public class Cartes {

    protected static HashMap<Character,Image> cartes;
    Image woodBox = new Image("map/woodBox.png");
    Image turtle1S = new Image ("map/Donatelo40S.png");
    Image turtle1N = new Image ("map/Donatelo40N.png");
    Image turtle1O = new Image ("map/Donatelo40O.png");
    Image turtle1E = new Image ("map/Donatelo40E.png");
    Image turtle2S = new Image ("map/Leonardo40S.png");
    Image turtle2N = new Image ("map/Leonardo40N.png");
    Image turtle2O = new Image ("map/Leonardo40O.png");
    Image turtle2E = new Image ("map/Leonardo40E.png");


    public Cartes() throws SlickException {

        Image jewel = new Image("map/RUBY40.png");


        Image carteBleue = new Image("map/cards/BlueCard.png");
        Image carteLaser = new Image("map/cards/LaserCard.png");
        Image carteViolette = new Image("map/cards/PurpleCard.png");
        Image carteJaune = new Image("map/cards/YellowCard.png");
        Image carteBack = new Image("map/cards/backcard_petit.png");
        Image carteMurGlace = new Image("map/ICE.png");
        Image carteMurPierre = new Image("map/WALL.png");
        Image turtle3S = new Image("map/michelangeloS.png");
        Image turtle4 = new Image("map/rafaelo.png");

        cartes = new HashMap<>();

        cartes.put('C',woodBox);
        cartes.put('1',turtle1S);
        cartes.put('2',turtle2S);
        cartes.put('3', turtle3S);
        cartes.put('4', turtle4);
        cartes.put('?',jewel);
        cartes.put('B',carteBleue);
        cartes.put('L',carteLaser);
        cartes.put('V',carteViolette);
        cartes.put('J',carteJaune);
        cartes.put('R',carteBack);
        cartes.put('G',carteMurGlace);
        cartes.put('P',carteMurPierre);


    }
   /* public static void updateDirectionImage(){
        for (int i = 0; i < Partie.getJoueurs().size(); i++) {
            switch (Partie.getJoueurs().get(i).getDirection()){
                case 'N':
                    if (Partie.getJoueurs().get(i).getNumJoueur() == 1) {
                        cartes.remove(cartes.containsKey(1));
                        cartes.put('1',turtle1N);
                    } else  if (Partie.getJoueurs().get(i).getNumJoueur() == 2) {
                        cartes.remove(cartes.containsKey(2));
                        cartes.put('2',turtle2N);
                    }
                    break;
                case 'O':
                    if (Partie.getJoueurs().get(i).getNumJoueur() == 1) {
                        cartes.remove(cartes.containsKey(1));
                        cartes.put('1',turtle1O);
                    } else  if (Partie.getJoueurs().get(i).getNumJoueur() == 2) {
                        cartes.remove(cartes.containsKey(2));
                        cartes.put('2',turtle2O);
                    }
                    break;
                case 'S':
                    if (Partie.getJoueurs().get(i).getNumJoueur() == 1) {
                        cartes.remove(cartes.containsKey(1));
                        cartes.put('1',turtle1S);
                    } else  if (Partie.getJoueurs().get(i).getNumJoueur() == 2) {
                        cartes.remove(cartes.containsKey(2));
                        cartes.put('2',turtle2S);
                    }
                    break;
                case 'E':
                    if (Partie.getJoueurs().get(i).getNumJoueur() == 1) {
                        cartes.remove(cartes.containsKey(1));
                        cartes.put('1',turtle1E);
                    } else  if (Partie.getJoueurs().get(i).getNumJoueur() == 2) {
                        cartes.remove(cartes.containsKey(2));
                        cartes.put('2',turtle2E);
                    }
                    break;
            }
        }
    }*/


    public static HashMap<Character, Image> getCartes() {
        return cartes;
    }

    static char carteBleue = 'B';//"Fait avancer la tortue d'une case.";
    static char carteJaune = 'J';//"Fait tourner la tortue dans le sens anti-horaire. Attention la tortue reste sur place.";
    static char carteViolette = 'V';//"Fait tourner la tortue dans le sens horaire. Attention la tortue reste sur place.";
    static char carteLaser = 'L';//"Utilise un laser pour attaquer ce qui est en face de la tortue.";
    static char murPierres = 'P';//"Obstacle inamovible et indestructible.";
    static char murGlace = 'G';//"Obstacle inamovible mais destructible par un laser.";


}
