package com.codecool.shop.dao.implementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public interface Queryhandler {

    String getConnectionConfigPath();

    default Connection getConnection() {
        Properties connection_props = new Properties();
        try {
            connection_props.load(new FileInputStream(getConnectionConfigPath()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
        String db_name = connection_props.getProperty("db_name");
        String db_url = connection_props.getProperty("db_url");
        String db_user = connection_props.getProperty("db_user");
        String db_password = connection_props.getProperty("db_password");
        String db_address = "jdbc:postgresql://" + db_url + "/" + db_name;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    db_address,
                    db_user,
                    db_password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
        return connection;
    }

    default PreparedStatement createPreparedStatement(Connection connection, String query, List<Object> parameters)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);

        Integer index = 1;
        for (Object parameter : parameters) {
            statement.setObject(index, parameter);
            index++;
        }
        return  statement;
    }

    default Integer executeDMLQuery(String query) {
        Integer result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ){
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    default Integer executeDMLQuery(String query, List<Object> parameters) {
        Integer result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = createPreparedStatement(connection, query, parameters);
        ){
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    default List<Map<String, Object>> executeSelectQuery(String query) {
        List<Map<String, Object>> resultListOfMaps = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> tempMap = new HashMap<>();
                for(int i=1; i<=numberOfColumns; i++){
                    tempMap.put(metadata.getColumnName(i),resultSet.getObject(i));
                }
                resultListOfMaps.add(tempMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultListOfMaps;
    }

    default List<Map<String, Object>> executeSelectQuery(String query, List<Object> parameters) {
        List<Map<String, Object>> resultListOfMaps = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = createPreparedStatement(connection, query, parameters)
        ){
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> tempMap = new HashMap<>();
                for(int i=1; i<=numberOfColumns; i++){
                    tempMap.put(metadata.getColumnName(i),resultSet.getObject(i));
                }
                resultListOfMaps.add(tempMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultListOfMaps;
    }
}
