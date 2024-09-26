package model.dto;

public class FavoriteDto extends Dto<Integer>{
    private String name;
    private Integer id_origin;
    private Integer id_destination;

    public FavoriteDto(String name, Integer id_origin, Integer id_destination) { //celui quand on veut insert(car auto generated key)
        super(1);
        this.name = name;
        this.id_origin = id_origin;
        this.id_destination = id_destination;
    }

    public FavoriteDto(Integer key,String name, Integer id_origin, Integer id_destination) { //celui pour select
        this(name, id_origin, id_destination);
        super.key=key;
    }

    public String getName() {
        return name;
    }

    public Integer getId_origin() {
        return id_origin;
    }

    public Integer getId_destination() {
        return id_destination;
    }
    @Override
    public String toString() {
        return name;
    }
}
