package dataimpl;


import utility.Constant;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Seven on 2016/12/19.
 */
public class UserDataServiceImpl {

    public boolean getUserByID(String id,String password) throws NamingException, SQLException {

        Statement statement= Constant.connection.createStatement();
        //check if the user is valid
        String getuser_sql="SELECT student_id FROM student " +
                "WHERE student_id="+id +" AND password="+password+";";
        ResultSet rs=statement.executeQuery(getuser_sql);
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }
}
