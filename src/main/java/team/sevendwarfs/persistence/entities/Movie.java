package team.sevendwarfs.persistence.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;

    // 评分
    @Column(name = "rating")
    private String rating;

    // 所属国家
    @Column(name = "country")
    private String country;

    // 上映地点
    @Column(name = "show_place")
    private String showPlace;

    // 电影简介
    @Column(name = "introduction", length = 1000)
    private String introduction;

    /**
     * 电影人
     * 执行单向多对多关联映射关系
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
    @JsonIgnore
    private List<Person> moviers = new LinkedList<Person>();

    // 海报图集
    @OneToMany(mappedBy = "movie")
    private List<MoviePicture> pictures = new LinkedList<>();

    // 演员名单
    @Transient
    private List<Person> actors;

    @Transient
    private List<Person> directors;

    public Movie() {}

    public Movie(String chineseName, String englishName, String type, String length, String url, Date releaseDate, String introduction) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.type = type;
        this.length = length;
        this.url = url;
        this.releaseDate = releaseDate;
        this.introduction = introduction;
    }

    public Movie(String chineseName, String englishName, String type, String length, String url, Date releaseDate, String introduction, List<Person> moviers) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.type = type;
        this.length = length;
        this.url = url;
        this.releaseDate = releaseDate;
        this.introduction = introduction;
        this.moviers = moviers;
    }

    public Movie(String chineseName, String englishName, String type, String length, String url, Date releaseDate, String rating, String country, String showPlace, String introduction, List<Person> moviers, List<MoviePicture> pictures, List<Person> actors, List<Person> directors) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.type = type;
        this.length = length;
        this.url = url;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.country = country;
        this.showPlace = showPlace;
        this.introduction = introduction;
        this.moviers = moviers;
        this.pictures = pictures;
        this.actors = actors;
        this.directors = directors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShowPlace() {
        return showPlace;
    }

    public void setShowPlace(String showPlace) {
        this.showPlace = showPlace;
    }

    public List<MoviePicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<MoviePicture> pictures) {
        this.pictures = pictures;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

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
        return this.directors;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        return getId().equals(movie.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
