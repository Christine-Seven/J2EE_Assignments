package service;

import model.Score;

import java.sql.SQLException;

/**
 * Created by Seven on 2016/12/25.
 */
public interface TestEnquiresService {
    public Score getScorePO(String student_id) throws SQLException;


}
