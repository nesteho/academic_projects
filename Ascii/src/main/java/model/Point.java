package model;
/**
 * Represents a point
 */
public class Point {
    //prof ok pr double-> int
    private int x;
    private int y;

    /**
     * Constructor of a Point
     * @param x the horizontal address of the point
     * @param y the vertical address the of point
     */
   public Point(int x, int y){
       // le point (0.0)  est le point le plus en haut à gauche
       if (x < 0) {
           throw new IllegalArgumentException("The abscissa of the point must be positive!");
       }
       if (y < 0) {
           throw new IllegalArgumentException("The ordinate of the point must be positive!");
       }
       this.x = x;
       this.y= y;
    }

    /**
     * Constructs a Point from another
     * @param p the other point needed
     */
    public Point(Point p){
       if (p == null){
           throw new IllegalArgumentException("No given point to duplicate ");
       }
        this.x = p.x;
        this.y= p.y;
    }
    /*
     *Getter of the ordinate of the point
     */
    public int getX() {
        return x;
    }

    /*
     *Getter of the abscissa of the point
     */
    public int getY() {
        return y;
    }

    /**
     * Moves a point
     * @param dx number and sens of horizontal moving
     * @param dy number and sens of vertical moving
     */
    public void move(int dx, int dy) {
       if (  this.x + dx < 0 || this.y + dy < 0){
           throw new IllegalArgumentException("Movement not allowed: The shape is not entirely within the illustration!");
       }
       this.x += dx;
       this.y += dy;
    }

    /**
     * Calculates the distance between the current instance and another given point
     * @param other the  given point
     * @return the distance between these two points
     */
    public double distanceTo(Point other) {
       int deltaX = other.x - this.x ;
       int deltaY = other.y - this.y;
       return Math.sqrt( Math.pow(deltaX,2) + Math.pow(deltaY,2) );
    }

    /**
     * Returns a string representation of the point
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "(" + x +","+ y +")";
    }
}
