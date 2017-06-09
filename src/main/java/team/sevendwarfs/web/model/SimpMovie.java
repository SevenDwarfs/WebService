package team.sevendwarfs.web.model;

import team.sevendwarfs.persistence.entities.Movie;

/**
 * 电影的简单信息，只包含电影名字，电影id，电影海报URL
 * Created by deng on 2017/6/4.
 */
public class SimpMovie {
    private String name;
    private Integer id;
    private String url;
    private String rating;

    public SimpMovie() {
        this.name = "该电影不存在";
        this.id = -1;
    }

    public SimpMovie(Movie movie) {
        this.name = movie.getChineseName();
        this.id = movie.getId();
        this.url = movie.getUrl();
        this.rating = movie.getRating();
    }

    public SimpMovie(String name, Integer id, String url, String rating) {
        this.name = name;
        this.id = id;
        this.url = url;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "SimpMovie{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
