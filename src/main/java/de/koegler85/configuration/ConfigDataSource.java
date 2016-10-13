package de.koegler85.configuration;

import org.hibernate.SessionFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * @author Gabriel Kögler
 */

@Configuration
@ConfigurationProperties("properties/application.properties")
@EnableTransactionManagement
public class ConfigDataSource
{
    @Autowired
    private Environment environment;

    /**
     * <p>
     * Method sets the general datasource settings to an embedded H2 database.
     * </p>
     *
     * @return via <code>EmbeddedDatabaseBuilder</code> built and configured H2 database
     *
     * @see EmbeddedDatabaseBuilder
     * @see EmbeddedDatabase
     */
    @Bean( name = "DataSource" )
    @ConfigurationProperties( prefix = "spring.datasource.url" )
    public EmbeddedDatabase getDatasource ()
    {
        return new EmbeddedDatabaseBuilder ()
            .generateUniqueName(true)
            .setType(H2)
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
            .addScript("h2/createDatabase.sql")
            //.addScripts("user_data.sql", "country_data.sql")
            .build();
    }

    /**
     * <p>
     *     Sets hibernate session for DAO implementation layer.
     *     Injects autowired datasource bean and hinbernate properties.
     * </p>
     *
     * @return <code>LocalSessionFactoryBean</code>
     *
     * @see
     */
    @Bean( name = "sessionFactory" )
    public LocalSessionFactoryBean getSessionFactory()
    {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean ();
        sessionFactoryBean.setDataSource ( getDatasource () );
        sessionFactoryBean.setHibernateProperties ( hibernateProperties() );
        return sessionFactoryBean;
    }

    /**
     * <p>
     *     Reads the property entries from file in classpath.
     *     Configures general hibernate settings and hinbernate datasource settings.
     * </p>
     *
     * @return <code>Property</code>
     */
    private Properties hibernateProperties ()
    {
        Properties properties = new Properties ();

        // application name
        properties.setProperty ( "spring.application.name",
                                 environment.getProperty ( "spring.application.name" ) );

        // hibernate configuration { show_sql, format_sql, ddl-auto },
        properties.setProperty ( "spring.jpa.hibernate.ddl-auto",
                                 environment.getProperty ( "spring.jpa.hibernate.ddl-auto" ) );
        properties.setProperty ( "spring.jpa.hibernate.format_sql",
                                 environment.getProperty ( "spring.jpa.hibernate.format_sql" ) );
        properties.setProperty ( "spring.jpa.hibernate.show_sql",
                                 environment.getProperty ( "spring.jpa.hibernate.show_sql" ) );
        properties.setProperty ( "spring.datasource.driver-class-name",
                                 environment.getProperty ( "spring.datasource.driver-class-name" ) );

        // hibernate database settings { database driver, url, username, password }
        properties.setProperty ( "spring.datasource.driver-class-name",
                                 environment.getProperty ( "spring.datasource.driver-class-name" ) );
        properties.setProperty ( "spring.datasource.url",
                                 environment.getProperty ( "spring.datasource.url" ) );
        properties.setProperty ( "spring.datasource.username",
                                 environment.getProperty ( "spring.datasource.username" ) );
        properties.setProperty ( "spring.datasource.password",
                                 environment.getProperty ( "spring.datasource.password" ) );

        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager getHibernateTransactionManager( SessionFactory sessionFactory )
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager (  );
        transactionManager.setSessionFactory ( sessionFactory );
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor getPersistenceExceptionTranslationPostProcessor()
    {
        return new PersistenceExceptionTranslationPostProcessor ();
    }
}