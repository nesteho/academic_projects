package model.commanddp;
import model.Drawing;

/**
 * Represents a command to set the color of a  shape
 */
public class SetColorCommand  implements Command{
    private final Drawing drawing; // receiver
    private final int shapeIndex;
    private final char lastColor;  // state
    private final char newColor;
    // tt donner en attribut de la classe

    public SetColorCommand(Drawing drawing, int shapeIndex, char newColor, char lastColor) {
        this.drawing = drawing;
        this.newColor = newColor;
        this.shapeIndex = shapeIndex;
        this.lastColor = lastColor;
    }
    /**
     * Executes the set color command
     */
    @Override
    public void execute() {
        drawing.setShapeC(this.shapeIndex, this.newColor);
    }

    /**
     * Reverses the moving command
     */
    @Override
    public void unexecute() {
        drawing.setShapeC(this.shapeIndex, this.lastColor);
    }
}
