package com.badlogic.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends DynamicGameObject{
    public static final float PLAYER_WIDTH = 80;
    public static final float PLAYER_HEIGHT = 87;

    public Player(float x, float y){
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public void update(float delta){
        // Sideways movement player
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) position.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) position.x += 200 * Gdx.graphics.getDeltaTime();
        if(position.x < 0) position.x = 0;
        if(position.x > 500 - Player.PLAYER_WIDTH) position.x = 500 - Player.PLAYER_WIDTH;
        bounds.setX(position.x);
        bounds.setY(position.y);
    }

    public void hitPlatform(){
        velocity.y = 400;
        position.y += 5;
    }

    public void hitSpring(){
        Assets.springSound.play();
        velocity.y = 800;
        position.y += 5;
    }

    public void noHit(float delta){
        velocity.add(World.gravity.x * delta, World.gravity.y * delta);
        position.y += velocity.y * delta;
    }
}
