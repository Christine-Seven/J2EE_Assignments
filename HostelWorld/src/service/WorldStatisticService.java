package service;

import model.WorldBalanceStaVO;
import model.WorldCheckInStaVO;
import model.WorldVipStaVO;

/**
 * Created by Seven on 2017/2/25.
 */
public interface WorldStatisticService {

    public WorldCheckInStaVO getWorldCheckInSta(String startDate,String endDate);

    public WorldBalanceStaVO getWorldBalanceSta(String startDate,String endDate);

    public WorldVipStaVO getWorldVipSta(String startDate,String endDate);
}
