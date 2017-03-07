package service.impl;

import model.WorldBalanceStaVO;
import model.WorldCheckInStaVO;
import model.WorldVipStaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.*;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class WorldStatisticServiceImpl implements WorldStatisticService {
    @Autowired
    private HostelService hostelService;
    @Autowired
    private VipService vipService;
    @Autowired
    private HostelStatisticService hostelStatisticService;
    @Autowired
    private VipStatisticService vipStatisticService;

    @Override
    public WorldCheckInStaVO getWorldCheckInSta(String startDate, String endDate) {
        return null;
    }

    @Override
    public WorldBalanceStaVO getWorldBalanceSta(String startDate, String endDate) {
        return null;
    }

    @Override
    public WorldVipStaVO getWorldVipSta(String startDate, String endDate) {
        return null;
    }
}
