package model;
/**
 * Represents a square
 */
public class Square extends Rectangle{

    /**
     * Constructor of a square
     * @param ul the upper left point of the square
     * @param side the length of the side of the square
     * @param color the color of the square
     */
    public Square(Point ul, int side, char color){

        // constr va chercher  meth de rect si  meth souhaitee pas dans Square  (va remonter-héritage)
        super(ul,side,side,color);
    }
    /**
     * Returns a string representation of the square
     * @return a string representation of the square
     */
    public String toString(){

        return "Square{ " +
                " upperLeft = " + this.getUpperLeft() +   // ce this a accès au getter des attr < Rectangle (= aussi les siens)
                ", side = " + this.getHeight() +
                ", color=" + this.getColor() + // get color de (à la base)  la classe "grand-mère" ColoredShape
                '}';
    }
}
