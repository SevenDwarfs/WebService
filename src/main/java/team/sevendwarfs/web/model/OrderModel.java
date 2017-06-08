package team.sevendwarfs.web.model;

import team.sevendwarfs.persistence.entities.FilmOrder;

import java.util.List;

/**
 * Created by deng on 2017/6/8.
 */
public class OrderModel {
    List<FilmOrder> filmOrderModelList;

    public OrderModel(List<FilmOrder> filmOrderModelList) {
        this.filmOrderModelList = filmOrderModelList;
    }

    public OrderModel() {
    }

    public List<FilmOrder> getFilmOrderModelList() {
        return filmOrderModelList;
    }

    public void setFilmOrderModelList(List<FilmOrder> filmOrderModelList) {
        this.filmOrderModelList = filmOrderModelList;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "filmOrderModelList=" + filmOrderModelList +
                '}';
    }
}
