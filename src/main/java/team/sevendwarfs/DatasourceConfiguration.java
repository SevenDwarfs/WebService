package team.sevendwarfs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 数据源配置类，配置 c3p0 数据源
 * 配置文件名为 application-c3p0.properties
 * Created by deng on 2017/4/23.
 */

@Configurable
@ActiveProfiles("c3p0")
public class DatasourceConfiguration {
    @Value("${jdbcUrl}")
    private String url;
    @Value("${user}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${driverClass}")
    private String driverClassName;
    @Value("${minPoolSize}")
    private Integer minPoolSize;
    @Value("${maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${maxIdleTime}")
    private Integer maxIdleTime;
    @Value("${acquireIncrement}")
    private Integer acquireIncrement;
    @Value("${maxStatements}")
    private Integer maxStatements;
    @Value("${initialPoolSize}")
    private Integer initialPoolSize;
    @Value("${idleConnectionTestPeriod}")
    private Integer idleConnectionTestPeriod;

    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @Primary
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClassName);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMaxIdleTime(maxIdleTime);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setMaxStatements(maxStatements);
        dataSource.setInitialPoolSize(initialPoolSize);
        dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        return dataSource;
    }
}
