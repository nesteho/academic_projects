package model.commanddp;

import model.Drawing;
/**
 * Represents a command to ungroup a composite shape in a drawing
 */
public class UnGroupCommand implements Command{
    private final Drawing drawing;
    private final int index;
    private int[] indexes;

    /**
     * Constructs an UnGroupCommand.
     * @param drawing the drawing where the ungrouping will be performed
     * @param index   the index of the composite shape to ungroup
     */
    public UnGroupCommand(Drawing drawing, int index) {
        this.drawing = drawing;
        this.index = index;
    }
    /**
     * Executes the ungrouping command
     */
    @Override
    public void execute() {
       indexes =  drawing.unGroupShapes(index);
    }

    /**
     * Reverses the ungrouping command
     */
    @Override
    public void unexecute() {
        drawing.groupShapes(indexes);
    }
}
