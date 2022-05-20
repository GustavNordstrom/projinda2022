package com.badlogic.doodlejump;

import com.badlogic.gdx.utils.Pool;

public class Platform extends GameObject implements Pool.Poolable{
    public static final float PLATFORM_WIDTH = 100;
    public static final float PLATFORM_HEIGHT = 45;

    public boolean onScreen;

    public Platform(){
        super(0, 0, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    public void init(float posX, float posY){
        position.set(posX, posY);
        bounds.set(posX, posY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        onScreen = true;
    }

    @Override
    public void reset() {
        position.set(0, 0);
        onScreen = false;
    }
}
