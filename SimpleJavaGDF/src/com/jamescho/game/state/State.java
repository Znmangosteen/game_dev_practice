package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {
    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics graphics);

    public abstract void onClick(MouseEvent event);

    public abstract void onKeyPress(KeyEvent event);

    public abstract void onKeyRelease(KeyEvent event);

    public void setCurrentState(State newState) {
        GameMain.sGame.setCurrentState(newState);
    }
}
