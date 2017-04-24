package team.sevendwarfs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置类, 配置文件为: application.properties
 * 配置了 c3p0数据源, Hibernate
 * Created by deng on 2017/4/23.
 */
@Configurable
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@PropertySource({"classpath:application.properties"})
public class SpringConfiguration {
    private static final String HibernateDialect = "hibernate.dialect";
    private static final String HibernateShowSql = "hibernate.show_sql";
    private static final String HibernateDDLAuto = "hibernate.hbm2ddl.auto";
    private static final String HibernatePackagesToScan = "hibernate.packagesToScan";
    private static final String HibernateFormatSql = "hibernate.format_sql";

    @Value("${spring.datasource.jdbcUrl}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.minPoolSize}")
    private Integer minPoolSize;
    @Value("${spring.datasource.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${spring.datasource.maxIdleTime}")
    private Integer maxIdleTime;
    @Value("${spring.datasource.acquireIncrement}")
    private Integer acquireIncrement;
    @Value("${spring.datasource.maxStatements}")
    private Integer maxStatements;
    @Value("${spring.datasource.initialPoolSize}")
    private Integer initialPoolSize;
    @Value("${spring.datasource.idleConnectionTestPeriod}")
    private Integer idleConnectionTestPeriod;

    @Autowired
    private Environment env;

    @Bean
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

    @Bean("localSessionFactoryBean")
    public LocalSessionFactoryBean localSessionFactoryBean() throws
            PropertyVetoException, IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(HibernatePackagesToScan));
        sessionFactoryBean.setHibernateProperties(hibProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean;
    }

    @Bean("sessionFactory")
    @Primary
    public SessionFactory sessionFactory() throws PropertyVetoException, IOException {
        SessionFactory sessionFactory = localSessionFactoryBean().getObject();
        return sessionFactory;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(HibernateDialect, env.getRequiredProperty(HibernateDialect));
        properties.put(HibernateShowSql, env.getRequiredProperty(HibernateShowSql));
        properties.put(HibernateDDLAuto, env.getRequiredProperty(HibernateDDLAuto));
        properties.put(HibernateFormatSql, env.getRequiredProperty(HibernateFormatSql));
        return properties;
    }
}
