package service.impl;

import dao.ApprovalDao;
import model.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ApprovalService;

import java.util.List;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class ApprovalServiceImpl implements ApprovalService{
    @Autowired
    private ApprovalDao approvalDao;

    @Override
    public Approval find(int approvalNum) {
        return approvalDao.find(approvalNum);
    }

    @Override
    public void save(Approval approval) {
        approvalDao.save(approval);
    }

    @Override
    public void update(Approval approval) {
        approvalDao.update(approval);
    }

    @Override
    public List<Approval> queryByState(String state) {
        return approvalDao.queryByState(state);
    }

    @Override
    public List<Approval> queryByType(String type) {
        return approvalDao.queryByType(type);
    }

    @Override
    public List<Approval> queryByHostel(String hostelNum) {
        return approvalDao.queryByHostel(hostelNum);
    }

    @Override
    public List<Approval> getAllList() {
        return approvalDao.getAllList();
    }
}
