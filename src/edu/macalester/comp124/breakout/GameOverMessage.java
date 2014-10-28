package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GLabel;

import java.awt.*;

/**
 * Created by John on 27-Oct-14.
 * Creates a GCompound with a GLabel in it, to write ou the Game over message
 */
public class GameOverMessage extends GCompound {
    private GLabel message;

    public GameOverMessage (){
        message = new GLabel("GAME OVER");
        message.setColor(Color.BLACK);
        message.setFont("SansSerif-bold-50");
        add(message);
    }
}
