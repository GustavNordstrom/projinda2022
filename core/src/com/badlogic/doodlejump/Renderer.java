package com.badlogic.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Renderer {
    World world;
    SpriteBatch batch;
    private OrthographicCamera camera;

    public Renderer(SpriteBatch batch, World world){
        this.batch = batch;
        this.world = world;

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 500, 600);
    }

    public void render(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderObjects();
    }

    private void renderObjects() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        // Render player and platforms
        batch.begin();
        for (int i = 0; i < world.platforms.size; i++){
            batch.draw(Assets.platformImage, world.platforms.get(i).position.x, world.platforms.get(i).position.y);
        }
        batch.draw(Assets.playerImage, world.player.position.x, world.player.position.y);
        batch.end();

        // Sideways movement player
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) world.player.position.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) world.player.position.x += 200 * Gdx.graphics.getDeltaTime();
        if(world.player.position.x < 0) world.player.position.x = 0;
        if(world.player.position.x > 500 - Player.PLAYER_WIDTH) world.player.position.x = 500 - Player.PLAYER_WIDTH;
        world.player.bounds.setX(world.player.position.x);

        // Player falling
        if (!platformCollision()){
            world.player.position.y -= 50 * Gdx.graphics.getDeltaTime();
            world.player.bounds.setY(world.player.position.y);
        }

    }

    //In this class temporarily
    // TODO:
    // Make it so that only contact with the top of the
    // platform from above is counted as a collision
    private boolean platformCollision(){
        for (int i = 0; i < world.platforms.size; i++){
            if (world.platforms.get(i).bounds.overlaps(world.player.bounds)){
                return true;
            }
        }
        return false;
    }
}
