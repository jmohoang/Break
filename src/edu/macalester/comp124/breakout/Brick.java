package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by John on 20-Oct-14.
 * Creates a GCompound that encompasses an individual brick in order to make the wall
 */
public class Brick extends GCompound {
    private GRect brick;
    public static final int BRICK_HEIGHT = 20;
    public static final int BRICK_WIDTH = 60;

    public Brick (Color f){
        brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(f);
        add(brick);
    }
}
