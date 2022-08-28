package repository;

import dao.DAO;
import database.ConnectionPool;
import entity.Entity;
import lombok.extern.java.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log
public class Repository {
    
    private final Connection connection;
    public Repository() {
        this.connection = ConnectionPool.getConnection();
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        ConnectionPool.releaseConnection(connection);
    }

    public void rollbackTransaction() throws SQLException {
        connection.commit();
    }

    public Connection getConnection() {
        return connection;
    }

    public List<Entity> findByCondition(String condition, DAO dao){
        List<Entity> entities = null;
        try {
            Method find = dao.getClass().getMethod("findByCondition", String.class);
            Method toEntity = dao.getClass().getMethod("toEntity", ResultSet.class);

            String findQuery = (String) find.invoke(dao, condition);
            log.info("Query for execution: " + findQuery);

            PreparedStatement preparedStatement = getConnection().prepareStatement(findQuery);
            ResultSet rs = preparedStatement.executeQuery();
            entities = new ArrayList<>();
            while (rs.next()){
                Entity entity = (Entity) toEntity.invoke(dao, rs);
                entities.add(entity);
            }
            preparedStatement.close();
            rs.close();

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | SQLException e) {
            log.warning(e.getMessage());
        }
        return entities;
    }

}
