package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@HelloBootTest
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}