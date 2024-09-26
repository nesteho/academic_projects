package model.dto;

import javafx.util.Pair;

public class StopDto extends Dto<Pair<Integer,Integer>> {
    private int id_order;

    public int getId_order() {
        return id_order;
    }

    public StopDto(Pair<Integer,Integer> key, int id_order) {
        super(key);
        this.id_order = id_order;
    }

}
