package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presenter.Presenter;

public class DutchStationHandler implements EventHandler<ActionEvent> {
    private Presenter presenter;

    public DutchStationHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        presenter.handleDutchStationAction();
    }
}
