package dao.impl;

import dao.BaseDao;
import dao.CheckInfoDao;
import model.CheckInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class CheckInfoDaoImpl implements CheckInfoDao {

    @Autowired
    private BaseDao baseDao;

//    public void setBaseDao(BaseDao baseDao) {
//        this.baseDao = baseDao;
//    }
//
//    public BaseDao getBaseDao() {
//        return baseDao;
//    }

    @Override
    public void save(CheckInfo checkInfo) {
        baseDao.save(checkInfo);
    }

    @Override
    public void update(CheckInfo checkInfo) {
        baseDao.update(checkInfo);
    }

    @Override
    public List<CheckInfo> getCheckInfoByHostel(String hostelNum) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+"\";";
        return baseDao.querySQL(sql);
    }

    @Override
    public List<CheckInfo> getCheckInfoByHostelAndLodger(String hostelNum, String lodgerNum) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+
                "\" and lodgerNum=\""+lodgerNum+"\";";
        return baseDao.querySQL(sql);
    }

    @Override
    public List<CheckInfo> getCheckInfoByCheckin(String hostelNum,String checkinDate) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+
                "\" and checkinDate=\""+checkinDate+"\";";
        return baseDao.querySQL(sql);
    }

    @Override
    public List<CheckInfo> getCheckInfoBycCheckout(String hostelNum,String checkoutDate) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+
                "\" and checkoutDate=\""+checkoutDate+"\";";
        return baseDao.querySQL(sql);
    }
}
