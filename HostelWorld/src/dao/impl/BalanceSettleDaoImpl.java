package dao.impl;

import dao.BalanceSettleDao;
import dao.BaseDao;
import model.BalanceSettle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class BalanceSettleDaoImpl implements BalanceSettleDao {

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
    public void save(BalanceSettle balanceSettle) {
        baseDao.save(balanceSettle);
    }

    @Override
    public List<BalanceSettle> getBalanceSettleByDate(String date) {
        String sql="select * from hostelworld.balanceSettle as bs where bs.settleDate=\""+date+"\";";
        return baseDao.querySQL(sql);
    }

    @Override
    public List<BalanceSettle> getBalanceSettleByHostel(String hostelNum) {
        String sql="select * from hostelworld.balanceSettle as bs where bs.hostelNum=\""+hostelNum+"\";";
        return baseDao.querySQL(sql);
    }
}
