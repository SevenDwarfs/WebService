package team.sevendwarfs.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by deng on 2017/4/23.
 */

@Entity
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    // 名字
    @Column(name = "name")
    private String name;

    // 照片的URL
    @Column(name = "url")
    private String url;

    // 表示是导演还是演员
    @Column(name = "type")
    private String type;

    // 与之相关的电影
    private List<Movie> movieList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    // 设置多对多关联映射，由Movice维护多对多映射关系
    @ManyToMany(mappedBy = "person")
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
