package com.company.Objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

public class Cartes {

    private static HashMap<Character, Image> cartes;


    public Cartes() throws SlickException {

        //Lien entre les états des cases et l'affichage de l'image correspondantes
        Image jewel = new Image("map/RUBY40.png");
        Image woodBox = new Image("map/woodBox.png");
        Image turtle1S = new Image("map/Donatelo40S.png");
        Image turtle2S = new Image("map/Leonardo40S.png");
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

        cartes.put('C', woodBox);
        cartes.put('1', turtle1S);
        cartes.put('2', turtle2S);
        cartes.put('3', turtle3S);
        cartes.put('4', turtle4);
        cartes.put('?', jewel);
        cartes.put('B', carteBleue);
        cartes.put('L', carteLaser);
        cartes.put('V', carteViolette);
        cartes.put('J', carteJaune);
        cartes.put('R', carteBack);
        cartes.put('G', carteMurGlace);
        cartes.put('P', carteMurPierre);
    }

    public static HashMap<Character, Image> getCartes() {
        return cartes;
    }
}
