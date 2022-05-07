package com.badlogic.doodlejump;

public class Player extends GameObject{
    public static final float PLAYER_WIDTH = 80;
    public static final float PLAYER_HEIGHT = 87;

    public Player(float x, float y){
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }
}
