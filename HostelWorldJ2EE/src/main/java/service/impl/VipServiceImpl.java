package service.impl;

import dao.VipDao;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.VipService;
import util.VipStateEnum;

import java.util.List;

/**
 * Created by Seven on 2017/2/15.
 */

@Service
public class VipServiceImpl implements VipService{

    @Autowired
    private VipDao vipDao;

    @Override
    public boolean isExist(String vipNum) {
        return vipDao.checkVip(vipNum);
    }

    @Override
    public boolean checkPassword(String vipNum, String password) {
        return vipDao.checkPassword(vipNum,password);
    }

    @Override
    public void registerVip(Vip vip) {
        vipDao.save(vip);
    }

    @Override
    public String getVipNum() {
        return vipDao.getVipNum();
    }

    @Override
    public boolean cancelVip(String vipNum,String date) {
        Vip vip=vipDao.find(vipNum);
        vip.setState(VipStateEnum.cancel.toString());
        vipDao.update(vip);
        return true;
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
    public List<Vip> queryByName(String vipName) {
        return vipDao.queryByName(vipName);
    }

    @Override
    public List<Vip> getAllVipList() {
        return vipDao.getAllVipList();
    }
}
