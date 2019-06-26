package com.jamescho.game.main;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Resources {
    public static final String AUDIO_PATH = "/resources/";
    public static final String IMG_PATH = "/resources/";

    public static BufferedImage welcome, iconimage;
    public static void load() {
        welcome = loadImage("welcome.png");
        iconimage = loadImage("iconimage.png");
    }

    public static AudioClip loadSound(String filename) {
        URL fileURL = Resources.class.getResource(AUDIO_PATH + filename);
        return Applet.newAudioClip(fileURL);

    }

    public static BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Resources.class.getResourceAsStream(IMG_PATH + filename));
        } catch (IOException e) {
            System.out.println("Error while reading" + filename);
            e.printStackTrace();
        }
        return img;

    }
}
