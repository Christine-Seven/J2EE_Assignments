package utility;

import dataimpl.DBConnection;

import java.sql.Connection;

/**
 * Created by Seven on 2016/12/19.
 */
public class Constant{
    public static final Connection connection=DBConnection.getConnection();
}
