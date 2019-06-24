package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayState extends State {
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Resources.darkBlue);
        graphics.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        graphics.setColor(Resources.darkRed);
        graphics.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);

        graphics.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) -2, 0, null);
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
