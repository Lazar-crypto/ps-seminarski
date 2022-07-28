package dao.user;

import dao.DAO;

public interface UserDAO extends DAO {
    String findByCondition(String condition);
}
