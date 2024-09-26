package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presenter.Presenter;

public class ExitMenuItemHandler implements EventHandler<ActionEvent> {
    private Presenter presenter;
    public ExitMenuItemHandler(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void handle(ActionEvent t) {
        presenter.handleExitMenuItemAction();
    }
}
