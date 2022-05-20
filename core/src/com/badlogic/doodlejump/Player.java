package com.badlogic.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends DynamicGameObject{
    public static final float PLAYER_WIDTH = 80;
    public static final float PLAYER_HEIGHT = 115;

    public boolean movingRight;

    public Player(float x, float y){
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    /**
     * Update movement of player
     *
     * @param delta the delta time between each update
     */
    public void update(float delta){
        // Sideways movement of player
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= 200 * Gdx.graphics.getDeltaTime();
            movingRight = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += 200 * Gdx.graphics.getDeltaTime();
            movingRight = true;
        }
        if(position.x + PLAYER_WIDTH / 2 < 0) position.x = 500 - PLAYER_WIDTH / 2;
        if(position.x > 500 - Player.PLAYER_WIDTH / 2) position.x = - Player.PLAYER_WIDTH / 2;
        bounds.setX(position.x);
        bounds.setY(position.y);
    }

    /**
     * Player hit a platform
     */
    public void hitPlatform(){
        Assets.jumpSound.play();
        velocity.y = 400;
        position.y += 5;
    }

    /**
     * Player hit a spring
     */
    public void hitSpring(){
        Assets.springSound.play();
        velocity.y = 800;
        position.y += 5;
    }

    /**
     * Player hit nothing
     *
     * @param delta the delta time between each update
     */
    public void noHit(float delta){
        velocity.add(World.gravity.x * delta, World.gravity.y * delta);
        position.y += velocity.y * delta;
    }
}
