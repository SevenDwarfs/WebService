package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;

import java.util.List;

/**
 * Created by deng on 2017/4/27.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据名字查询
    public List<Person> findByName(String name);

    // 根据类型查询
    public List<Person> findByType(String type);

    // 根据 人的类型和电影 查询
    public List<Person> findByTypeAndMoivesContains(String type, Movie movie);
}
