package service;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by Seven on 2016/12/25.
 */
public interface UserDataService {
    public boolean getUserByID(String id, String password) throws NamingException, SQLException;
}