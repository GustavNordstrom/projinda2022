package com.badlogic.doodlejump;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class World {
    public static final Vector2 gravity = new Vector2(0, -300);

    public final Player player;
    public Array<Platform> platforms;
    public Array<Spring> springs;

    public World(){
        this.player = new Player(250, 200);
        this.platforms = new Array<>();
        this.springs = new Array<>();
        generateWorld();
    }

    private void generateWorld(){
        //Generate starting platform
        Platform firstPlat = new Platform(250, 0);
        platforms.add(firstPlat);
    }

    public void generatePlatforms(){
        int platformY = (int) platforms.get(platforms.size-1).position.y;
        if(platformY - player.position.y >= 400) return;
        Random rng = new Random();
        for (int i = 1; i < 11; i++) {
            Platform platform = new Platform(rng.nextInt(400), rng.nextInt(100) + 200* i + platformY);
            generateSpring(platform.position.x, platform.position.y + platform.PLATFORM_HEIGHT, platform.PLATFORM_WIDTH);
            platforms.add(platform);
        }
    }

    private void generateSpring(float x, float y, float width){
        Random rng = new Random();
        if (rng.nextInt(10) < 2) return;
        Spring spring = new Spring(rng.nextInt((int) width) + x, y);
        springs.add(spring);
    }
}
