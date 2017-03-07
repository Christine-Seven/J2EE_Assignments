package dao.impl;

import dao.ApprovalDao;
import dao.BaseDao;
import model.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/24.
 */
@Repository
public class ApprovalDaoImpl implements ApprovalDao{

    @Autowired
    private BaseDao baseDao;


    @Override
    public void save(Approval approval) {
        baseDao.save(approval);
    }

    @Override
    public void update(Approval approval) {
        baseDao.update(approval);
    }

    @Override
    public List<Approval> queryByState(String state) {
        String sql="select * from hostelworld.approval as a where a.approvalState=\""+state+"\";";
        List<Approval> approvals=baseDao.querySQL(sql);
        return approvals;
    }


    @Override
    public List<Approval> queryByType(String type) {
        String sql="select * from hostelworld.approval as a where a.approvalType=\""+type+"\";";
        List<Approval> approvals=baseDao.querySQL(sql);
        return approvals;
    }

    @Override
    public List<Approval> queryByHostel(String hostelNum) {
        String sql="select * from hostelworld.approval as a where a.hostelNum=\""+hostelNum+"\";";
        List<Approval> approvals=baseDao.querySQL(sql);
        return approvals;
    }

    @Override
    public List<Approval> getAllList() {
        return baseDao.getAllList(Approval.class);
    }
}
