package dao.impl;

import dao.BankCardDao;
import dao.BaseDao;
import model.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Seven on 2017/2/22.
 */
@Repository
public class BankCardDaoImpl implements BankCardDao {
    @Autowired
    private BaseDao baseDao;


    @Override
    public void save(BankCard bankCard) {
        baseDao.save(bankCard);
    }

    @Override
    public void update(BankCard bankCard) {
        baseDao.update(bankCard);
    }

    @Override
    public BankCard find(String bankCardId) {
        BankCard bankCard=(BankCard)baseDao.load(BankCard.class,bankCardId);
        return bankCard;
    }
}
