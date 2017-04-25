package team.sevendwarfs.persistence.dao;

import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.Movie;

/**
 * Created by deng on 2017/4/25.
 */

@Repository
public class MovieDaoImpl extends AbstractJpaDAO<Movie> implements MovieDao {

    /**
     * Instantiates a new Person dao.
     */
    public MovieDaoImpl() {
        super();

        setClazz(Movie.class);
    }

    // API 由 AbstractJpaDAO 实现
}
