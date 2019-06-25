package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.model.Ball;
import com.jamescho.game.model.Paddle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayState extends State {
    private Paddle paddleLeft, paddleRight;
    private Ball ball;

    private static final int PADDLE_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 60;
    private static final int BALL_DIAMETER = 20;


    private int playerScore = 0;
    private Font scoreFont;

    private boolean ready = false;

    @Override
    public void init() {
        paddleLeft = new Paddle(0, 195, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddleRight = new Paddle(785, 195, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new Ball(300, 200, BALL_DIAMETER, BALL_DIAMETER);
        scoreFont = new Font("SansSerif", Font.BOLD, 25);

        ready = true;

        System.out.println("Entered PlayState");
    }

    @Override
    public void update() {
        if (!ready) {
            return;
        }
        paddleLeft.update();
        paddleRight.update();
        ball.update();

        if (ballCollides(paddleLeft)) {
            playerScore++;
            ball.onCollideWith(paddleLeft);
            Resources.hit.play();
        } else if (ballCollides(paddleRight)) {
            playerScore++;
            ball.onCollideWith(paddleRight);
            Resources.hit.play();
        } else if (ball.isDead()) {
            playerScore -= 3;
            ball.reset();
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (!ready) {
            return;
        }
//        draw background
        graphics.setColor(Resources.darkBlue);
        graphics.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        graphics.setColor(Resources.darkRed);
        graphics.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);
//        draw separator line
        graphics.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) - 2, 0, null);

//        draw paddles
        graphics.setColor(Color.white);
        graphics.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
        graphics.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());

//        draw ball
        graphics.drawRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

//        draw UI
        graphics.setFont(scoreFont);
        graphics.drawString("" + playerScore, 350, 40);
    }

    @Override
    public void onClick(MouseEvent event) {

    }

    @Override
    public void onKeyPress(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            paddleLeft.accelUp();
            paddleRight.accelDown();
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddleLeft.accelDown();
            paddleRight.accelUp();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_UP) {
            paddleLeft.stop();
            paddleRight.stop();
        }
    }

    private boolean ballCollides(Paddle p) {
        return ball.getRect().intersects(p.getRect());
    }
}
