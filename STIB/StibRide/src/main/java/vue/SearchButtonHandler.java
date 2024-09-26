package vue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presenter.Presenter;

public class SearchButtonHandler implements EventHandler<ActionEvent> {

    private Presenter presenter;
    public SearchButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void handle(ActionEvent t) {
        presenter.handleSearchButtonAction();
    }

}

