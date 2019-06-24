package com.jamescho.game.main;

import com.jamescho.game.state.LoadState;
import com.jamescho.game.state.State;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {
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
        initGame();
    }

    public void initGame() {
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();

    }


    @Override
    public void run() {
        while (running) {
            currentState.update();
            prepareGameImage();
            currentState.render(gameImage.getGraphics());
            repaint();
            try {
                Thread.sleep(14);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    private void prepareGameImage() {
        if (gameImage == null) {
            gameImage = createImage(gameWidth, gameHeight);
        }
        Graphics g = gameImage.getGraphics();
        g.clearRect(0, 0, gameWidth, gameHeight);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (gameImage == null) {
            return;
        }
        graphics.drawImage(gameImage, 0, 0, null);
    }


    public void exit() {
        running = false;
    }
}
