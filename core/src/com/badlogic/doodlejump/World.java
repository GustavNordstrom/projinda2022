package com.badlogic.doodlejump;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
    public static final Vector2 gravity = new Vector2(0, -300);

    public final Player player;
    public Array<Platform> platforms;

    public World(){
        this.player = new Player(300, 400);
        this.platforms = new Array<>();
        generateWorld();
    }

    private void generateWorld(){
        Platform platform = new Platform(300, 100);
        platforms.add(platform);
    }
}
