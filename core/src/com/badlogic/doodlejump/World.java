package com.badlogic.doodlejump;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

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

    private final Pool<Platform> platformPool = new Pool<Platform>() {
        @Override
        protected Platform newObject() {
            return new Platform();
        }
    };

    public World(){
        this.player = new Player(250, 200);
        this.platforms = new Array<>();
        this.springs = new Array<>();
        this.monsters = new Array<>();
        this.state = WORLD_STATE_RUNNING;
        generateWorld();
    }

    /**
     * Update all objects, including platforms, monsters, player and springs
     *
     * @param delta the delta time between each update
     */
    public void update(float delta){
        player.update(delta);
        checkCollisions(delta);
        checkOffScreenItems();
        generatePlatforms();
        addPlatformsToPool();
    }

    private void checkOffScreenItems() {
        for (int i = 0; i < platforms.size; i++){
            if (player.position.y - platforms.get(i).position.y > 400){
                platforms.get(i).onScreen = false;
            }
        }
    }

    private void addPlatformsToPool(){
        Platform platform;
        int len = platforms.size;
        for (int i = len; --i >= 0;) {
            platform = platforms.get(i);
            if (!platform.onScreen) {
                platforms.removeIndex(i);
                platformPool.free(platform);
            }
        }
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
        Platform platform = platformPool.obtain();
        platform.init(250, 0);
        platforms.add(platform);
    }

    /**
     * Generate new platforms for the player to jump on, starting above the current highest platform.
     */
    public void generatePlatforms(){
        int platformY = (int) platforms.get(platforms.size-1).position.y;
        if(platformY - player.position.y >= 400) return;
        Random rng = new Random();
        for (int i = 1; i < 6; i++) {
            Platform platform = platformPool.obtain();
            platform.init(rng.nextInt(400), rng.nextInt(100) + 200* i + platformY);
            platforms.add(platform);
            generateSpring(platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT, Platform.PLATFORM_WIDTH);
            generateMonster(platform.position.x, platform.position.y, Platform.PLATFORM_HEIGHT, Platform.PLATFORM_WIDTH);
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
