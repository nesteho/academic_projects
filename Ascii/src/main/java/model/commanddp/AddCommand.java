package model.commanddp;

import model.Drawing;
import model.compositedp.Shape;
/**
 * Represents a command to add a shape in a drawing
 */
public class AddCommand implements Command{
    private final Shape shape;
    // en attr au lieu d'av : private x,y, radius,color 4 attr   MAIS QUAND MM RECUP LES PARAM 1 A 1 ET PAS UNE SHAPE DS CONSTRUCT
    // Au lieu d'avoir 4  ClassesConcreteCommand qui sont : CreateCircle/Square/REct/Line -> une classe addCommand qui recoit une shape et le drawing
    private final Drawing drawing;
    /**
     *Constructs an AddCommand.
     * @param shape   the shape to delete
     * @param drawing the drawing where the deletion will be performed
     */
    public AddCommand(Shape shape, Drawing drawing) {
       this.shape = shape;
       this.drawing = drawing;
    }

    /**
     * Executes the add command
     */
    @Override
    public void execute() {
        drawing.addShape(shape);
        // executer une commande qui crée un cercle != créer le cercle!  . Creer le cercle se fait direct dans constr de la commandeConcrete
    }
    /**
     * Reverses the add command
     */
    @Override
    public void unexecute() {
        drawing.deleteShape(shape);
    }
}
