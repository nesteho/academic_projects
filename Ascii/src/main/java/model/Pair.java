package model;

/**
 * Represents a pair of points composed of limits of the shape
 */
public class Pair {
    private final Point upperLeft;
    private final Point bottomRight;

    /**
     * Constructs a pair of points
     */
    public Pair(Point ul, Point br){
        this.upperLeft= ul;
        this.bottomRight = br;
    }

    /**
     * Getter of the upper left point of the pair
     * @return the upper left point of the pair
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Getter of the bottom right point of the pair
     * @return the bottom right point of the pair
     */
    public Point getBottomRight() {
        return bottomRight;
    }
}
