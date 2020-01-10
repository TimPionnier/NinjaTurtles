package com.company;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class Tour {

    private boolean debutTour = true;
    int xpos = Mouse.getX();
    int ypos = Mouse.getY();
    GameContainer gc;

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
        switch (key){
            case Input.KEY_Q:
                this.completerProgramme();
                break;
            case Input.KEY_B:
                this.construireMur(gc);
                break;
            case Input.KEY_C:
                this.executerProgramme();
                break;
        }
    }

    public void construireMur(GameContainer gc){
        Input input = gc.getInput();
        if ((xpos>260 && xpos<380) && (ypos<340 && ypos>297)){
            if (input.isMouseButtonDown(0)) {

            }
        }
    }

    public void completerProgramme(){

    }

    public void executerProgramme(){

    }

    public boolean isDebutTour() {
        return debutTour;
    }

    public void setDebutTour(boolean debutTour) {
        this.debutTour = debutTour;
    }
}
