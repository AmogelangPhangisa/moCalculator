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

    public static DataSource getDatasource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setInitializationFailTimeout(0);
        dataSource.setMaximumPoolSize(5);
//        dataSource.setDataSourceClassName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
//        dataSource.addDataSourceProperty("url", "jdbc:sqlserver://LPTAYA61:2010;databaseName=calculatorDB");
//        dataSource.addDataSourceProperty("user", "amogelang");
//        dataSource.addDataSourceProperty("password", "amogelang");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://database-1.czye84gu27l8.eu-north-1.rds.amazonaws.com:3306/momecalculatordb");
        dataSource.addDataSourceProperty("user", "admin");
        dataSource.addDataSourceProperty("password", "Amogelang");
        
        dataSource.addDataSourceProperty("cachePrepStmts", "true");
        dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource.addDataSourceProperty("useServerPrepStmts", "true");
        

        return dataSource;
    }

    
    public static DataSource getDatasourceLookup() {
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/calculatorDS");
            return dataSource;
        } catch (NamingException ex) {
            Logger.getLogger(DataSourceUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
