package team.sevendwarfs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.SpringConfiguration;
import team.sevendwarfs.persistence.service.PersonService;

/**
 * Created by deng on 2017/4/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringConfiguration.class)
public class PersistenceServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    public void personServiceTest() {
        System.out.println(personService.findOne(1));
    }
}
