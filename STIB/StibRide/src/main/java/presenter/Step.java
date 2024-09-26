package presenter;
import model.dto.StationDto;

import java.util.List;

public class Step {
    private StationDto station;
    private List<Integer> lines;

    public Step(StationDto station, List<Integer> lines) {
        this.station = station;
        this.lines = lines;
    }

    public StationDto getStation() {
        return station;
    }

    public List<Integer> getLines() {
        return lines;
    }
}
