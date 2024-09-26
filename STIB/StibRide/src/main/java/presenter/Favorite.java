package presenter;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import model.dto.StationDto;

public class Favorite {
      private Integer idCol;
      private String nameCol;
      private StationDto originCol;
      private StationDto destinationCol;

    public Integer getIdCol() {
        return idCol;
    }

    public String getNameCol() {
        return nameCol;
    }

    public StationDto getOriginCol() {
        return originCol;
    }

    public StationDto getDestinationCol() {
        return destinationCol;
    }

    public Favorite(Integer idCol, String nameCol, StationDto originCol, StationDto destinationCol) {
        this.idCol = idCol;
        this.nameCol = nameCol;
        this.originCol = originCol;
        this.destinationCol = destinationCol;
    }
}
