package com.duongprj.logistic_service.utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

@Component
public class DatabaseUtils {

    private final DataSource dataSource;

    public DatabaseUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getLastParcelId() {
        String lastId = null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id FROM parcel ORDER BY id DESC LIMIT 1")) {
            if (resultSet.next()) {
                lastId = resultSet.getString("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastId;
    }
}