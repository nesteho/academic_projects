package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presenter.Presenter;

public class DeleteFavoriteButtonHandler implements EventHandler<ActionEvent> {
    private Presenter presenter;
    public DeleteFavoriteButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void handle(ActionEvent t) {
        presenter.handleDeleteFavoriteButtonAction();
    }
}
