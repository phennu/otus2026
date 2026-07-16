package ru.otus.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnectionManager {
     ResultSet executeQuery(String query) throws SQLException;
     void executeNoResult(String query) throws SQLException;
     void close() throws SQLException;
}
