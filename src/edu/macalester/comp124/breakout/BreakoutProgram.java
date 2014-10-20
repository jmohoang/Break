package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.util.ArrayList;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {
    Paddle paddle1;
    Ball ball1;


    public void run() {
        paddle1 = new Paddle();
        add (paddle1);
        ball1 = new Ball();
        add (ball1);
        moveBall(ball1);

    }
    public void moveBall (GCompound obj){
        double dX = 2;
        double dY = 2;
        while (true){
            obj.move(dX, dY);
            pause(20);
            if (obj.getX() <= 0){dX = -dX;}
            if (obj.getY() <= 0) {dY = -dY;}
            if (obj.getX() + Ball.BALL_RADIUS >= getWidth()){dX = -dX;}
            if (obj.getY() + Ball.BALL_RADIUS >= getHeight()){dY = -dY;}
        }
    }
}
