package ru.otus.tables;

import ru.otus.database.IDBConnectionManager;
import ru.otus.database.SqlConnectionManager;
import ru.otus.factory.DBFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AbsTable {
    protected IDBConnectionManager idbConnectionManager;
    protected String tableName;
    protected Map<String, String> columns;

    public AbsTable(String tableName, IDBConnectionManager idbConnectionManager) throws SQLException {
        this.idbConnectionManager = idbConnectionManager;
        this.tableName = tableName;
        columns = new HashMap<>();

    }

    public void create(){
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, convertMapColumnsToString());
        try {
            idbConnectionManager.executeNoResult(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String convertMapColumnsToString(){
        String result = "";
        for(Map.Entry<String, String> el : columns.entrySet()){
            result += el.getKey() + " " + el.getValue() + ",";
        }
        result = result.substring(0, result.length()-1);
        return result;
    }
}
