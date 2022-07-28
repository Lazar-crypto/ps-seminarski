package dao.cased.impl;

import dao.cased.CaseDAO;
import entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CaseDAOImpl implements CaseDAO {

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getTableColumns() {
        return null;
    }

    @Override
    public Entity toEntity(ResultSet rs) throws SQLException {
        return null;
    }
}
