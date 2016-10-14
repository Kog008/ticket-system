package de.koegler85.configuration;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * @author Gabriel Kögler
 */

@Configuration
@ConfigurationProperties("properties/application.yaml")
@EnableTransactionManagement
public class PersistenceConfig
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
    @Bean( name = "Datasource" )
    @ConfigurationProperties( prefix = "spring.datasource.url" )
    public EmbeddedDatabase getDatasource ()
    {
        return new EmbeddedDatabaseBuilder ()
            .generateUniqueName(true)
            .setType(H2)
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
            .addScript("h2/createDatabase.sql")
            .build();
    }

    public JpaVendorAdapter getJpaVendorAdapter()
    {
        // sets hibernate as jpa provider
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter ();

        adapter.setDatabase ( Database.H2 );
        adapter.setGenerateDdl ( true );
        adapter.setShowSql ( true );

        return adapter;
    }

    public LoadTimeWeaver getLoadTimeWeaver()
    {
        LoadTimeWeaver loadTimeWeaver = new TomcatLoadTimeWeaver ();

        return loadTimeWeaver;
    }

    @Bean( name = "entityManager" )
    public LocalContainerEntityManagerFactoryBean getEntityManager()
    {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

        entityManager.setDataSource ( getDatasource () );
        entityManager.setJpaVendorAdapter ( getJpaVendorAdapter () );
        entityManager.setLoadTimeWeaver ( getLoadTimeWeaver () );

        return entityManager;
    }

    /**
     * <p>
     *     Reads the property entries from file in classpath.
     *     Configures general hibernate settings and hinbernate datasource settings.
     * </p>
     *
     * @return <code>Property</code>
     */
    @Deprecated
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
}