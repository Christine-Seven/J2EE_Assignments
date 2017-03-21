package service;

/**
 * Created by Seven on 2017/2/24.
 */
public interface VipStatisticService {

    /**
     * 获得该会员的统计信息
     * @param vipNum
     * @return
     */
    public VipStaVO getVipStaInfo(String vipNum);
}
