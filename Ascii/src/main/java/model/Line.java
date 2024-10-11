package model;
import model.compositedp.ColoredShape;

/**
 * Represents a line
 */
public class Line extends ColoredShape {
    private final Point A;
    private final Point B;
    private final int m;
    /**
     * Constructor of a Line
     * @param a the first given point
     * @param b the second given point
     * @param color the color of line
     */
    public Line(Point a, Point b,char color) {
        super(color);
        this.A = a;
        this.B = b;
        m = (b.getY() - a.getY())/(b.getX()-a.getX());
    }
    /**
     * Moves the line
     * @param dx number and sens of horizontal moving
     * @param dy number and sens of vertical moving
     */
    @Override
    public void move(int dx, int dy) {
        this.A.move(dx, dy);
        this.B.move(dx, dy);
    }
    /**
     * Calculates whether a given point is in the line
     * @param p the given point
     * @return whether the point is in the line
     */
    public boolean isInside(Point p) {

        double distance = Math.abs( ( (m*p.getX()) - p.getY() - (m*this.A.getX()) + this.A.getY()  )
                                        / Math.sqrt(m*m +1) );
        return distance <= 0.5 ;
    }
    /**
     * Calculates the bounds of the line
     * @return the bounds of the line
     */
    @Override
    public Pair Bounds() {
        return new Pair(A,B);
    }
    /**
     * Returns a string representation of the line
     * @return a string representation of the line
     */
    @Override
    public String toString() {
        return "Line{" +
                " Point A = " + this.A +
                ", Point B = " + this.B +
                ", Coefficient directeur = " +m +
                ", color = " + this.getColor() +  // get color de (à la base) de la classe mère ColoredShape
                '}';
    }
}
