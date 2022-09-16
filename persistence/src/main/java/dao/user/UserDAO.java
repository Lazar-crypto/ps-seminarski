package dao.user;

import dao.DAO;

public interface UserDAO extends DAO {
    String find(String condition);
}
