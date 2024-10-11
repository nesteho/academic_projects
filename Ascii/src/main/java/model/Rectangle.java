package model;

import model.compositedp.ColoredShape;
/**
 * Represents a rectangle
 */
public class Rectangle extends ColoredShape {
    private final Point upperLeft;
    private final int height;
    private final int width;

    /**
     * Constructor of a Rectangle
     * @param ul the upper left point of the rectangle
     * @param h the height of the rectangle
     * @param w the width  of the rectangle
     * @param color the color of the rectangle
     */
    public Rectangle(Point ul, int h,int w, char color ){

        super(color);
        if (h <= 0){
            throw new IllegalArgumentException("Height must be strictly positive!");
        }
        if (w <= 0){
            throw new IllegalArgumentException("Width must be strictly positive!");
        }

        this.upperLeft = ul;
        this.height = h;
        this.width = w;
    }

    /**
     * Calculates the bounds of the rectangle
     * @return the bounds of the rectangle
     */
    public Pair Bounds(){

        Point upLeft = new Point(upperLeft);
        Point downRight = new Point(upperLeft.getX()+width, upperLeft.getY()+height);

        return new Pair(upLeft,downRight);
    }

    /**
     * Moves the rectangle
     * @param dx number and sens of horizontal moving
     * @param dy number and sens of vertical moving
     */
    @Override
    public void move(int dx, int dy) {
        upperLeft.move(dx, dy);
    }

    /**
     * Calculates whether a given point is in the rectangle
     * @param p the given point
     * @return whether the point is in the rectangle
     */
    @Override
    public boolean isInside(Point p) {
        return p.getX() >= upperLeft.getX()
                &&  p.getX() <=  upperLeft.getX() + width
                && p.getY() >= upperLeft.getY()
                &&  p.getY() <= upperLeft.getY() + height;
    }

    /**
     * Getter of the upper left point of the rectangle
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Getter of the rectangle height
     * @return the rectangle height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter of the rectangle width
     * @return the rectangle width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns a string representation of the rectangle
     * @return a string representation of the rectangle
     */
    @Override
    public String toString() {
        return "Rectangle{ " +
                "upperLeft = " + upperLeft +
                ", height = " + height +
                ", width = " + width +
                ", color = " + this.getColor() + // get color de (à la base) de la classe mère ColoredShape
                '}';
    }
}
