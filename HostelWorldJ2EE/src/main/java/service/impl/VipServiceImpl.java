package service.impl;

import dao.BankCardDao;
import dao.VipDao;
import model.BankCard;
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
    @Autowired
    private BankCardDao bankCardDao;

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
        String bankCardId=vip.getBankCardId();

        BankCard bankCard=new BankCard();
        bankCard.setBankCardId(bankCardId);
        bankCard.setBalance(10000);
        bankCardDao.save(bankCard);
    }

    @Override
    public String getVipNum() {
        return vipDao.getVipNum();
    }

    @Override
    public boolean cancelVip(String vipNum) {
        Vip vip=vipDao.find(vipNum);
        vip.setState(VipStateEnum.CANCEL.toString());
        vipDao.update(vip);
        return true;
    }

    @Override
    public void updateVip(Vip vip) {
        String bankCardId=vip.getBankCardId();
        if(bankCardDao.find(bankCardId)==null) {
            BankCard bankCard = new BankCard();
            bankCard.setBankCardId(bankCardId);
            bankCard.setBalance(10000);
            bankCardDao.save(bankCard);
        }
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
