package graphic_view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a numeric keyboard
 */
public class NumericalKeyBoard extends VBox {
    // VUE : observateur de son enfant
    private int nColumns;
    private int first;
    private int last;
    private int previousCode;
    private int userCode;
    private List<FiguresColumn> columns;
    private PropertyChangeSupport propChS; // pour me rendre ecoutabLE
    public  static  final String PROPERTY_NAME = "new code entered";

    /**
     * Constructs a NumericalKeyBoard instance
     * @param v v space between each children
     * @param nColumns the number of colums
     * @param first the minimum value of the button
     * @param last the maximum value of the button
     */
    public NumericalKeyBoard(double v, int nColumns, int first, int last){
        super(v);
        propChS = new PropertyChangeSupport(this);
        this.nColumns = nColumns;
        this.first = first;
        this.last = last;
        this.userCode = 0;
        previousCode = 0;
        columns = new ArrayList<>();
        this.setStyle("-fx-background-color: white;");
        makeTheKeyBoard();
        this.setAlignment(Pos.CENTER);
    }
    private void makeTheKeyBoard() {
        Label title = new Label("Test A Code");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Label subTitle = new Label("Enter Your Code");
        subTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

        String shapePath = "/shapes.png";
        ImageView shapesImage = new ImageView(getClass().getResource(shapePath).toString());
        this.getChildren().addAll(title,subTitle,shapesImage);
        shapesImage.setFitHeight(20);
        shapesImage.setFitWidth(80);
        this.setPrefSize(130,150);

        var figures = new HBox(5);
        for (int i = 0; i < nColumns ; i++) {
            var column = new FiguresColumn( 5, first,last);
            figures.getChildren().add(column);
            columns.add(column);
        }
        figures.setAlignment(Pos.CENTER);

        Button testCodeButton = new Button("Test");
        testCodeButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        testCodeButton.setOnAction(e -> {
            previousCode = userCode;
            userCode = formUserCode();
            notifiyPropertyChange();

        });

        this.getChildren().addAll(figures,testCodeButton);
    }

    /**
     * Resets the keyboard
     */
    public void unSelectAll(){
        var container = (HBox) this.getChildren().get(3);
        for ( Node fc :  container.getChildren() ) {
            var column = (FiguresColumn) fc;
            column.setSelectedButton();
        }
    }
    private int formUserCode () {
        var userCode = new StringBuilder();
        var container = (HBox) this.getChildren().get(3); // offset 3 car av y a titre, sous-titre,shapes
            for ( Node fc : container.getChildren() ) {
                var column = (FiguresColumn) fc;
                var selectedButton = column.getSelectedButton();
                if(selectedButton != null){
                    userCode.append(selectedButton.getText());
                }// c le model qui va verif que 3 chiffres ou pas
            }
        if(userCode.length() == 0 ){
            userCode.append(1); // pas ma responsabilité de verifier longueur du code pas moi qui throw
        }
        return Integer.parseInt(userCode.toString());
    }
    /**
     * Adds a PropertyChangeListener to the list of listeners.
     * @param pcs the PropertyChangeListener
     */
    void addPropertyChangeListener (PropertyChangeListener pcs ){
        propChS.addPropertyChangeListener(pcs);
    }

    /**
     * Removes a PropertyChangeListener from the list of listeners.
     * @param pcs the PropertyChangeListener
     */
    void removePropertyChangeListener (PropertyChangeListener pcs ) {
        propChS.removePropertyChangeListener(pcs);
    }
    private void notifiyPropertyChange(){
        propChS.firePropertyChange(PROPERTY_NAME, previousCode , userCode);
    }


}
