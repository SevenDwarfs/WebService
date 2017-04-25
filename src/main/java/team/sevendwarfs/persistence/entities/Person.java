package team.sevendwarfs.persistence.entities;

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
    static final public String ACTOR = "actor";
    static final public String DIRECTOR = "director";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    // 名字
    @Column(name = "name")
    private String name;

    // 照片的URL
    @Column(name = "url")
    private String url;

    // 表示是导演还是演员
    @Column(name = "type")
    private String type;

    /**
     * 配置双向多对多关联映射关系
     * 由 movier 维护映射关系
     * mappedBy: 对方对应成员变量名, targetEntity: 对方持久化类类名
     */
    @ManyToMany(mappedBy = "moviers", targetEntity = Movie.class)
    private List<Movie> moives;

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

    public List<Movie> getMoives() {
        return moives;
    }

    public void setMoives(List<Movie> moives) {
        this.moives = moives;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
