package dataimpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Seven on 2016/12/19.
 */
public class DBConnection {

    public static Connection getConnection(){
        Connection connection=null;
        try {
            InitialContext ic = new InitialContext();
            DataSource ds=(DataSource)ic.lookup("java:comp/env/jdbc/j2eeStudentScore");
            connection=ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
