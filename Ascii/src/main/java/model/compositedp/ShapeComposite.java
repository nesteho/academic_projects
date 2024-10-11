package model.compositedp;

import model.Pair;
import model.Point;
import java.util.List;

public class ShapeComposite extends ColoredShape {
    private final List<Shape> children;

    /**
     * Constructor of a composite component
     * @param color    the composite component's color
     * @param children the composite component's children
     */
    public ShapeComposite(char color, List<Shape> children) {
        super(color);
        this.children = children;
    }

    /**
     * Removes a child component
     */
    public void remove(Shape shape) {
        if (shape == null) throw new NullPointerException("No component provided");
        children.remove(shape);
    }

    /**
     * Adds a child component
     */
    public void add(Shape shape) {
        if (shape == null) throw new NullPointerException("No component provided");
        children.add(shape);
    }

    /**
     * Gets the children
     * @return the list of children
     */
    public List<Shape> getChildren() {
        return children;
    }

    @Override
    public void move(int dx, int dy) {
        for (Shape child : children) {
            child.move(dx, dy);
        }
    }
    /**
     * Calculates whether a given point is in the shapeComposite
     * @param p the given point
     * @return whether the point is in the shapeComposite
     */
    @Override
    public boolean isInside(Point p) {
        boolean isIn;
        for (Shape child : children) {
            isIn = child.isInside(p);
            if (isIn) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Pair Bounds() {
        return null;
    }
    /**
     * Returns a string representation of the shapeComposite
     * @return a string representation of the shapeComposite
     */
    @Override
    public String toString() {
        String compositeString = "ShapeComposite{ " + "\n";
        compositeString += " color=" + this.getColor() + "\n";

        for (Shape child : children) {
            compositeString += " , " + child.toString() + "\n";
        }
        compositeString += '}' + "\n";
        return compositeString;
    }
}
