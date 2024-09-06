package br.com.lux.config;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolConfig {
    private static BasicDataSource dataSource;

    private ConnectionPoolConfig() {
        getDataSource();
    }

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:h2:~/test");
            dataSource.setUsername("sa");
            dataSource.setPassword("sa");
            dataSource.setMinIdle(10);   // Número mínimo de conexões ociosas no pool
            dataSource.setMaxIdle(20);  // Número máximo de conexões ociosas no pool
            dataSource.setMaxTotal(50); // Número máximo de conexões totais no pool

            System.out.println("New connection pool created successfully");
        }

        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = getDataSource().getConnection();
        if (connection != null) {
            System.out.println("Connection established successfully");
        } else {
            System.out.println("Failed to establish connection");
        }
        return connection;
    }
}