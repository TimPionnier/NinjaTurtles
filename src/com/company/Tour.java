package com.company;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Tour {

    private boolean debutTour = true;

    public void init() throws SlickException {

    }

    public void renderChoixTour(Graphics g) throws SlickException {
        String txt =
                "Quelle action souhaitez-vous effectuer ?\n" +
                "A- Compléter votre programme\n" +
                "B- Construir un mur\n" +
                "C- Executer votre programme";
        g.drawString(txt,130,600);
    }

    public void keyReleased(int key, char c) {

    }

    public boolean isDebutTour() {
        return debutTour;
    }

    public void setDebutTour(boolean debutTour) {
        this.debutTour = debutTour;
    }
}
