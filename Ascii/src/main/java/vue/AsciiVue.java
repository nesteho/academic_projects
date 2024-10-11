package vue;
public class AsciiVue {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    /**
     * Displays the given drawing
     * @param drawing the given drawing
     */
    public static void showTheDrawing(String drawing ){
        System.out.println(drawing);
    }
    /**
     * Displays the welcoming message
     */
    public static void displayWelcome() {
        System.out.println("***** Welcome to Ascii Paint! *****");
    }

    /**
     * Displays the given list of shapes
     * @param shapeList the given list of shapes
     */
    public static void displayShapeList(String shapeList){

        if (shapeList == null)  System.out.println("The shape list is empty! ");
        else System.out.println(shapeList);
    }
    /**
     * Display the given message
     */
    public static void  displayMessage(String message){
        System.out.println(message);
    }
    /**
     * Displays the drawing's dimensions
     */
    public static void  displayDimensions(int height, int width){
        System.out.println(" Drawing dimensions are  :"+ width +" x "+height);
    }

    /**
     * Displays the commands
     */
    public static void displayHelp(){

        System.out.println(
                "commands:\n" +
                "- add a circle : add circle <centerX> <centerY> <radius> <color>\n" +
                "- add a line : add line <x1> <y1> <x2> <y2> <color>\n" +
                "- add a rectangle : add rectangle <upperLeftX> <upperLeftY> <width> <height> <color>\n" +
                "- add a square : add square <upperLeftX> <upperLeftY> <side> <color>\n" +
                "- delete a shape : delete <shapeIndex>\n" +
                "- show the draw : show\n" +
                "- show the list of shapes represented in the draw :list\n" +
                "- group  some shapes : group <shapeIndex> (<shapeIndex> ...)\n" +
                "- ungroup a shape : ungroup <shapeIndex>\n" +
                "- move a shape : move <shapeIndex> <deltaX> <deltaY>\n" +
                "- set the color of a shape : color <shapeIndex> <newColor>\n"+
                " -undo a command: undo\n"+
                " -redo a command: redo\n"+
                " -exit the application : exit\n"+
                "newColor : is a character\n" +
                "radius : is an int"
                );
    }

    /**
     * Displays the given error message
     * @param message the given error message
     */
    public static void displayError(String message){
        System.out.println( RED+message+RESET );
    }
}
