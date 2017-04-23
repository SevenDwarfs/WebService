package team.sevendwarfs.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.sevendwarfs.DatasourceConfiguration;

/**
 * 基础测试类，所有测试类都继承这个类
 * Created by deng on 2017/4/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DatasourceConfiguration.class)
public class BaseTest {
}
