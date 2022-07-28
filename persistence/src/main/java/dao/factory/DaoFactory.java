package dao.factory;

import dao.DAO;
import dao.cased.impl.CaseDAOImpl;
import dao.user.impl.UserDAOImpl;
import entity.Entity;

public class DaoFactory {

    public static DAO create(Class<? extends Entity> entityClass){
        switch (entityClass.getSimpleName()){
            case "User":
                return new UserDAOImpl();
            case "Case":
                return new CaseDAOImpl();
            default:
                return null;
        }
    }
}
