package dao;

import model.BalanceSettle;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface BalanceSettleDao {

    /**
     * 新增结算历史
     * @param balanceSettle
     */
    public void save(BalanceSettle balanceSettle);

    /**
     * 根据时间获得结算记录
     * @param date
     * @return
     */
    public List<BalanceSettle> getBalanceSettleByDate(String date);

    /**
     * 根据客栈编号获得结算记录
     * @return
     */
    public List<BalanceSettle> getBalanceSettleByHostel(String hostelNum);
}
