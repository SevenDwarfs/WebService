package team.sevendwarfs.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.SpringConfiguration;
import team.sevendwarfs.Application;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by deng on 2017/4/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringConfiguration.class,
                            Application.class})
public class PersistenceEntitiesTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Transaction tx;

    /**
     * 测试 C3P0 数据源是否成功连接数据库
     * @throws SQLException
     */
    @Test
    public void dataSourceTest() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Before
    public void beforeTest() {
        this.session = sessionFactory.openSession();
        this.tx = session.beginTransaction();
    }

    @After
    public void afterTest() {
        this.tx.commit();
        this.session.close();
    }

    /**
     * 测试 Hibernate 关系映射是否正常, 操作
     * 1. 修改 application.prperties hibernate.hbm2ddl.auto=create
     * 2. 运行 hibernateSaveTest 单元测试
     * 3. 修改 application.prperties hibernate.hbm2ddl.auto=update
     * 4. 运行 hibernateMappingRelationTest 单元测试
     */
    @Test
    public void hibernateMappingRelationTest() {
        Movie caribbean = session.get(Movie.class, 3);
        System.out.print(caribbean);
    }

    /**
     * 测试 Hibernate 是否配置成功，并向数据库中添加几个数据
     */
    @Test
    public void hibernateSaveTest() {

        Movie movie1 = new Movie();

        Person person1 = new Person();
        Person person2 = new Person();

        person1.setName("约翰尼·德普");
        person1.setType(Person.ACTOR);
        person1.setUrl("https://imgsa.baidu.com/baike/s%3D220/" +
                "sign=9abb265d0ad79123e4e093769d355917/" +
                "3b292df5e0fe9925ec0a1f0532a85edf8db17164.jpg");

        person2.setName("戈尔·维宾斯基");
        person2.setType(Person.DIRECTOR);

        movie1.setChineseName("加勒比海盗");
        movie1.setEnglishName("Pirates of the Caribbean");
        movie1.setIntroduction("很好看很好看的电影");
        movie1.setReleaseDate(new Date());

        List<Person> moviers = new ArrayList<Person>();
        moviers.add(person1);
        moviers.add(person2);
        movie1.setMoviers(moviers);

        session.save(person1);
        session.save(person2);
        session.save(movie1);
    }
}
