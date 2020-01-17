package com.company;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

public class Cartes {

    protected static HashMap<Character,Image> cartes;




    public Cartes() throws SlickException {
        Image woodBox = new Image("map/woodBox.png");
        Image jewel = new Image("map/RUBY40.png");
        Image turtle1 = new Image ("map/Donatelo40.png");
        Image turtle2 = new Image ("map/Leonardo40.png");
        Image carteBleue = new Image("map/cards/BlueCard.png");
        Image carteLaser = new Image("map/cards/LaserCard.png");
        Image carteViolette = new Image("map/cards/PurpleCard.png");
        Image carteJaune = new Image("map/cards/YellowCard.png");
        Image carteBack = new Image("map/cards/backcard_petit.png");
        Image carteMurGlace = new Image("map/ICE.png");
        Image carteMurPierre = new Image("map/WALL.png");

        cartes = new HashMap<>();

        cartes.put('C',woodBox);
        cartes.put('1',turtle1);
        cartes.put('2',turtle2);
        cartes.put('?',jewel);
        cartes.put('B',carteBleue);
        cartes.put('L',carteLaser);
        cartes.put('V',carteViolette);
        cartes.put('J',carteJaune);
        cartes.put('R',carteBack);
        cartes.put('G',carteMurGlace);
        cartes.put('P',carteMurPierre);

    }

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
