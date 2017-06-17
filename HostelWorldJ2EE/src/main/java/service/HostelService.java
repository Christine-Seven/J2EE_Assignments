package service;

import model.Hostel;

import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 2017/2/23.
 */
public interface HostelService {

    /**
     * 验证客栈是否存在
     * @param hostelNum
     * @return
     */
    public boolean checkHostel(String hostelNum);

    /**
     * 验证客栈是否被审批通过
     * @param hostelNum
     * @return
     */
    public boolean checkApprove(String hostelNum);

    /**
     * 验证客栈编号与密码正确
     * @param hostelNum
     * @param hostelPassword
     * @return
     */
    public boolean checkPassword(String hostelNum,String hostelPassword);

    /**
     * 新注册一家客栈
     * @param hostel
     */
    public void registerHostel(Hostel hostel);

    /**
     * 获得可用客栈编号
     * @return
     */
    public String getHostelNum();

    /**
     * 更新客栈信息
     * 需要审批，默认状态为disapprove
     * @param hostel
     */
    public void updateHostel(Hostel hostel);

    /**
     * 删除客栈
     * @param hostelNum
     */
    public void deleteHostel(String hostelNum);

    /**
     * 根据省市查找客栈
     * @param province
     * @return
     */
    public List<Hostel> queryHostelByProvince(String province);
    public List<Hostel> queryHostelByCity(String city);

    /**
     * 根据客栈名查找客栈
     * @param hostelName
     * @return
     */
    public List<Hostel> queryHostelByName(String hostelName);


    /**
     * 根据客栈编号查找客栈
     * @param hostelNum
     * @return
     */
    public Hostel queryHostelByNum(String hostelNum);

    /**
     * 根据客栈等级筛选客栈
     * @param level
     * @return
     */
    public List<Hostel> queryHostelByLevel(int level);

    /**
     * 获得所有客栈
     * @return
     */
    public List<Hostel> queryAll();

    /**
     * 根据审批状态获得订单
     * @param approveState
     * @return
     */
    public List<Hostel> queryByApprove(String approveState);

    /**
     * 根据客栈编号获得其每月平均房价
     * Integer-->月份，Double[0]-->adr，Double[1]-->实际客房收入
     * @param hostelNum
     * @return
     */
    public Map<Integer,Double[]> getAdrByHostel(String hostelNum);

    /**
     *  根据客栈编号获得其每月入住率
     *  Integer-->月份，Double[0]-->occ，Double[1]-->实际入住订单数
     * @param hostelNum
     * @return
     */
    public Map<Integer,Double[]> getOccByHostel(String hostelNum);

    /**
     * 根据客栈编号获得其每月平均每间可售房收入
     * Integer-->月份，Double[0]-->revpar，Double[1]-->可售房数量
     * @param hostelNum
     * @return
     */
    public Map<Integer,Double[]> getRevparByHostel(String hostelNum);

    /**
     * 根据客栈获得会员等级分布
     * @param hostelNum
     * @return
     */
    public Map<Integer,Integer> getLevelByHostel(String hostelNum);

    /**
     * 根据客栈获得指定月份的订单价格区间分布
     * @param hostelNum
     * @param months
     * @return
     */
    public Map<Integer, Map<String, Integer>> getPriceByMonth(String hostelNum, List<Integer> months);
}
