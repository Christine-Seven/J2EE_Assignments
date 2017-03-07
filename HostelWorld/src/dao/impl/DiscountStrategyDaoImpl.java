package dao.impl;

import dao.BaseDao;
import dao.DiscountStrategyDao;
import model.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Seven on 2017/2/22.
 */
@Repository
public class DiscountStrategyDaoImpl implements DiscountStrategyDao {
    @Autowired
    private BaseDao baseDao;

    @Override
    public void save(DiscountStrategy discountStrategy) {
        baseDao.save(discountStrategy);
    }

    @Override
    public void update(DiscountStrategy discountStrategy) {
        baseDao.update(discountStrategy);
    }

    @Override
    public DiscountStrategy find(int vipLevel) {
        DiscountStrategy discountStrategy=(DiscountStrategy)baseDao.load(DiscountStrategy.class,String.valueOf(vipLevel));
        return discountStrategy;
    }
}
