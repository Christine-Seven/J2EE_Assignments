package dao;

import model.BankCard;

/**
 * Created by Seven on 2017/2/22.
 */

public interface BankCardDao {
    /**
     * 新建银行卡
     * @param bankCard
     */
    public void save(BankCard bankCard);

    /**
     * 修改余额
     * @param bankCard
     */
    public void update(BankCard bankCard);

    /**
     * 查询余额
     * @param bankCardId
     * @return
     */
    public BankCard find(String bankCardId);
}
