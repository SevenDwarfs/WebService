package team.sevendwarfs.business.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Created by deng on 2017/4/23.
 */

@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private  Long id;

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

    // 导演名单
    private List<Person> directors;

    // 演员名单
    private List<Person> actors;

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 设置多对多关联映射
    @ManyToMany
    @JoinTable(name="movice_person", joinColumns={@JoinColumn(name="mid") },
            inverseJoinColumns = {@JoinColumn(name="pid")})
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
