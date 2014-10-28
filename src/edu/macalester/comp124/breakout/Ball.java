package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GOval;

import java.awt.*;

/**
 * Created by John on 20-Oct-14.
 */
public class Ball extends GCompound{
    public static final double BALL_DIAMETER = 7;
    private GOval ball;
    public Ball (){
        ball = new GOval(0,0, BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball);
    }
}
