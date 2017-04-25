package team.sevendwarfs.persistence.dao;

import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.Person;

/**
 * Created by deng on 2017/4/25.
 */
@Repository
public class PersonDaoImpl extends AbstractJpaDAO<Person> implements PersonDao {

    /**
     * Instantiates a new Person dao.
     */
    public PersonDaoImpl() {
        super();

        setClazz(Person.class);
    }

    // API 由 AbstractJpaDAO 实现

}
