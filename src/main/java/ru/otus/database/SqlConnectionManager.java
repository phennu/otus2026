package ru.otus.database;

import java.sql.*;

public class SqlConnectionManager implements IDBConnectionManager{

    private static Connection connection = null;
    private static Statement statement = null;

    public SqlConnectionManager(){
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "test22";
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public void executeNoResult(String query) throws SQLException{
        statement.execute(query);
    }

    public void close() throws SQLException{
        if(statement != null){
            statement.close();
        }
        if(connection !=null){
            connection.close();
        }
    }
}
