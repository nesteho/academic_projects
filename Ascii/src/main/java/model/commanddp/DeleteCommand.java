package model.commanddp;

import model.Drawing;
import model.compositedp.Shape;
/**
 * Represents a command to delete a shape in a drawing
 */
public class DeleteCommand implements Command{
    private final Drawing drawing;
    private final int index;
    private Shape lastDeletedShape;

    /**
     *Constructs an DeleteCommand.
     * @param drawing the drawing where the deletion will be performed
     * @param index   the index of the shape to delete
     */
    public DeleteCommand(Drawing drawing, int index) {
        this.drawing = drawing;
        this.index = index;
    }
    /**
     * Executes the deletion command
     */
    @Override
    public void execute() {
        lastDeletedShape = drawing.getShape(index);
        drawing.deleteShape(index);
    }
    /**
     * Reverses the deletion command
     */
    @Override
    public void unexecute() {
        if (lastDeletedShape != null){
            drawing.addShape(lastDeletedShape);
        }
    }
}
