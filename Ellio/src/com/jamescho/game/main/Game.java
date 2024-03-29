package com.jamescho.game.main;

import com.jamescho.game.state.LoadState;
import com.jamescho.game.state.State;
import com.jamescho.game.util.InputHandler;

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

    private InputHandler inputHandler;

    public Game(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.black);
        setFocusable(true);
        requestFocus();

    }

    private void initInput() {
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
    }

    public void setCurrentState(State newState) {
        System.gc();
        currentState = newState;
        newState.init();
        inputHandler.setCurrentState(currentState);

    }

    @Override
    public void addNotify() {
        super.addNotify();

        initInput();

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

        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        while (running) {
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = updateDurationMillis + sleepDurationMillis;

            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);

            try {
                Thread.sleep(sleepDurationMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    private void updateAndRender(long deltaMillis) {
        currentState.update(deltaMillis / 1000f);
        prepareGameImage();
        currentState.render(gameImage.getGraphics());
        renderGameImage(getGraphics());
    }

    private void prepareGameImage() {
        if (gameImage == null) {
            gameImage = createImage(gameWidth, gameHeight);
        }
        Graphics g = gameImage.getGraphics();
        g.clearRect(0, 0, gameWidth, gameHeight);
    }

    private void renderGameImage(Graphics g) {
        if (gameImage != null) {
            g.drawImage(gameImage, 0, 0, null);
        }

        g.dispose();
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
