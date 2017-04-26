package team.sevendwarfs.test;

import org.hibernate.annotations.Filter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.SpringConfiguration;
import team.sevendwarfs.SpringMvcQuickstartApplication;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.persistence.service.PersonService;
import team.sevendwarfs.web.filter.MyOpenSessionFilter;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by deng on 2017/4/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringConfiguration.class,
                            SpringMvcQuickstartApplication.class})
public class PersistenceServiceTest {
    @Autowired
    PersonService personService;

    @Autowired
    MovieService movieService;


    MyOpenSessionFilter myOpenSessionFilter;

    @Test
    public void personServiceTest() {
        System.out.println(personService);
        List<Person> list = personService.findAll();
        System.out.println(list.size());
        for (Person person : list) {
            System.out.println(person);
        }
    }

    @Test
    public void movieServiceTest() {
        System.out.println(movieService);
        List<Movie> list = movieService.findAll();
        System.out.println(list.size());
        for (Movie movie : list) {
            System.out.println(movie);
        }
    }

    @Test
    public void openSessionFilterTest() {
        System.out.println(myOpenSessionFilter);
    }
}
