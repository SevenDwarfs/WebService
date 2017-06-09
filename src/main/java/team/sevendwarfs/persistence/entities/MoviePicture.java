package team.sevendwarfs.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by deng on 2017/6/8.
 */

@Entity
@Table(name = "movie_picture")
public class MoviePicture implements Serializable {
    @Id
    @Column(name = "url")
    private String url;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="mid")
    @JsonIgnore
    private Movie movie;

    public MoviePicture() {
    }

    public MoviePicture(String url, Movie movie) {
        this.url = url;
        this.movie = movie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MoviePicture{" +
                "url='" + url + '\'' +
                ", movie=" + movie +
                '}';
    }
}
