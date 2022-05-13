package com.badlogic.doodlejump;

public class Spring extends GameObject{
    public static final float SPRING_WIDTH = 20;
    public static final float SPRING_HEIGHT = 25;

    public Spring(float x, float y) {
        super(x, y, SPRING_WIDTH, SPRING_HEIGHT);
    }
}
