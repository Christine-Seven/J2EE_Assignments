package dao.impl;

import dao.BaseDao;
import dao.VipDao;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/15.
 */

@Repository
public class VipDaoImpl implements VipDao {

    @Autowired
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public BaseDao getBaseDao() {
        return baseDao;
    }

    @Override
    public void save(Vip vip) {
        try {
            baseDao.save(vip);
        }catch (Exception e){
            e.printStackTrace();
        }
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
    public List<Vip> getAllVipList() {
        @SuppressWarnings("Unchecked")
        List<Vip> list=baseDao.getAllList(Vip.class);
        return list;
    }
}
