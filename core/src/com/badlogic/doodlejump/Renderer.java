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

        // Sideways movement player
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) world.player.position.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) world.player.position.x += 200 * Gdx.graphics.getDeltaTime();
        if(world.player.position.x < 0) world.player.position.x = 0;
        if(world.player.position.x > 500 - Player.PLAYER_WIDTH) world.player.position.x = 500 - Player.PLAYER_WIDTH;
        world.player.bounds.setX(world.player.position.x);

        // Player falling
        if (!platformCollision()){
            world.player.velocity.add(World.gravity.x * Gdx.graphics.getDeltaTime(), World.gravity.y * Gdx.graphics.getDeltaTime());
            world.player.position.y += world.player.velocity.y * Gdx.graphics.getDeltaTime();
        } else if (springCollision()){
            Assets.springSound.play();
            world.player.velocity.y = 800;
            world.player.position.y += 5;
        } else if (monsterCollision()) {
            //Game over
        }
        else {
            world.player.velocity.y = 400;
            world.player.position.y += 5;
        }
        world.player.bounds.setY(world.player.position.y);
    }

    //In this class temporarily
    private boolean platformCollision(){
        for (int i = 0; i < world.platforms.size; i++){
            if (world.platforms.get(i).position.y < world.player.position.y) {
                if (world.platforms.get(i).bounds.overlaps(world.player.bounds)) {
                    if (world.player.velocity.y < 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean springCollision(){
        for (int i = 0; i < world.springs.size; i++){
            if (world.springs.get(i).bounds.overlaps(world.player.bounds)) {
                return true;
            }
        }
        return false;
    }

    private boolean monsterCollision(){
        for (int i = 0; i < world.monsters.size; i++){
            if (world.monsters.get(i).bounds.overlaps(world.player.bounds)) {
                return true;
            }
        }
        return false;
    }
}
