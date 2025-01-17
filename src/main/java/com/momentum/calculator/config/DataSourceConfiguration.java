/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.config;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *
 * @author S2028873
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.momentum.calculator.persistence"}, transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = "com.momentum.calculator")
@EnableTransactionManagement
public class DataSourceConfiguration {
 @Bean(name = "localContainerEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Map<String, Object> hibernateProps = new LinkedHashMap<>();
        hibernateProps.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        hibernateProps.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
        hibernateProps.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
        hibernateProps.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
        hibernateProps.put(org.hibernate.cfg.Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        hibernateProps.put(org.hibernate.cfg.Environment.GENERATE_STATISTICS, true);
        hibernateProps.put(org.hibernate.cfg.Environment.DEFAULT_BATCH_FETCH_SIZE, 100);
        hibernateProps.put("hibernate.event.merge.entity_copy_observer", "allow");
        hibernateProps.put("org.hibernate.envers.audit_table_suffix", "_AUD");
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("server-persistence-unit");
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.momentum.calculator");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(hibernateProps);
        localContainerEntityManagerFactoryBean.setDataSource(DataSourceUtility.getDatasourceLookup());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(@Qualifier("localContainerEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return entityManagerFactoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "auditReader")
    AuditReader auditReader(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
    }

}
  // Comment for @Configuration
    // Indicates that this class is a configuration class for Spring.

    // Comment for @EnableJpaRepositories
    // Enables JPA repositories in the specified base package(s) and sets the transaction manager to be used.

    // Comment for @ComponentScan
    // Specifies the base package(s) to scan for Spring components (beans).

    // Comment for @EnableTransactionManagement
    // Enables Spring's annotation-driven transaction management.

    // Comment for @Bean
    // Indicates that the method entityManagerFactory() is a bean-producing method.
    // The specified name is used to uniquely identify this bean.

    // Comment for method entityManagerFactory()
    // Configures and returns a LocalContainerEntityManagerFactoryBean bean.

    // Comment for hibernateProps.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
    // Adds a Hibernate property for the SQL dialect to be used as SQL Server.

    // Comment for hibernateProps.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
    // Enables the display of SQL statements in the console.

    // Comment for hibernateProps.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
    // Enables the formatting of SQL statements.

    // Comment for hibernateProps.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
    // Configures Hibernate to automatically update the database schema based on the entity mappings.

    // Comment for hibernateProps.put(org.hibernate.cfg.Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
    // Enables lazy loading of associations outside of a transaction.

    // Comment for hibernateProps.put(org.hibernate.cfg.Environment.GENERATE_STATISTICS, true);
    // Enables the generation of statistics about Hibernate's internal state.

    // Comment for hibernateProps.put(org.hibernate.cfg.Environment.DEFAULT_BATCH_FETCH_SIZE, 100);
    // Configures the default batch fetch size for