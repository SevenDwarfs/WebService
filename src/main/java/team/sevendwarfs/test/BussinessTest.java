package team.sevendwarfs.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.SpringConfiguration;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by deng on 2017/4/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringConfiguration.class)
public class BussinessTest {
    @Autowired
    DataSource dataSource;

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

    /**
     * 测试 Hibernate 是否配置成功，并向数据库中添加几个数据
     */
    @Test
    public void hibernateTest() {
        System.out.println(sessionFactory);
        Session session = sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

        Movie movie1 = new Movie();
        Movie movie2 = new Movie();

        Person person1 = new Person();
        Person person2 = new Person();


        movie1.setChineseName("速度与激情8");
        movie1.setEnglishName("speed and ?? 8");

        movie2.setChineseName("功夫熊猫");
        movie2.setEnglishName("Kongfu Panda");

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
