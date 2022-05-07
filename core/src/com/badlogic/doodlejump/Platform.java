package com.badlogic.doodlejump;

public class Platform extends GameObject{
    public static final float PLATFORM_WIDTH = 100;
    public static final float PLATFORM_HEIGHT = 45;

    public Platform(float x, float y){
        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }
}
