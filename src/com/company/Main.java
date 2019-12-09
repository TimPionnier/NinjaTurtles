package com.company;

import org.newdawn.slick.*;

public class Main {

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 640, 480, false).start();
        System.out.println("salut les ptits potes");
    }
}
