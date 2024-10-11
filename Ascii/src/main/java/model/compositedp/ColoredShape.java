package model.compositedp;

public abstract class ColoredShape implements Shape {
    private  char color;
    public  ColoredShape(char color){

        this.color = color;   /* les constructeur 'enfants' vont faire appel à ce constructeur uniq pr initialiser la couleur
                                                                                         (elem commun a ts)*/
    }

    /**
     * Getter of the color of the shape
     * @return the color of the shape
     */
    public char getColor() {
        return color;
    }

    /**
     * Sets the color of the shape
     * @param color the new color of the shape
     */
    public void setColor(char color) {
        this.color = color;
    }
}
