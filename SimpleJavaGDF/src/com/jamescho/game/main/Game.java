package com.jamescho.game.main;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends JPanel {
    private int gameWidth;
    private int gameHeight;
    private Image gameImage;

    private Thread gameThread;
    private volatile boolean running;


    public Game(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.black);
        setFocusable(true);
        requestFocus();

    }
}
