package es.ies.puerto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteConnectionManager {
    private static String jdbcUrl = "jdbc:sqlite:/src/database/centroplus.db";
    public static Connection getConnection() throws SQLException { return DriverManager.getConnection(jdbcUrl); }
    public static void setJdbcUrl(String url) { jdbcUrl = url; }
    public static void setDatabasePath(String testDb) {
        jdbcUrl = "jdbc:sqlite:" + testDb;
    }
}
