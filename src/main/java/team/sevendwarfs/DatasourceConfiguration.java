package team.sevendwarfs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * 数据源配置类，配置 c3p0 数据源
 * 配置文件名为 application-c3p0.properties
 * Created by deng on 2017/4/23.
 */
@Configurable
@PropertySource({"classpath:application-c3p0.properties", "classpath:application.properties"})
public class DatasourceConfiguration {
    private static final String Hibernate_Dialect = "hibernate.dialect";
    private static final String Hibernate_ShowSql = "hibernate.show_sql";
    private static final String Hibernate_DDLAuto = "spring.jap.hibernate.hbm2ddl.auto";
    private static final String Hibernate_PackagesToScan = "hibernate.packagesToScan";
    private static final String Hibernate_format_sql = "hibernate.format_sql";

    @Value("${c3p0.jdbcUrl}")
    private String url;
    @Value("${c3p0.username}")
    private String username;
    @Value("${c3p0.password}")
    private String password;
    @Value("${c3p0.driverClass}")
    private String driverClassName;
    @Value("${c3p0.minPoolSize}")
    private Integer minPoolSize;
    @Value("${c3p0.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${c3p0.maxIdleTime}")
    private Integer maxIdleTime;
    @Value("${c3p0.acquireIncrement}")
    private Integer acquireIncrement;
    @Value("${c3p0.maxStatements}")
    private Integer maxStatements;
    @Value("${c3p0.initialPoolSize}")
    private Integer initialPoolSize;
    @Value("${c3p0.idleConnectionTestPeriod}")
    private Integer idleConnectionTestPeriod;

    @Resource
    private Environment env;

    @Bean("dataSource")
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
        sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(Hibernate_PackagesToScan));
        sessionFactoryBean.setHibernateProperties(hibProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean;
    }

    @Bean("sessionFactory")
    public SessionFactory sessionFactory() throws PropertyVetoException, IOException {
        SessionFactory sessionFactory = localSessionFactoryBean().getObject();
        return sessionFactory;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(Hibernate_Dialect, env.getRequiredProperty(Hibernate_Dialect));
        properties.put(Hibernate_ShowSql, env.getRequiredProperty(Hibernate_ShowSql));
        properties.put(Hibernate_DDLAuto, env.getRequiredProperty(Hibernate_DDLAuto));
        properties.put(Hibernate_format_sql, env.getRequiredProperty(Hibernate_format_sql));
        return properties;
    }
}
