package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {
    Paddle paddle1;
    Ball ball1;
    Wall wall1;
    double lastX;  //last x position of where the mouse pointer is
    double dX;
    double dY;
    double previousX;
    double previousY;  //I use these as a basis of whether to flip the move direction vertically or horizontally.
    boolean playOn;
    GameOverMessage message;
    double tries; //keeps track of how many turns have been played.
    int totalBrickNum; //Keeps track of the total number of bricks.
    RandomGenerator rgen;


    public BreakoutProgram (){
        tries = 1;
        rgen = new RandomGenerator();
    }


    public void init (){
        addMouseListeners();
    }
    public void mouseMoved (MouseEvent e){
        lastX = e.getX();
        paddle1.move(lastX - paddle1.getX(), 0);
        pause (5);

    }
    public void run() {
        totalBrickNum = 100;
        playOn = true;
        setSize(600, 700);
        paddle1 = new Paddle();
        add (paddle1, 0, 570);
        wall1 = new Wall();
        add(wall1);
        init();
        ball1 = new Ball();
        add (ball1,300,300);
        previousX = ball1.getX();
        previousY = ball1.getY();
        moveBall(ball1);
        message = new GameOverMessage();
        add(message, 200, 400);
    }

    public void moveBall (GCompound obj){
        dX = rgen.nextDouble(0, 5);
        dY = 2; //rgen.nextDouble(0.1, 2);
        if (rgen.nextBoolean(0.5)){ dX = -dX;}

        while (playOn){
            obj.move(dX, dY);
            pause(20);
            if (obj.getX() <= 0){dX = -dX;}
            if (obj.getY() <= 0) {dY = -dY;}
            if (obj.getX() + Ball.BALL_DIAMETER >= getWidth()){dX = -dX;}
            if (obj.getY() + Ball.BALL_DIAMETER >= getHeight()){
                if (tries < 3){
                tries+=1;
                removeAll();
                run();
                }
                if (tries>= 3){playOn = false;}
            }
            if (totalBrickNum <= 0){playOn = false;}
            //bouncing over the paddle
            //I defined all four corners of the ball square which may hit the paddle
            if (getElementAt(obj.getX(), obj.getY()) == paddle1 ||
            getElementAt(obj.getX()+Ball.BALL_DIAMETER, obj.getY()) == paddle1 ||
            getElementAt(obj.getX(), obj.getY()+Ball.BALL_DIAMETER) == paddle1 ||
            getElementAt(obj.getX()+Ball.BALL_DIAMETER, obj.getY()+Ball.BALL_DIAMETER) == paddle1)
            {dY = -dY;}

//            if (getElementAt(obj.getX()+Ball.BALL_DIAMETER, obj.getY()) == paddle1) {dY = -dY;}
//            if (getElementAt(obj.getX()+Ball.BALL_DIAMETER, obj.getY()+Ball.BALL_DIAMETER) == paddle1){dY = -dY;}
            //deleting the bricks
            deleteBricks();
            previousX = ball1.getX();
            previousY = ball1.getY();
        }
    }
    public void deleteBricks (){
        double x = ball1.getX();
        double y = ball1.getY(); //coordinates of the top left corner of the ball
        double xb = ball1.getX() + Ball.BALL_DIAMETER;
        double yb = ball1.getY() + Ball.BALL_DIAMETER;  //coordinates at the bottom right corner of the ball

        //Top left corner of the ball.
        if (ball1.getElementAt(x,y) != paddle1 &&
        wall1.contains(x,y)){
            if (previousY >= wall1.getElementAt(x,y).getY()+20 &&
                    wall1.getElementAt(x,y).getY()+20 >= y){dY = -dY;}
            if (previousX >= wall1.getElementAt(x,y).getX()+60 &&
                    wall1.getElementAt(x,y).getX()+60 >= x) {dX = -dX;}
            wall1.remove(wall1.getElementAt(x,y));
            totalBrickNum -= 1;

        }
        //Bottom right corner
        if (ball1.getElementAt(xb,yb) != paddle1 &&
                wall1.contains(xb,yb)){
            if (previousY <= wall1.getElementAt(xb,yb).getY() &&
                    wall1.getElementAt(xb,yb).getY() <= yb) {dY = -dY;}
            if (previousX <= wall1.getElementAt(xb,yb).getX() &&
                    wall1.getElementAt(xb,yb).getX() <= xb) {dX = -dX;}
            wall1.remove(wall1.getElementAt(xb,yb));
            totalBrickNum -= 1;
        }

        //Top right corner
        if (ball1.getElementAt(xb,y) != paddle1 &&
                wall1.contains(xb,y)){
            if (previousY >= wall1.getElementAt(xb,y).getY()+20 &&
                    wall1.getElementAt(xb,y).getY()+20 >= y){dY = -dY;}
            if (previousX <= wall1.getElementAt(xb,y).getX() &&
                    wall1.getElementAt(xb,y).getX() <= xb) {dX = -dX;}
            wall1.remove(wall1.getElementAt(xb,y));
            totalBrickNum -= 1;
        }

        //Bottom left corner
        if (ball1.getElementAt(x,yb) != paddle1 &&
                wall1.contains(x,yb)){
            if (previousY <= wall1.getElementAt(x,yb).getY() &&
                    wall1.getElementAt(x,yb).getY() <= yb) {dY = -dY;}
            if (previousX >= wall1.getElementAt(x,yb).getX()+60 &&
                    wall1.getElementAt(x,yb).getX()+60 >= x) {dX = -dX;}
            wall1.remove(wall1.getElementAt(x,yb));
            totalBrickNum -= 1;
        }

    }
}
