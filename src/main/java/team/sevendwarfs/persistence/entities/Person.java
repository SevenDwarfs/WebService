package team.sevendwarfs.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by deng on 2017/4/23.
 */

@Entity
@Table(name = "person")
public class Person implements Serializable {
    static final public String ACTOR = "演员";
    static final public String DIRECTOR = "导演";

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getId().equals(person.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
