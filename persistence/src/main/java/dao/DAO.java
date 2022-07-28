package dao;

import entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface DAO {

    String getTableName();

    String getTableColumns();

    Entity toEntity(ResultSet rs) throws SQLException;
}
