package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *Created by John Mohoang
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

    /**
     * Constructor for the breakout game.
     * I used it mainly to construct the tries which holds the number of times the run method has been called
     */
    public BreakoutProgram (){
        tries = 1;
        rgen = new RandomGenerator();
    }

    /**
     * The init method was used to add the mouse listener.
     */
    public void init (){
        addMouseListeners();
    }

    /**
     * The method that describes the mouseMoved event.
     * The mouseMoved is used to move the paddle such that it follows the x coordinate of the mouse pointer
     * @param e
     */
    public void mouseMoved (MouseEvent e){
        lastX = e.getX();
        paddle1.move(lastX - paddle1.getX(), 0);
        pause (5);

    }

    /**
     * Main run function of the breakout game
     * When this run function is called, the game will play.
     */
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

    /**
     * Takes in a GObject which in the case of the breakout game is supposed to be the ball.
     * It drops off the ball towards the paddle at a random angle
     * and it also keeps the ball moving until either the ball misses the paddle or the bricks are all hit
     * @param obj
     */
    public void moveBall (GCompound obj){
        dX = rgen.nextDouble(-2, 2);
        dY = rgen.nextDouble(1, 2);

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
            bounceOverPaddle(obj);
            deleteBricks();
            previousX = ball1.getX();
            previousY = ball1.getY();
        }
    }

    /**
     * Takes in the ball object and defines bouncing over the paddle
     * I defined all four corners of the ball square which may hit the paddle
     * @param obj
     */
    public void bounceOverPaddle (GCompound obj){
        if (getElementAt(obj.getX(), obj.getY()) == paddle1) {dX = -dX;}
        if  (getElementAt(obj.getX()+Ball.BALL_DIAMETER, obj.getY()) == paddle1) {dX = -dX;}
        if (getElementAt(obj.getX(), obj.getY()+Ball.BALL_DIAMETER) == paddle1 &&
                getElementAt(obj.getX()+Ball.BALL_DIAMETER, obj.getY()+Ball.BALL_DIAMETER) != paddle1) {dY = -dY;}
        if (getElementAt(obj.getX()+Ball.BALL_DIAMETER, obj.getY()+Ball.BALL_DIAMETER) == paddle1) {dY = -dY;} //Gets preference
    }

    /**
     * This method is used to make the bricks disappear when the are hit by the ball.
     * All the four corners of the ball square are defined
     * as well each of the sides of a brick that each corner of the ball square is likely to come in contact with
     * such that the ball bounces appropriately when it hits a brick on the sides
     * as compared to either the top or bottom
     */
    public void deleteBricks (){
        double x = ball1.getX();
        double y = ball1.getY(); //coordinates of the top left corner of the ball
        double xb = ball1.getX() + Ball.BALL_DIAMETER;
        double yb = ball1.getY() + Ball.BALL_DIAMETER;  //coordinates at the bottom right corner of the ball


        //Top right corner
        //This corner has to hit the brick by itself otherwise it won't be given preference
        if (ball1.getElementAt(xb,y) != paddle1 &&
                wall1.contains(xb,y) &&
                !wall1.contains(x,y) &&
                !wall1.contains(x, yb) &&
                !wall1.contains(xb, yb)){
            if (previousY >= wall1.getElementAt(xb,y).getY()+20 &&
                    wall1.getElementAt(xb,y).getY()+20 >= y){dY = -dY;}
            if (previousX <= wall1.getElementAt(xb,y).getX() &&
                    wall1.getElementAt(xb,y).getX() <= xb) {dX = -dX;}
            wall1.remove(wall1.getElementAt(xb,y));
            totalBrickNum -= 1;
        }

        //Bottom left corner
        //This corner has to hit the brick by itself otherwise it won't be given preference
        if (ball1.getElementAt(x,yb) != paddle1 &&
                wall1.contains(x,yb) &&
                !wall1.contains(x,y) &&
                !wall1.contains(xb, y) &&
                !wall1.contains(xb,yb)){
            if (previousY <= wall1.getElementAt(x,yb).getY() &&
                    wall1.getElementAt(x,yb).getY() <= yb) {dY = -dY;}
            if (previousX >= wall1.getElementAt(x,yb).getX()+60 &&
                    wall1.getElementAt(x,yb).getX()+60 >= x) {dX = -dX;}
            wall1.remove(wall1.getElementAt(x,yb));
            totalBrickNum -= 1;
        }
        //Bottom right corner
        if (ball1.getElementAt(xb,yb) != paddle1 &&
                wall1.contains(xb,yb) &&
                !wall1.contains(x,y)){  //makes sure that Top left corner has more preference over this one
            if (previousY <= wall1.getElementAt(xb,yb).getY() &&
                    wall1.getElementAt(xb,yb).getY() <= yb) {dY = -dY;}
            if (previousX <= wall1.getElementAt(xb,yb).getX() &&
                    wall1.getElementAt(xb,yb).getX() <= xb) {dX = -dX;}
            wall1.remove(wall1.getElementAt(xb,yb));
            totalBrickNum -= 1;
        }

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
    }
}
