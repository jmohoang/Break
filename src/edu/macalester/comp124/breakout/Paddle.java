package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by John on 20-Oct-14.
 */
public class Paddle extends GCompound{
    GRect paddle;
    public Paddle(){
        paddle = new GRect(500,400, 60, 20);
        paddle.setFilled (true);
        paddle.setColor(Color.BLUE);
        add(paddle);
    }
}
