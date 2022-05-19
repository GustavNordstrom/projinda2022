package com.badlogic.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture playerImage1;
    public static Texture playerImage2;
    public static Texture platformImage;
    public static Texture springImage;
    public static Sound springSound;
    public static Texture monsterLeftImage;
    public static Texture monsterRightImage;
    public static Sound monsterSound;

    public static void load(){
        // Load the images
        playerImage1 = new Texture(Gdx.files.internal("player1.png"));
        playerImage2 = new Texture(Gdx.files.internal("player2.png"));
        platformImage = new Texture(Gdx.files.internal("platform.png"));
        springImage = new Texture(Gdx.files.internal("spring.png"));
        monsterLeftImage = new Texture(Gdx.files.internal("monster_left.png"));
        monsterRightImage = new Texture(Gdx.files.internal("monster_right.png"));

        // Load sound
        springSound = Gdx.audio.newSound(Gdx.files.internal("spring.wav"));
        monsterSound = Gdx.audio.newSound(Gdx.files.internal("monster_roar.wav"));
    }
}
