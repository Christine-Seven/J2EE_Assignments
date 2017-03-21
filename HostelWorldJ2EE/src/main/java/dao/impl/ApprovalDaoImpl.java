package dao.impl;

import dao.ApprovalDao;
import dao.BaseDao;
import model.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2017/2/24.
 */
@Repository
public class ApprovalDaoImpl implements ApprovalDao{

    @Autowired
    private BaseDao baseDao;


    @Override
    public Approval find(int approvalNum) {
        return (Approval) baseDao.load(Approval.class,approvalNum);
    }

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
        String sql="select * from hostelworld.approval as a where a.approveState='"+state+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getApprovals(objects);
    }


    @Override
    public List<Approval> queryByType(String type) {
        String sql="select * from hostelworld.approval as a where a.approvalType='"+type+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getApprovals(objects);
    }

    @Override
    public List<Approval> queryByHostel(String hostelNum) {
        String sql="select * from hostelworld.approval as a where a.hostelNum='"+hostelNum+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getApprovals(objects);
    }

    @Override
    public List<Approval> getAllList() {
        return baseDao.getAllList(Approval.class);
    }

    private List<Approval> getApprovals(List<Object[]> objects){
        List<Approval> approvals=new ArrayList<>();
        for(Object[] object:objects){
            Approval approval=new Approval();
            approval.setApprovalNum((int)object[0]);
            approval.setHostelNum(String.valueOf(object[1]));
            approval.setApproveDate(String.valueOf(object[2]));
            approval.setApprovalType(String.valueOf(object[3]));
            approval.setApproveState(String.valueOf(object[4]));
            approval.setApplyDate(String.valueOf(object[5]));
            approvals.add(approval);
        }
        return approvals;
    }
}
