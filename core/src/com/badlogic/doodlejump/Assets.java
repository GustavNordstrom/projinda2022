package com.badlogic.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture playerImage;
    public static Texture platformImage;
    public static Texture springImage;

    public static void load(){
        // Load the images
        playerImage = new Texture(Gdx.files.internal("player.png"));
        platformImage = new Texture(Gdx.files.internal("platform.png"));
        springImage = new Texture(Gdx.files.internal("spring.png"));
    }
}
