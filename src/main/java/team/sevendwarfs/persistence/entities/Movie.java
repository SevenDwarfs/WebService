package team.sevendwarfs.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 映射数据表 movie
 * Created by deng on 2017/4/23.
 */

@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    // 中文名字
    @Column(name = "chinese_name")
    private  String chineseName;

    // 英文名字
    @Column(name = "english_name")
    private String englishName;

    // 电影类型
    @Column(name = "type")
    private String type;

    // 电影长度
    @Column(name = "length")
    private String length;

    // 电影海报URL
    @Column(name = "url")
    private String url;

    // 上映时间
    @Column(name = "release_date")
    private Date releaseDate;

    // 电影简介
    @Column(name = "introduction")
    private String introduction;

    /**
     * 电影人
     * 执行双向多对多关联映射关系
     * @JoinTable: name: 中间表名
     *             joinColumns: 我方在中间表的外键
     *             inverseJoinColumns: 对方在中间表的外键
     */
    @Column(name = "moiver")
    @ManyToMany
    @JoinTable(
            name = "movie_person",
            joinColumns = @JoinColumn(name = "mid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    private List<Person> moviers;

    // 演员名单
    @Transient
    private List<Person> actors;

    @Transient
    private List<Person> directors;

    public List<Person> getActors() {
        if (this.actors == null) {
            this.actors = new LinkedList<Person>();
            for (Person person : this.moviers) {
                if (Person.ACTOR.equals(person.getType()))
                { this.actors.add(person); }
            }
        }
        return this.actors;
    }

    public List<Person> getDirectors() {
        if (this.directors == null) {
            this.directors = new LinkedList<Person>();
            for (Person person : this.moviers) {
                if (Person.DIRECTOR.equals(person.getType()))
                { this.directors.add(person); }
            }
        }
        return this.actors;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Person> getMoviers() {
        return moviers;
    }

    public void setMoviers(List<Person> moviers) {
        this.moviers = moviers;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", type='" + type + '\'' +
                ", length='" + length + '\'' +
                ", url='" + url + '\'' +
                ", releaseDate=" + releaseDate +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
