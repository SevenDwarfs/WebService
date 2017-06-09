package team.sevendwarfs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.SpringConfiguration;
import team.sevendwarfs.Application;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.service.MovieServiceImpl;
//import team.sevendwarfs.web.filter.MyOpenSessionFilter;

import java.util.List;

/**
 * Created by deng on 2017/4/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringConfiguration.class,
                            Application.class})
public class PersistenceServiceTest {

    @Autowired
    MovieServiceImpl movieServiceImpl;


//    MyOpenSessionFilter myOpenSessionFilter;

//    @Test
//    public void personServiceTest() {
//        System.out.println(personService);
//        List<Person> list = personService.findAll();
//        System.out.println(list.size());
//        for (Person person : list) {
//            System.out.println(person);
//        }
//    }

    @Test
    public void movieServiceTest() {
        System.out.println(movieServiceImpl);
        List<Movie> list = movieServiceImpl.findAll();
        System.out.println(list.size());
        for (Movie movie : list) {
            System.out.println(movie);
        }
    }

//    @Test
//    public void openSessionFilterTest() {
//        System.out.println(myOpenSessionFilter);
//    }
}
