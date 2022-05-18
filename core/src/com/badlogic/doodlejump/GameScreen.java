package com.badlogic.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    final DoodleJump game;
    World world;
    Renderer renderer;

    public GameScreen(final DoodleJump game){
        this.game = game;
        world = new World();
        renderer = new Renderer(game.batch, world);

        Assets.load();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
