package ru.otus.factory;

import ru.otus.database.IDBConnectionManager;
import ru.otus.database.SqlConnectionManager;
import ru.otus.exceptions.DbNotSupported;

import java.sql.SQLException;

public class DBFactory {
    public IDBConnectionManager getConnectionManager (String dbType) throws SQLException{
        switch (dbType){
            case "SQL_DB" ->{
                return new SqlConnectionManager();
            }
        }
        throw new DbNotSupported(dbType);
    }
}
