package model;


import model.compositedp.ColoredShape;
/**
 * Represents a Circle
 */
public class Circle  extends ColoredShape {
    private final Point center;
    private final int radius;

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Constructor of a Rectangle
     * @param center the center point of the circle
     * @param radius the radius of the circle
     * @param color the color of the circle
     */
    public Circle(Point center, int radius, char color){

        super(color);

        if (radius <= 0.0 ){
            throw new IllegalArgumentException("The radius of the circle must be strictly positive!");
        }
        this.center = center;
        this.radius =radius;
    }
    /**
     * Calculates the bounds of the circle
     * @return a pair composed of two points
     */
    public Pair Bounds(){
        int radius = this.radius;
        Point upLeft = new Point(this.center.getX()-radius, this.center.getY()-radius);
        Point downRight = new Point(this.center.getX()+radius, this.center.getY()+radius);
        return new Pair(upLeft,downRight);
    }

    /**
     * Moves the circle
     * @param dx number and sens of horizontal moving
     * @param dy number and sens of vertical moving
     */
    @Override
    public void move(int dx, int dy) {
        center.move(dx, dy);

    }
    /**
     * Calculates whether a given point is in the circle
     * @param p the given point
     * @return whether the point is in the circle
     */
    public boolean isInside(Point p) {

        double distance = this.center.distanceTo(p);
        return distance <= radius ;
    }
    /**
     * Returns a string representation of the circle.
     * @return a string representation of the circle
     */
    @Override
    public String toString() {
        return "Circle{" +
                " center = " + center +
                ", radius =" + radius +
                ", color = " + this.getColor() +  // get color de (à la base) de la classe mère ColoredShape
                '}';
    }
}
