package team.sevendwarfs.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by deng on 2017/4/23.
 */

@Entity
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_person",
            joinColumns = @JoinColumn(name = "pid"),
            inverseJoinColumns = @JoinColumn(name = "mid")
    )
    private Set<Movie> moives;

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

    public Set<Movie> getMoives() {
        return moives;
    }

    public void setMoives(Set<Movie> moives) {
        this.moives = moives;
    }
}
