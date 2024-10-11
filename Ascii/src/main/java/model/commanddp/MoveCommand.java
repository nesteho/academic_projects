package model.commanddp;

import model.Drawing;
/**
 * Represents a command to move a  shape in a drawing
 */
public class MoveCommand implements Command{
    private final Drawing drawing;
    private final int shapeIndex;
    private final int dx;
    private final int dy;

    /**
     * Constructs an MoveCommand.
     * @param drawing the drawing where the moving will be performed
     * @param shapeIndex   the index of the  shape to move
     * @param dx number and sens of horizontal moving
     * @param dy number and sens of vertical moving
     */
    public MoveCommand(Drawing drawing, int shapeIndex, int dx, int dy) {
        this.drawing = drawing;
        this.shapeIndex = shapeIndex;
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Executes the moving command
     */
    @Override
    public void execute() {
        drawing.moveShape(shapeIndex,dx,dy);
    }

    /**
     * Reverses the moving command
     */
    @Override
    public void unexecute() {
        drawing.moveShape(shapeIndex, -dx,-dy);
    }
}
