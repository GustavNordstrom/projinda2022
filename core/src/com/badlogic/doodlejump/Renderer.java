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
    final DoodleJump game;

    public Renderer(SpriteBatch batch, World world, DoodleJump game){
        this.game = game;
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
        //End game if player falls below camera
        if (world.player.position.y < camera.position.y - 400) world.state = World.WORLD_STATE_END;
        batch.setProjectionMatrix(camera.combined);
        renderObjects();
    }

    private void renderObjects() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        // Render player, platforms, springs and monsters
        batch.begin();
        for (int i = 0; i < world.platforms.size; i++){
            batch.draw(Assets.platformImage, world.platforms.get(i).position.x, world.platforms.get(i).position.y);
        }
        for (int i = 0; i < world.springs.size; i++){
            batch.draw(Assets.springImage, world.springs.get(i).position.x, world.springs.get(i).position.y);
        }
        for (int i = 0; i < world.monsters.size; i++){
            world.monsters.get(i).move();
            if (world.monsters.get(i).movingRight) {
                batch.draw(Assets.monsterRightImage, world.monsters.get(i).position.x, world.monsters.get(i).position.y);
            }
            else {
                batch.draw(Assets.monsterLeftImage, world.monsters.get(i).position.x, world.monsters.get(i).position.y);
            }
        }
        batch.draw(Assets.playerImage, world.player.position.x, world.player.position.y);
        batch.end();
    }
}
