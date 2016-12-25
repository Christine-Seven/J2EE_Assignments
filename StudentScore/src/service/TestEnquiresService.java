package service;

import dao.ScoreDAO;

import java.sql.SQLException;

/**
 * Created by Seven on 2016/12/25.
 */
public interface TestEnquiresService {
    public ScoreDAO getScorePO(String student_id) throws SQLException;



    }
