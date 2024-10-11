package model;

import model.commanddp.*;


public class AsciiPaint {
    private final Invoker invoker = new Invoker() ;
    private final Drawing drawing;
    //  utilité drawing   -dire quoi créer ) -et où mettre la forme (= ds drawing)
   //  Ascii 1 est le créateur de commande : une fct = un créateur d'1  commande concrete (qui appelle constructeur de la commande concrete)

    /**
     * Constructor of a facade of the drawing
     * @param height the facade height
     * @param width the facade width
     */
    public AsciiPaint(int height, int width) {

        this.drawing = new Drawing(height, width);
    }
    /**
     * Constructor of a facade of the drawing
     */
    public AsciiPaint() {
        this.drawing = new Drawing();
    }

    /**
     * Creates a new Command  circle
     * @param x the abscissa of the circle's center point
     * @param y the ordinate of the circle's center point
     * @param radius the circle radius
     * @param color  the circle color
     */
    public void newCircle (int x, int y, int radius, char color){
        var circle = new Circle( new Point(x, y), radius, color);
        var command = new AddCommand(circle, drawing);
        invoker.execute(command);
    }

    /**
     * Creates a new CreateRectangleCommand Command
     * @param x the abscissa of the rectangle's upper left point
     * @param y the ordinate of the rectangle's upper left point
     * @param width the rectangle width
     * @param height the rectangle height
     * @param color the rectangle color
     */
    public void newRectangle (int x, int y, int width, int height, char color){
        var rectangle = new Rectangle( new Point(x, y), width, height,color);
        var command = new AddCommand(rectangle, drawing);
        invoker.execute(command);
    }

    /**
     * Creates a new Command  circle
     * @param x the abscissa of the square's upper left point
     * @param y the ordinate of the square's upper left point
     * @param side the square side
     * @param color the square color
     */
    public void newSquare (int x, int y, int side, char color){
        var square = new Square( new Point(x, y),side,color);
        var command = new AddCommand(square, drawing);
        invoker.execute(command);
    }

    /**
     * Creates a new Command  line
     * @param x1 the abscissa of the first given point
     * @param y1 the ordinate of the first given point
     * @param x2 the abscissa of the second given point
     * @param y2 the ordinate of the second given point
     * @param color  the line color
     */
    public void newLine (int x1, int y1, int x2, int y2, char color){
        var line = new Line( new Point(x1,y1),new Point(x2,y2),color);
        var command = new AddCommand(line, drawing);
        invoker.execute(command);
    }

    /**
     * Moves a shape
     * @param i the index of the shape
     * @param dx number and sens of horizontal moving
     * @param dy number and sens of vertical moving
     */
    public void moveShape ( int i , int dx, int dy){
        var moveShape = new MoveCommand(drawing,i,dx,dy);
        invoker.execute(moveShape);
    }

    /**
     * Sets the  shape color
     * @param index the index of the shape
     * @param c the new shape color
     */
    public void setShapeColor (int index, char c){
        if(index<0 || index >= drawing.shapeListSize()){
            throw new IndexOutOfBoundsException("There is no shape at index" + index + " in the list of shapes");
        }
        var setcolorC = new SetColorCommand(drawing, index, c, drawing.getShapeColor(index));
        invoker.execute(setcolorC);
    }

    /**
     * Deletes a shape to the list of shapes
     * @param index  the index of shape to delete
     */
    public void deleteShape (int index){
        var deleteCommand = new DeleteCommand(drawing,index);
        invoker.execute(deleteCommand);
    }

    /**
     * Returns a string version of the list
     * @return  a string version of the list
     */
    public String  setListToString(){
      return drawing.ListToString();
    }

    /**
     * Returns a string version of the drawing
     * @return Returns a string version of the drawing
     */
    public  String asAscii(){
        return drawing.toString();
    }

    /**
     * Groups shapes at the specified indexes into a composite shape
     * @param indexes the indexes of shapes to be grouped
     */
    public void groupShapes(int... indexes) {
        var groupShapesC = new GroupCommand(drawing, indexes);
        invoker.execute(groupShapesC);
    }

    /**
     * Ungroups the composite shape at the specified index
     * @param index the index of the composite shape to ungroup
     */
    public void unGroupShapes(int index) {
        var unGroupShapeC = new UnGroupCommand(drawing, index);
        invoker.execute(unGroupShapeC);
    }

    /**
     * Undoes the last command
     */
    public void undo() {
        invoker.undo();
    }

    /**
     * Redoes the last undone command
     */
    public void redo() {
        invoker.redo();
    }
}
