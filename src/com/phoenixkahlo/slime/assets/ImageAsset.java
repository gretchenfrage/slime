package com.phoenixkahlo.slime.assets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.NoSuchElementException;

/**
 * Created by Phoenix on 5/5/2017.
 */
public enum ImageAsset {

    FLAPPY("assets/flappy.png"),
    BACKGROUND_1("assets/background1.png"),
    MINECRAFT_BRICK("assets/Brick.png"),
    ;

    private Image image;

    ImageAsset(String path) {
        try {
            image = new Image(path);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Image image() {
        if (image == null)
            throw new NoSuchElementException();
        else
            return image;
    }

}
