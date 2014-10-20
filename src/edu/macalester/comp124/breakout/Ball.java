package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by John on 20-Oct-14.
 */
public class Ball extends GCompound{
    public static final double BALL_RADIUS = 15;

    private GOval ball;
    public Ball (){
        ball = new GOval(0,0, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        ball.setColor(Color.red);
        add(ball);
    }
}
