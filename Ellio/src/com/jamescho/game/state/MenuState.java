package com.jamescho.game.state;

import com.jamescho.game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState extends State{

    @Override
    public void init() {
        System.out.println("Entered MenuState");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Resources.welcome, 0, 0, null);
    }

    @Override
    public void onClick(MouseEvent event) {

    }

    @Override
    public void onKeyPress(KeyEvent event) {

    }

    @Override
    public void onKeyRelease(KeyEvent event) {

    }
}
