package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.program.GraphicsProgram;

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



    public void init (){
        addMouseListeners();
    }
    public void mouseMoved (MouseEvent e){
        lastX = e.getX();
        paddle1.move(lastX - paddle1.getX(), 0);
        pause (20);

    }
    public void run() {
        paddle1 = new Paddle();
        add (paddle1);
        wall1 = new Wall();
        add(wall1);
        init();
        ball1 = new Ball();
        add (ball1);
        remove(wall1.getElementAt(0,0));
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
            //deleteBricks();
            if (deleteBricks() == true){
                dY = -dY;
            }

        }
    }

    public boolean deleteBricks (){
        double x = ball1.getX();
        double y = ball1.getY(); //coordinates of the top left corner of the ball
        if (ball1.getElementAt(getX(),getY()) != paddle1 &&
        wall1.contains(x,y)){
            //wall1.getElementAt(x,y).setVisible(false);
            wall1.remove(wall1.getElementAt(x,y));
            return true;
        }
        return false;
    }
}
