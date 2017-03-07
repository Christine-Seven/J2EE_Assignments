package service;

import model.DiscountStrategy;

/**
 * Created by Seven on 2017/2/22.
 */
public interface DiscountStrategyService {
    /**
     * 获得对应的优惠策略
     * @param level
     * @return
     */
    public DiscountStrategy getDiscount(int level);
}
