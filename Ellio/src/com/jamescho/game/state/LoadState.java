package com.jamescho.game.state;

import com.jamescho.game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LoadState extends State {

    @Override
    public void init() {
        Resources.load();
        System.out.println("load successfully");
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Graphics graphics) {

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
