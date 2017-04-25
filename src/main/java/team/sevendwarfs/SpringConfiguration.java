package team.sevendwarfs;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置类, 配置文件为: application.properties
 * 配置了 c3p0数据源, Hibernate
 * Created by deng on 2017/4/23.
 */
@Configurable
@ComponentScan({ "team.sevendwarfs.persistence", "team.sevendwarfs.web.controller" })
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
public class SpringConfiguration {
    private static final String HIBERNATEDIALECT = "hibernate.dialect";
    private static final String HIBERNATESHOWSQL = "hibernate.show_sql";
    private static final String HIBERNATEDDLAUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATEPACKAGESCAN = "hibernate.packagesToScan";
    private static final String HIBERNATEFORMATSQL = "hibernate.format_sql";

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
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) throws
            PropertyVetoException, IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(HIBERNATEPACKAGESCAN));
        sessionFactoryBean.setHibernateProperties(hibProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean;
    }

    @Bean("sessionFactory")
    @Primary
    public SessionFactory sessionFactory(LocalSessionFactoryBean localSessionFactoryBean) throws
            PropertyVetoException, IOException {
        SessionFactory sessionFactory = localSessionFactoryBean.getObject();
        return sessionFactory;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
            (DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(env.getRequiredProperty(HIBERNATEPACKAGESCAN));

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibProperties());
        em.afterPropertiesSet();

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }




    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATEDIALECT, env.getRequiredProperty(HIBERNATEDIALECT));
        properties.put(HIBERNATESHOWSQL, env.getRequiredProperty(HIBERNATESHOWSQL));
        properties.put(HIBERNATEDDLAUTO, env.getRequiredProperty(HIBERNATEDDLAUTO));
        properties.put(HIBERNATEFORMATSQL, env.getRequiredProperty(HIBERNATEFORMATSQL));
        return properties;
    }
}
