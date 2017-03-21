package service;

/**
 * Created by Seven on 2017/2/24.
 */
public interface HostelStatisticService {

    /**
     * 获得一段时间内的客栈入住情况统计
     * @param hostelNum
     * @param startDate
     * @param endDate
     * @return
     */
    public HostelCheckInStaVO getHostelCheckInSta(String hostelNum,String startDate,String endDate);

    /**
     * 获得一段时间内的会员预订情况统计
     * @param hostelNum
     * @param startDate
     * @param endDate
     * @return
     */
    public HostelVipStaVO getHostelVipSta(String hostelNum,String startDate,String endDate);

    /**
     * 获得一段时间内的客栈财务情况统计
     * @param hostelNum
     * @param startDate
     * @param endDate
     * @return
     */
    public HostelBalanceStaVO getHostelBalanceSta(String hostelNum,String startDate,String endDate);



}
