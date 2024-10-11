package model;

import model.compositedp.Shape;
import model.compositedp.ShapeComposite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Drawing {
    private final List<Shape> shapes;
    private final int  height;
    private final int width;
    public final static  int DEFAULT_HEIGHT = 50;
    public  final static  int DEFAULT_WIDTH = 50;

    /**
     * Constructor of a drawing
     */
    public Drawing(){
        this(DEFAULT_HEIGHT,DEFAULT_WIDTH);
    }

    /**
     * Constructor of a drawing
     * @param height the drawing's height
     * @param width the drawing's width
     */
    public Drawing(int height, int width){
        if (height <= 0){
            throw new IllegalArgumentException("Height must be strictly positive!");
        }
        if (width <= 0){
            throw new IllegalArgumentException("Width must be strictly positive!");
        }
        this.height = height;
        this.width = width;
        this.shapes =  new ArrayList<>();
    }

    /**
     * Adds a shape to the list of shapes
     * @param shape the shape to add
     */
    public void addShape ( Shape shape){
        checkIfNull(shape,"No given shape to add  ");
        if (shape.Bounds().getUpperLeft().getX() >= width || shape.Bounds().getBottomRight().getY()>= height){
            throw new IllegalArgumentException("Shape is not entirely in the drawing ! ");
        }
        shapes.add(shape);
    }

    /**
     * Find the shape to which the given point belongs
     * @param p the given point
     * @return the shape the point belongs to
     */
    public Shape getShapeAt (Point p){
        checkIfNull(p, "The point does not exist (yet)");
        int cptShapes = 0;

        while (cptShapes < shapes.size() && !shapes.get(cptShapes).isInside(p) ){
            cptShapes++;
        }
        if ( cptShapes< shapes.size() ){
            return shapes.get(cptShapes);
        }
        return null;
    }

    /**
     * Returns a string representation of the drawing
     * @return a string representation of the drawing
     */
    @Override
    public String toString(){
        StringBuilder draw = new StringBuilder();
        for (int i = 0; i < height ; i++) {
             for (int j = 0; j < width; j++) {
                 Point point = new Point(i,j);

                 if (getShapeAt(point) == null){
                     draw.append(" ");
                 }
                 else{
                     draw.append(getShapeAt(point).getColor());
                 }
                 // getColor appartient de base à ColoredShape car c'est elle qui a attr color, ms rect/circ = enfants -> y ont aççès
             }
             draw.append("\n");
         }
         return draw.toString();
    }

    /**
     * Returns a string representation of the list of shapes
     * @return a string representation of the list of shapes
     */
    public String ListToString(){
        StringBuilder listS = new StringBuilder();
        for (Shape shape: shapes ) {
            listS.append(shape.toString()+"\n");
        }
        return  listS.toString();
    }

    /**
     * Sets the  shape color
     * @param index the index of the shape
     * @param c the new shape color
     */
    public void setShapeC (int index,  char c){
        checkShapeIndex(index,"Color change not possible! There is no shape at index" +
                                                                index + " in the list of shapes");
        this.shapes.get(index).setColor(c);
    }

    /**
     * Moves the shape
     * @param index the index of the shape
     * @param dx the number and sens of the horizontal moving
     * @param dy the number and sens of the vertical moving
     */
    public void moveShape ( int index , int dx, int dy){
        checkShapeIndex(index,"Moving shape not possible! There is no shape at index" +
                                                            index + " in the list of shapes");
        shapes.get(index).move(dx,dy);
    }
    /**
     * Deletes a shape to the list of shapes
     * @param indexes   indexes of shapes to delete
     */
    public void deleteShape ( int... indexes ){
        checkIfNull(indexes,"There is no shape at given index");
        sortDesc(indexes);
        for (int index : indexes) {
            checkShapeIndex(index,"Removing shape not possible! There is no shape at index" +
                    index + " in the list of shapes");

            shapes.remove(index);
        }
    }
    /**
     * Deletes the given shape from the list of shapes.
     * @param shape the shape to be deleted
     */
    public void deleteShape(Shape shape) {
        checkIfNull(shape, "The shape to delete does not exist");
        shapes.remove(shape);
    }
    // pr le unexecute de create Command

    /**
     * Groups shapes at the specified indexes into a composite shape.
     * @param indexes the indexes of shapes to be grouped
     * @return the index of the newly created composite shape in the list of shapes
     */
    public int groupShapes (  int... indexes ){
        checkIfNull(indexes,"There is no given indexes to group");
        var children = new ArrayList<Shape>();
        checkShapeIndex(indexes[0], "Grouping shapes not possible! There is no shape at index" +
                                                                    indexes[0] + " in the list of shapes");

        ShapeComposite group = new ShapeComposite(shapes.get(indexes[0]).getColor(),children);

        for (int index: indexes) {
            checkShapeIndex(index,"Grouping shapes not possible! There is no shape at index" +
                                                                        index + " in the list of shapes");
          group.add(shapes.get(index));
          // ajoute un composite à la liste de shape Possible car Shapecomposite hérite de l'implémentation de Shape faite par ColoredShape
        }

        deleteShape(indexes);
        shapes.add(group);
        return shapes.size()-1;
    }

    /**
     * Ungroups the composite shape at the specified index, restoring its individual shapes.
     * @param index the index of the composite shape to ungroup
     * @return an array of indexes representing the original positions of the ungrouped shapes
     */
    public int[] unGroupShapes (int index){
        checkShapeIndex(index,"Ungouping shape not possible! There is no shape at index" +
                                                                     index + " in the list of shapes");
        var preGroupmentIndexes = new ArrayList<Integer>();
        ShapeComposite groupToDelete = (ShapeComposite) shapes.get(index);

        for (int i = 0; i < groupToDelete.getChildren().size(); i++) {
            preGroupmentIndexes.add(shapes.size() + i);
            shapes.add(groupToDelete.getChildren().get(i));
        }
        shapes.remove(groupToDelete);

        //return int[] avec les indices  et pas void car besoin ds unexecute de GroupCommand
        return preGroupmentIndexes.stream().mapToInt(Integer::intValue).toArray();
    }
    private void sortDesc(int[] indexes){
        Arrays.sort(indexes);
        for (int i = 0; i < indexes.length / 2; i++) {
            int temp = indexes[i];
            indexes[i] = indexes[indexes.length - 1 - i];
            indexes[indexes.length - 1 - i] = temp;
        }
    }

    /**
     * Gets the color of the shape at the given index in the list of shapes.
     * @param index the index of the shape in the list
     * @return the color of the shape at the given index
     */
    public char getShapeColor(int index) {
        checkShapeIndex(index, "There is no shape at index " + index + " in the list of shapes");
        return shapes.get(index).getColor();
    }

    /**
     * Gets the shape at the given index in the list of shapes.
     * @param index the index of the shape in the list
     * @return the shape at the given index
     */
    public Shape getShape(int index) {
        checkShapeIndex(index,"Color change not possible! There is no shape at index"
                                                            + index + " in the list of shapes");
        return shapes.get(index);
    }
    /**
     * Gets the size of the shape list .
     * @return the shape at the given index
     */
    public int shapeListSize() {
        return shapes.size();
    }

    private void checkIfNull(Object o, String errorMessage){
        if (o == null){
            throw new NullPointerException(errorMessage);
        }
    }
    private void checkShapeIndex ( int index, String errorMessage){
        if ( index < 0 || index >= shapes.size()  ){
            throw new IndexOutOfBoundsException(errorMessage);
        }
    }
}

   // un getShapes() dans cette classe :  mv / dangeureux car encapsulation non respectée
   // : donne accès aux forme et on peut potentiellement les modifiées
