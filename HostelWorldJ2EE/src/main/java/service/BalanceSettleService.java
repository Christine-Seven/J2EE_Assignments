package service;

import model.BalanceSettle;

import java.util.List;

/**
 * Created by Seven on 18/03/2017.
 */
public interface BalanceSettleService {

    /**
     * 获得所有结算信息
     * @return
     */
    public List<BalanceSettle> getAll(String date);

    /**
     * 获得所有结算历史
     * @return
     */
    public List<BalanceSettle> getAllList();
}
