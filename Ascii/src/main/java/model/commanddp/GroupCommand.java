package model.commanddp;

import model.Drawing;
/**
 * Represents a command to group some shapes in a drawing.
 */
public class GroupCommand implements Command {
    private Drawing drawing;
    private int[] indexes;
    private int groupIndex;
    /**
     * Constructs a GroupCommand.
     * @param drawing the drawing where the grouping will be performed
     * @param indexes   the indexes of the shapes to group
     */
    public GroupCommand(Drawing drawing, int[] indexes) {
        this.drawing = drawing;
        this.indexes = indexes;
    }

    /**
     * Executes the grouping command
     */
    @Override
    public void execute() {
         this.groupIndex = drawing.groupShapes(indexes);
    }

    /**
     * Reverts the grouping command
     */
    @Override
    public void unexecute() {
        drawing.unGroupShapes(groupIndex);
    }
}
