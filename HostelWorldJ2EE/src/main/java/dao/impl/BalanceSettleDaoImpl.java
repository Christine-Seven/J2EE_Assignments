package dao.impl;

import dao.BalanceSettleDao;
import dao.BaseDao;
import model.BalanceSettle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class BalanceSettleDaoImpl implements BalanceSettleDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public void save(BalanceSettle balanceSettle) {
        baseDao.save(balanceSettle);
    }

    @Override
    public List<BalanceSettle> getAll() {
        return baseDao.getAllList(BalanceSettle.class);
//        String sql="select * from hostelworld.balanceSettle;";
//        List<Object[]> objects=baseDao.querySQL(sql);
//        return this.getBalanceSettle(objects);
    }

    @Override
    public void update(BalanceSettle balanceSettle) {
        baseDao.update(balanceSettle);
    }


    @Override
    public List<BalanceSettle> getBalanceSettleByDate(String date) {
        String sql="select * from hostelworld.balanceSettle as bs where bs.settleDate='"+date+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getBalanceSettle(objects);
    }

    @Override
    public List<BalanceSettle> getBalanceSettleByHostel(String hostelNum) {
        String sql="select * from hostelworld.balanceSettle as bs where bs.hostelNum='"+hostelNum+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getBalanceSettle(objects);
    }

    @Override
    public List<BalanceSettle> getBalanceSettleByCondition(String condition) {
        String sql="select * from hostelworld.balanceSettle as bs where bs.settleCondition='"+condition+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getBalanceSettle(objects);
    }

    @Override
    public BalanceSettle getByHostelAndCondition(String hostelNum, String condition) {
        String sql="select * from hostelworld.balanceSettle as bs where bs.settleCondition='"+condition+"' and hostelNum='" +hostelNum
                +"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        List<BalanceSettle> balanceSettles=this.getBalanceSettle(objects);
        if(balanceSettles.size()>0){
            return balanceSettles.get(0);
        }else{
            return null;
        }

    }

    private List<BalanceSettle> getBalanceSettle(List<Object[]> objects){
        List<BalanceSettle> balanceSettles=new ArrayList<>();
        for(Object[] object:objects){
            BalanceSettle balanceSettle=new BalanceSettle();
            balanceSettle.setSettleNum((int)object[0]);
            balanceSettle.setSettleDate(String.valueOf(object[1]));
            balanceSettle.setBalance((double)object[2]);
            balanceSettle.setSettleCondition(String.valueOf(object[3]));
            balanceSettle.setHostelNum(String.valueOf(object[4]));
            balanceSettles.add(balanceSettle);
        }
        return balanceSettles;
    }
}
