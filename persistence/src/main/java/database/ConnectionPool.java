package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnectionPool {
    
    public static final LinkedList<Connection> POOL = new LinkedList<>();
    
     static {
        String url = DatabaseProperties.getInstance().getProperty("database_url");
        String username = DatabaseProperties.getInstance().getProperty("database_user");
        String password = DatabaseProperties.getInstance().getProperty("database_password");
        String numberOfConnectionsStr = DatabaseProperties.getInstance().getProperty("number_of_connections");
        int numberOfConnections = Integer.parseInt(numberOfConnectionsStr);
        
        for (int i = 0; i < numberOfConnections; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                POOL.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     
     public static Connection getConnection(){
        if (POOL.isEmpty()){
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
            return getConnection();
        }
        
        return POOL.pop();
    }

    public static boolean releaseConnection(Connection connection){
        return POOL.add(connection);
    }
    
}
