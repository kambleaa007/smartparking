package com.techmahindra.smartparking.config;

import static com.techmahindra.smartparking.constant.IConstant.DATASOURCE_JNDI;
import static com.techmahindra.smartparking.constant.IConstant.HIBERNATE_DATABASE;
import static com.techmahindra.smartparking.constant.IConstant.HIBERNATE_DIALECT;
import static com.techmahindra.smartparking.constant.IConstant.HIBERNATE_GENERATE_DDL;
import static com.techmahindra.smartparking.constant.IConstant.HIBERNATE_SHOW_SQL;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.interceptor.EntityInterceptor;

/**
 * PersistanceConfig.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:db_config.properties", "classpath:hibernate.properties" })
public class PersistanceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistanceConfig.class); // Initializing
                                                                                           // logger
    private static final String[] ENTITY_PACKAGES = { "com.techmahindra.smartparking.pojo.dbentity" };
    @Autowired
    Environment env;
    
    @Autowired
	private EntityInterceptor entityInterceptor;
	
	

    /**
     * Get the dataSource configured
     * 
     * @return dataSource - DataSource
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        LOGGER.info("datasource JNDI: " + DATASOURCE_JNDI + " -> "
                + env.getProperty(DATASOURCE_JNDI));
        DataSource dataSource = dsLookup.getDataSource(env.getProperty(DATASOURCE_JNDI));
        return dataSource;
    }

    /**
     * Get the entityManagerFactory configured
     * 
     * @param dataSource
     * @return localContainerEntityManagerFactoryBean -
     *         LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
            throws GenericDBException {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        try {
            localContainerEntityManagerFactoryBean.setDataSource(dataSource);
            localContainerEntityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
            jpaVendorAdapter.setShowSql(Boolean.valueOf(env.getProperty(HIBERNATE_SHOW_SQL)));
            jpaVendorAdapter
                    .setGenerateDdl(Boolean.valueOf(env.getProperty(HIBERNATE_GENERATE_DDL)));
            jpaVendorAdapter.setDatabasePlatform(env.getProperty(HIBERNATE_DIALECT));
            jpaVendorAdapter.setDatabase(Database.valueOf(env.getProperty(HIBERNATE_DATABASE)));
            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            throw new GenericDBException(e);
        }
        return localContainerEntityManagerFactoryBean;
    }

    /**
     * Get the transactionManager configured
     * 
     * @param dataSource
     * @return platformTransactionManager - PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource)
            throws GenericDBException {
        EntityManagerFactory entityManagerFactory;
        try {
            entityManagerFactory = entityManagerFactory(dataSource).getObject();
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            throw new GenericDBException(e);
        }
        return new JpaTransactionManager(entityManagerFactory);
    }
}