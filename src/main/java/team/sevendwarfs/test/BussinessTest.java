package team.sevendwarfs.test;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.DatasourceConfiguration;
import team.sevendwarfs.business.entities.Movie;
import team.sevendwarfs.business.entities.Person;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by deng on 2017/4/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DatasourceConfiguration.class)
public class BussinessTest {
    @Autowired
    DataSource dataSource;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String string;

    @Value("${c3p0.minPoolSize}")
    Integer minPoolSize;

    @Autowired
    SessionFactory sessionFactory;
    /**
     * 测试 C3P0 数据源是否成功连接数据库
     * @throws SQLException
     */
    @Test
    public void dataSourceTest() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void hibernate() {
        System.out.println(sessionFactory);
        Session session = sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

        Movie movie1 = new Movie();
        Movie movie2 = new Movie();

        Person person1 = new Person();
        Person person2 = new Person();


        movie1.setChineseName("速度与激情8");
        movie1.setEnglishName("speed and ?? 8");

        movie1.setChineseName("功夫熊猫");
        movie1.setEnglishName("Kongfu Panda");

        person1.setName("李青");
        person2.setName("崔斯塔娜");

        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie1);
        movies.add(movie2);
        person1.setMoives(movies);
        person2.setMoives(movies);

        session.save(movie1);
        session.save(movie2);
        session.save(person1);
        session.save(person2);

        tx.commit();
        session.close();
    }
}
