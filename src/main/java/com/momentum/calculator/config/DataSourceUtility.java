/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author S2028873
 */
public class DataSourceUtility {
    public static DataSource getDatasourceLookup() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setInitializationFailTimeout(0);
        dataSource.setMaximumPoolSize(5);
        
        // AWS RDS MySQL configuration
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://database-1.czye84gu27l8.eu-north-1.rds.amazonaws.com:3306/momecalculatordb");
        dataSource.setUsername("admin");
        dataSource.setPassword("Amogelang");
        
        // Connection pool optimizations
        dataSource.addDataSourceProperty("cachePrepStmts", "true");
        dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource.addDataSourceProperty("useServerPrepStmts", "true");
        
        return dataSource;
    }
    
    // You can remove this method if it's not used elsewhere
    public static DataSource getDatasource() {
        return getDatasourceLookup();
    }
}

