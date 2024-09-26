package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presenter.Presenter;

public class ShowPathButtonHandler implements EventHandler<ActionEvent> {
    private Presenter presenter;
    public ShowPathButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void handle(ActionEvent t) {
        presenter.handleShowPathButtonAction();
    }
}
