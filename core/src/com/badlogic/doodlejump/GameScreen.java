package com.badlogic.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    static final int GAME_OVER = 0;

    final DoodleJump game;
    World world;
    Renderer renderer;

    public GameScreen(final DoodleJump game){
        this.game = game;
        world = new World();
        renderer = new Renderer(game.batch, world, game);

        Assets.load();
    }

    @Override
    public void show() {

    }

    /**
     * Render the game screen
     *
     * @param delta the delta time between each update
     */
    @Override
    public void render(float delta) {
        if (world.state == World.WORLD_STATE_END) game.setScreen(new EndScreen(game));
        renderer.render();
        world.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
