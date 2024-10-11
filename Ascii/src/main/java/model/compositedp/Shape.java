package model.compositedp;

import model.Pair;
import model.Point;

/**
 *  shape of the game
 */
public interface Shape   {

    void move(int dx, int dy);
    boolean isInside(Point p);
   char getColor();
   void setColor(char color);
   Pair Bounds();
}
