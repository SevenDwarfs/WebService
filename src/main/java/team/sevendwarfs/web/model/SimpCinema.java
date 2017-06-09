package team.sevendwarfs.web.model;

import team.sevendwarfs.persistence.entities.Cinema;

/**
 * Created by deng on 2017/6/6.
 */
public class SimpCinema {
    private Integer id;
    private String name;

    public SimpCinema() {}

    public SimpCinema(Cinema cinema) {
        this.id = cinema.getId();
        this.name = cinema.getName();
    }

    public SimpCinema(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpCinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
