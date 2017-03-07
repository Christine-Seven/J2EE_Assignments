package service.impl;

import model.HostelBalanceStaVO;
import model.HostelCheckInStaVO;
import model.HostelVipStaVO;
import org.springframework.stereotype.Service;
import service.HostelStatisticService;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class HostelStatisticServiceImpl implements HostelStatisticService {
    @Override
    public HostelCheckInStaVO getHostelCheckInSta(String hostelNum, String startDate, String endDate) {
        return null;
    }

    @Override
    public HostelVipStaVO getHostelVipSta(String hostelNum, String startDate, String endDate) {
        return null;
    }

    @Override
    public HostelBalanceStaVO getHostelBalanceSta(String hostelNum, String startDate, String endDate) {
        return null;
    }
}
