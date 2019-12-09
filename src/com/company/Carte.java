package com.company;

public class Carte {
    static char carteTortue = 'T';
    static char carteJoyau = '?';

    static String[] deckCarte = new String[37];
    static char carteBleue = 'ß';//"Fait avancer la tortue d'une case.";
    static char carteJaune = 'J';//"Fait tourner la tortue dans le sens anti-horaire. Attention la tortue reste sur place.";
    static char carteViolette = 'V';//"Fait tourner la tortue dans le sens horaire. Attention la tortue reste sur place.";
    static char carteLaser = 'L';//"Utilise un laser pour attaquer ce qui est en face de la tortue.";

    static String[] deckObstacle = new String[36];
    static char murPierres = 'P';//"Obstacle inamovible et indestructible.";
    static char murGlace = 'G';//"Obstacle inamovible mais destructible par un laser.";
    static char caisseBois = 'C'; //"Obstacle amovible et indestructible.";

    static String[] deckDefausse;
}
