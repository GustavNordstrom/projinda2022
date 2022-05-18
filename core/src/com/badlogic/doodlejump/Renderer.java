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
        //Move camera when player jumps higher
        if (world.player.position.y > camera.position.y) camera.position.y = world.player.position.y;
        batch.setProjectionMatrix(camera.combined);
        world.generatePlatforms();
        renderObjects();
    }

    private void renderObjects() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        // Render player and platforms
        batch.begin();
        for (int i = 0; i < world.platforms.size; i++){
            batch.draw(Assets.platformImage, world.platforms.get(i).position.x, world.platforms.get(i).position.y);
        }
        for (int i = 0; i < world.springs.size; i++){
            batch.draw(Assets.springImage, world.springs.get(i).position.x, world.springs.get(i).position.y);
        }
        batch.draw(Assets.playerImage, world.player.position.x, world.player.position.y);
        batch.end();
    }
}
