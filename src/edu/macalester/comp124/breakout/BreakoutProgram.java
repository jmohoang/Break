package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.program.GraphicsProgram;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {
    Paddle paddle1;
    Ball ball1;
    Wall wall1;
    Brick brick1;


    public void run() {
        paddle1 = new Paddle();
        add (paddle1);
        wall1 = new Wall();
        add(wall1);
        ball1 = new Ball();
        add (ball1);
//        brick1 = new Brick();
//        add(brick1);
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
            if (obj.getX() + Ball.BALL_DIAMETER >= getWidth()){dX = -dX;}
            if (obj.getY() + Ball.BALL_DIAMETER >= getHeight()){dY = -dY;}
        }
    }
}
