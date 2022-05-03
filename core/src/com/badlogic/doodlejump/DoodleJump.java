package com.badlogic.doodlejump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class DoodleJump extends ApplicationAdapter {
    private Texture playerImage;
    private Texture platformImage;
    private Rectangle player;
    private Array<Rectangle> platforms;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    @Override
    public void create () {
        // Load the images
        playerImage = new Texture(Gdx.files.internal("player.png"));
        platformImage = new Texture(Gdx.files.internal("platform.png"));

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 500, 600);

        // Create SpriteBatch
        batch = new SpriteBatch();

        // Player's starting position
        player = new Rectangle();
        player.width = 80;
        player.height = 87;
        player.x = 500 / 2 - player.width / 2;
        player.y = 400;

        // Instantiate array of platforms
        platforms = new Array<>();

        // A platform
        Rectangle platform = new Rectangle();
        platform.width = 100;
        platform.height = 45;
        platform.x = 500 / 2 - platform.width / 2;
        platform.y = 5;
        platforms.add(platform);
    }

    @Override
    public void render () {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        // Render player and platforms
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(playerImage, player.x, player.y);
        for(Rectangle platform: platforms) {
            batch.draw(platformImage, platform.x, platform.y);
        }
        batch.end();

        // Sideways movement player
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += 200 * Gdx.graphics.getDeltaTime();
        if(player.x < 0) player.x = 0;
        if(player.x > 500 - player.width) player.x = 500 - player.width;

        // Player falling
        if (!platformCollision()){
            player.y -= 50 * Gdx.graphics.getDeltaTime();
        }
    }

    // TODO:
    // Make it so that only contact with the top of the
    // platform from above is counted as a collision
    private boolean platformCollision(){
        for (Rectangle platform : platforms) {
            if(platform.overlaps(player)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispose () {
    }
}
