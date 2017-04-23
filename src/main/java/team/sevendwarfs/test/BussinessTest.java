package team.sevendwarfs.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by deng on 2017/4/23.
 */

@ActiveProfiles("c3p0")
public class BussinessTest extends BaseTest {
    @Autowired
    DataSource dataSource;

    /**
     * 测试 C3P0 数据源是否成功连接数据库
     * @throws SQLException
     */
    @Test
    public void dataSourceTest() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
