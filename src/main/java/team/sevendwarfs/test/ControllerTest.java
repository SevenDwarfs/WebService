package team.sevendwarfs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.SpringConfiguration;

/**
 * Created by deng on 2017/4/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringConfiguration.class)
public class ControllerTest {

    @Value("${spring.datasource.minPoolSize}")
    private String minPoolSize;


    @Test
    public void helloWorldControllerTest() {
        System.out.println(minPoolSize);
    }
}
