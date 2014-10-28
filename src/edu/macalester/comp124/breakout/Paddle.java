package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by John on 20-Oct-14.
 * Creates a GCompound that encompasses the GRect that is used as the paddle
 */
public class Paddle extends GCompound {
    GRect paddle;

    public Paddle() {
        paddle = new GRect(0, 0, 60, 3);
        paddle.setFilled(true);
        paddle.setColor(Color.BLUE);
        add(paddle);
    }
}
