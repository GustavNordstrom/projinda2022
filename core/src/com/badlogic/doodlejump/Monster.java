package com.badlogic.doodlejump;

public class Monster extends GameObject{
    public static final float MONSTER_WIDTH = 53;
    public static final float MONSTER_HEIGHT = 65;
    private static final float speed = 1;
    public boolean movingRight;
    private float startingPoint;
    private float range;

    public Monster(float x, float y, float platformWidth) {
        super(x, y-10, MONSTER_WIDTH, MONSTER_HEIGHT);
        movingRight = true;
        startingPoint = x;
        range = platformWidth;
    }

    public void direction(){
        if (super.position.x <= startingPoint) {
            movingRight = true;
            return;
        }
        if (super.position.x >= startingPoint + range - MONSTER_WIDTH) movingRight = false;
    }

    public void move(){
        direction();
        if (movingRight) {
            super.position.x += speed;
        }
        else {
            super.position.x -= speed;
        }
    }
}
