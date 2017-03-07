package service.impl;

import dao.CheckInfoDao;
import model.CheckInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CheckInfoService;

import java.util.List;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class CheckInfoServiceImpl implements CheckInfoService {
    @Autowired
    private CheckInfoDao checkInfoDao;

    @Override
    public void save(CheckInfo checkInfo) {
        checkInfoDao.save(checkInfo);
    }

    @Override
    public void update(CheckInfo checkInfo) {
        checkInfoDao.update(checkInfo);
    }

    @Override
    public boolean pay(int checkNum, double money) {
        CheckInfo checkInfo=checkInfoDao.find(checkNum);
        if(null!=checkInfo) {
            double currentPaid = checkInfo.getPaidMoney();
            checkInfo.setPaidMoney(currentPaid + money);
            checkInfoDao.update(checkInfo);
            return true;
        }
        return false;
    }

    @Override
    public List<CheckInfo> getCheckInfoByHostel(String hostelNum) {
        return checkInfoDao.getCheckInfoByHostel(hostelNum);
    }

    @Override
    public List<CheckInfo> getCheckInfoByHostelAndLodger(String hostelNum, String lodgerNum) {
        return checkInfoDao.getCheckInfoByHostelAndLodger(hostelNum,lodgerNum);
    }

    @Override
    public List<CheckInfo> getCheckInfoByCheckin(String hostelNum, String checkinDate) {
        return checkInfoDao.getCheckInfoByCheckin(hostelNum,checkinDate);
    }

    @Override
    public List<CheckInfo> getCheckInfoBycCheckout(String hostelNum, String checkoutDate) {
        return checkInfoDao.getCheckInfoBycCheckout(hostelNum,checkoutDate);
    }

    @Override
    public List<CheckInfo> getCheckInfoBetween(String startDate, String endDate) {
        return checkInfoDao.getCheckInfoBetween(startDate,endDate);
    }
}
