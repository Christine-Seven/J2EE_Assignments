package dao.impl;

import dao.BaseDao;
import dao.VipDao;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Seven on 2017/2/15.
 */

@Repository
public class VipDaoImpl implements VipDao {

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
    public void save(Vip vip) {
        try {
            baseDao.save(vip);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getVipNum() {
        long currentNum=baseDao.getTotalCount(Vip.class);
        DecimalFormat df=new DecimalFormat("000000");
        String newNum="V"+df.format(currentNum+1);
        return newNum;
    }

    @Override
    public void update(Vip vip) {
        baseDao.update(vip);
    }

    @Override
    public Vip find(String vipNum) {
        Vip vip=(Vip)baseDao.load(Vip.class,vipNum);
        return vip;
    }

    @Override
    public boolean checkVip(String vipNum) {
        if(this.find(vipNum)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String vipNum, String password) {
        Vip vip=this.find(vipNum);
        if(vip.getVipPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public List<Vip> queryByName(String vipName) {
        String sql="select * from hostelworld.vip v where v.vipName=\""+vipName+"\";";
        return baseDao.querySQL(sql);
    }

    @Override
    public List<Vip> getAllVipList() {
        List<Vip> list=baseDao.getAllList(Vip.class);
        return list;
    }
}
