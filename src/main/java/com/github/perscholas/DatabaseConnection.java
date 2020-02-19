package com.github.perscholas;

import java.sql.*;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection {
    MARIADB;

    public Connection getConnection() {
        return getConnection(name().toLowerCase());
    }

    private Connection getConnection(String dbVendor) {
        String username = "root";
        String password = "";
        String databaseName = "SBA_Week8";
//        String dbVendor = name().toLowerCase();
        String url = "jdbc:" + dbVendor + "://127.0.0.1/";
        try {
            return DriverManager.getConnection(url+databaseName, username, password);
        } catch (SQLException e) {
            try{
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Error(e);
        }
    }


    public ResultSet executeQuery(String sqlStatement) throws SQLException {
        Connection connection = DatabaseConnection.MARIADB.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlStatement);
    }

}
