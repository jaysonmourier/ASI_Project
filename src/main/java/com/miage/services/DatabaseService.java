package com.miage.services;

import java.sql.*;

public class DatabaseService {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projetasi";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static DatabaseService instance;
    private Connection conn;

    private DatabaseService() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public void connect() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void disconnect() throws SQLException {
        conn.close();
    }

    public ResultSet query(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public int update(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(query);
    }

    public void beginTransaction() throws SQLException {
        conn.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void rollback() throws SQLException {
        conn.rollback();
        conn.setAutoCommit(true);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return conn.prepareStatement(query);
    }

    public void setInt(PreparedStatement pstmt, int parameterIndex, int value) throws SQLException {
        pstmt.setInt(parameterIndex, value);
    }

    public void setFloat(PreparedStatement pstmt, int parameterIndex, float value) throws SQLException {
        pstmt.setFloat(parameterIndex, value);
    }

    public void setDouble(PreparedStatement pstmt, int parameterIndex, double value) throws SQLException {
        pstmt.setDouble(parameterIndex, value);
    }

    public void setString(PreparedStatement pstmt, int parameterIndex, String value) throws SQLException {
        pstmt.setString(parameterIndex, value);
    }

    public void setNull(PreparedStatement pstmt, int parameterIndex) throws SQLException {
        pstmt.setNull(parameterIndex, Types.INTEGER);
    }

    public ResultSet execute(PreparedStatement pstmt) throws SQLException {
        return pstmt.executeQuery();
    }

    public int executeUpdate(PreparedStatement pstmt) throws SQLException {
        return pstmt.executeUpdate();
    }
}

    