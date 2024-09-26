package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presenter.Presenter;

public class FavoriteButtonHandler implements EventHandler<ActionEvent> {
    private Presenter presenter;
    public FavoriteButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void handle(ActionEvent t) {
        presenter.handleFavoriteButtonAction();
    }
}
