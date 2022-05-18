package com.badlogic.doodlejump;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class World {
    public static final Vector2 gravity = new Vector2(0, -300);
    public static final int WORLD_STATE_END = 0;
    public static final int WORLD_STATE_RUNNING = 1;

    public final Player player;
    public Array<Platform> platforms;
    public Array<Spring> springs;
    public Array<Monster> monsters;
    public int state;

    public World(){
        this.player = new Player(250, 200);
        this.platforms = new Array<>();
        this.springs = new Array<>();
        this.monsters = new Array<>();
        this.state = WORLD_STATE_RUNNING;
        generateWorld();
    }

    public void update(float delta){
        player.update(delta);
        checkCollisions(delta);
    }

    private void checkCollisions(float delta) {
        checkSpringCollision();
        checkPlatformCollision(delta);
        checkMonsterCollision();
    }

    private void checkMonsterCollision() {
        for (int i = 0; i < monsters.size; i++){
            if (monsters.get(i).bounds.overlaps(player.bounds)) {
                Assets.monsterSound.play();
                //Game over
                state = WORLD_STATE_END;
                //game.setScreen(new EndScreen(game));
            }
        }
    }

    private void checkSpringCollision() {
        for (int i = 0; i < springs.size; i++){
            if (springs.get(i).position.y < player.position.y) {
                if (springs.get(i).bounds.overlaps(player.bounds)) {
                    if (player.velocity.y < 0) player.hitSpring();
                }
            }
        }
    }

    private void checkPlatformCollision(float delta) {
        for (int i = 0; i < platforms.size; i++){
            if (platforms.get(i).position.y < player.position.y) {
                if (platforms.get(i).bounds.overlaps(player.bounds)) {
                    if (player.velocity.y < 0) {
                        player.hitPlatform();
                    }
                }
            }
        }
        player.noHit(delta);
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
            generateSpring(platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT, Platform.PLATFORM_WIDTH);
            generateMonster(platform.position.x, platform.position.y, Platform.PLATFORM_HEIGHT, Platform.PLATFORM_WIDTH);
            platforms.add(platform);
        }
    }

    private void generateSpring(float x, float y, float width){
        Random rng = new Random();
        if (rng.nextInt(10) < 7) return;
        Spring spring = new Spring(rng.nextInt((int) (width - Spring.SPRING_WIDTH)) + x, y);
        springs.add(spring);
    }

    private void generateMonster(float x, float y, float height, float width) {
        Random rng = new Random();
        if (rng.nextInt(10) < 9) return;
        Monster monster = new Monster(x, y + height, width);
        monsters.add(monster);
    }
}
