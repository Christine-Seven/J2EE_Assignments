package service.impl;

import dao.VipDao;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import service.VipService;

import java.util.List;

/**
 * Created by Seven on 2017/2/15.
 */

public class VipServiceImpl implements VipService{
    @Autowired
    private VipDao vipDao;

    public VipDao getVipDao() {
        return vipDao;
    }

    public void setVipDao(VipDao vipDao) {
        this.vipDao = vipDao;
    }

    public void registerVip(Vip vip){
        vipDao.save(vip);
    }

    @Override
    public void updateVip(Vip vip) {
        vipDao.update(vip);
    }

    @Override
    public Vip findVipById(String vipNum) {
        return vipDao.find(vipNum);
    }

    @Override
    public List<Vip> getAllVipList() {
        return vipDao.getAllVipList();
    }
}
