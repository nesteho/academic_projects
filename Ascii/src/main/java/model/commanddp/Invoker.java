package model.commanddp;

import java.util.Stack;
/**
 * Represents the invoker in the design pattern command
 */
public class Invoker {

    // client : application  | invoker : nvl classe invoker ou CommandManager | concrete command et reveiver  : ok
     private final Stack<Command> undos;
     private final Stack<Command> redos;

    /**
     *  Constructs an Invoker
     */
    public Invoker() {
        this.undos = new Stack<>();
        this.redos = new Stack<>();
    }

    /**
     * Undoes a command
     */
    public void undo(){
        if (!undos.empty()) {
            var command = undos.pop();
            command.unexecute();
            redos.add(command ) ; //  undos.pop() : dernière command ajoutée est première retiree
            //  cette commande undo peut être redo
        }
    }

    /**
     * Redoes a command
     */
    public void redo(){
        // on peut undo redo la meme (dernière ) commande executée
        if (!redos.empty()) {
            var command = redos.pop();
            command.execute();
            undos.add(command ) ;
        }
    }

    /**
     * Executes a command
     * @param command the command to execute
     */
    public void execute (Command command){
        if(command != null){
            command.execute();
            // on a execute cette commande -> c'est donc une commande qui peut être défaite/annulée
            // -> ajouter dans undo
            undos.add(command);
            redos.clear();  // aps avoir exectue une commande on peut peut undo mais pas redo : synchro
        }
    }
}
