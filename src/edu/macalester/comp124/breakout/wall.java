package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by John on 20-Oct-14.
 * Uses ArrayLists to store the total number of bricks requires of the same color
 * Iterates over all the ArrayLists and makes a GCompound with all the bricks for the wall.
 */
public class Wall extends GCompound {
    private ArrayList<Brick> redRow;
    private ArrayList<Brick> orangeRow;
    private ArrayList<Brick> yellowRow;
    private ArrayList<Brick> cyanRow;
    private ArrayList<Brick> greenRow;

    public Wall() {
        redRow = new ArrayList<Brick>();
        orangeRow = new ArrayList<Brick>();
        yellowRow = new ArrayList<Brick>();
        greenRow = new ArrayList<Brick>();
        cyanRow = new ArrayList<Brick>();
        makeColorWall(Color.red, redRow, 0);
        makeColorWall(Color.orange, orangeRow, 2 * Brick.BRICK_HEIGHT);
        makeColorWall(Color.yellow, yellowRow, 4 * Brick.BRICK_HEIGHT);
        makeColorWall(Color.green, greenRow, 6 * Brick.BRICK_HEIGHT);
        makeColorWall(Color.cyan, cyanRow, 8*Brick.BRICK_HEIGHT);
    }

    /**
     * Creates bricks, gives each an appropriate color and stores them in an ArrayList
     * Iterates over each ArrayList and creates the wall
     * @param c  which is the color the bricks
     * @param a  a particular array in which to add the bricks of the same color
     * @param y  the y coordinate in which to add the first brick of a particular color
     */
    public void makeColorWall(Color c, ArrayList<Brick> a, int y) {
        for (int i = 0; i <= 19; i++) {
            a.add(new Brick(c));
        }
        int i = 0;
        int x = 0;
        while (i < a.size()){
            add(a.get(i),x, y);
            i++;
            x += Brick.BRICK_WIDTH;
            if (i == 10){
                y+= Brick.BRICK_HEIGHT;
                x = 0;
            }
        }
    }
}
