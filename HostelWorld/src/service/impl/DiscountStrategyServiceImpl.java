package service.impl;

import dao.DiscountStrategyDao;
import model.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DiscountStrategyService;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class DiscountStrategyServiceImpl implements DiscountStrategyService {

    @Autowired
    private DiscountStrategyDao discountStrategyDao;

    @Override
    public DiscountStrategy getDiscount(int level) {
        return discountStrategyDao.find(level);
    }
}
