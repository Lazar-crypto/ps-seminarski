package dao.user.impl;


import dao.user.UserDAO;
import entity.implementation.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getTableColumns() {
        return "attorneyIdentificationNumber, name, surname, email, password";
    }

    @Override
    public User toEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int attorneyIdentificationNumber = rs.getInt("attorneyIdentificationNumber");
        String name = rs.getString("name");
        String surnamge = rs.getString("surname");
        String email = rs.getString("email");
        String password = rs.getString("password");

        return new User(id,attorneyIdentificationNumber,name,surnamge,email,password);

    }

    @Override
    public String findByCondition(String condition) {
        return  "SELECT *" +
                " FROM user" +
                " WHERE" + condition + ";";
    }

}
