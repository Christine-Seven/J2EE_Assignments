package service.impl;

import dao.BalanceSettleDao;
import model.BalanceSettle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BalanceSettleService;

import java.util.List;

/**
 * Created by Seven on 18/03/2017.
 */
@Service
public class BalanceSettleServiceImpl implements BalanceSettleService {
    @Autowired
    BalanceSettleDao balanceSettleDao;

    @Override
    public List<BalanceSettle> getAll(String date) {
        return balanceSettleDao.getBalanceSettleByDate(date);
    }

    @Override
    public List<BalanceSettle> getAllList() {
        return balanceSettleDao.getAll();
    }


}
