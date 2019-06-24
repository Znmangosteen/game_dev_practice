package com.jamescho.game.main;

import com.jamescho.game.state.LoadState;
import com.jamescho.game.state.State;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends JPanel {
    private int gameWidth;
    private int gameHeight;
    private Image gameImage;

    private Thread gameThread;
    private volatile boolean running;

    private volatile State currentState;

    public Game(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.black);
        setFocusable(true);
        requestFocus();

    }

    public void setCurrentState(State newState) {
        System.gc();
        newState.init();

        this.currentState = newState;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setCurrentState(new LoadState());

    }

}
