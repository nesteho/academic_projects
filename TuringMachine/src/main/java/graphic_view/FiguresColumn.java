package graphic_view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a column of buttons
 */
public class FiguresColumn extends VBox {
    private int first;
    private int last;
    private Button selectedButton;
    private List<Button> buttons;

    /**
     * Constructs a column of buttons
     * @param v space between each button
     * @param first the minimum value of the button
     * @param last the maximum value of the button
     */
    public FiguresColumn(int v ,int first, int last) {
        super(v);
        this.first = first;
        this.last = last;
        buttons = new ArrayList<>();
        selectedButton = null;
        makeAFiguresColumn();
    }
    private void makeAFiguresColumn(){
        for (int i = first; i <= last; i++) {
            Button button = new Button(Integer.toString(i));
            this.getChildren().add(button);
            setSelectedButton(button);
            buttons.add(button);
        }
    }

    /**
     * Gets the list of the buttons
     * @return
     */
    public List<Button> getButtons(){
        return buttons;
    }
     void setSelectedButton(Button button){
        button.setOnAction(event ->{
            unSelectButtons();
            selectedButton = button;
            button.setStyle("-fx-background-color: lightgreen;");
        });
    }
     private void unSelectButtons(){
         for (Button button : this.getButtons()) {
             button.setStyle("");
         }
    }

    /**
     * Gets the selected button of the column
     * @return the selected button of the column
     */
    public Button getSelectedButton() {
        return selectedButton;
    }
    /**
     * Sets the selected button of the column
     */
    public void setSelectedButton() {
        if(selectedButton != null) {
            selectedButton.setStyle("");
            selectedButton = null ;
        }
    }
}
