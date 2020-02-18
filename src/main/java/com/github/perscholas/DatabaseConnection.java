package com.github.perscholas;

import java.sql.*;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection {
    MYSQL;

    public Connection getConnection() {
        String username = "root";
        String password = "";
        String dbVendor = name().toLowerCase();
        String url = "jdbc:" + dbVendor + "://127.0.0.1/";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    public void executeStatement(String sqlStatement) {
    }

    public ResultSet executeQuery(String sqlQuery) {
        return null;
    }

    private Statement getScrollableStatement() {
        return null;
    }
}
