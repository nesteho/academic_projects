package model.dto;

import javafx.scene.shape.Line;

public class StationDto extends Dto<Integer> {
    private String name;

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
    public StationDto(Integer key, String name) {
        super(key);
        this.name = name;
    }
}
