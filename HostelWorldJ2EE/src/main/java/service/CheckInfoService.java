package service;

import model.CheckInfo;

import java.util.List;

/**
 * Created by Seven on 2017/2/23.
 */
public interface CheckInfoService {

    /**
     * 新增一条入离店记录
     * @param checkInfo
     */
    public void save(CheckInfo checkInfo);

    /**
     * 查找一条入离店记录
     * @param checkNum
     * @return
     */
    public CheckInfo find(int checkNum);

    /**
     * 修改一条入离店记录
     * @param checkInfo
     */
    public void update(CheckInfo checkInfo);

    /**
     * 付款
     * @param checkNum
     * @param money
     * @return
     */
    public boolean pay(int checkNum, double money);

    /**
     * 根据客栈编号查找入离店记录
     * @param hostelNum
     * @return
     */
    public List<CheckInfo> getCheckInfoByHostel(String hostelNum);

    /**
     * 根据客栈编号和住户编号查找入离店记录
     * @param hostelNum
     * @param lodgerNum
     * @return
     */
    public List<CheckInfo> getCheckInfoByHostelAndLodger(String hostelNum,String lodgerNum);

    /**
     * 根据住客入店日期查找记录
     * @param hostelNum
     * @param checkinDate
     * @return
     */
    public List<CheckInfo> getCheckInfoByCheckin(String hostelNum, String checkinDate);

    /**
     * 根据离店日期查找记录
     * @param hostelNum
     * @param checkoutDate
     * @return
     */
    public List<CheckInfo> getCheckInfoBycCheckout(String hostelNum, String checkoutDate);

    //如何获得在某两个日期之间的入住记录
    //即所有入店日期在结束日期之前，离店日期在开始日期之后
    //需要写一个访问数据库的方法
    //通过这个记录的条数，可以获得这两个日期间已被占用的房间数目
    //通过访问该时间段内最新的roomplan,获得房间总数
    //最后得到可预订房间数

    /**
     * 获得某两个日期之间的入住记录
     * @param startDate
     * @param endDate
     * @return
     */
    public List<CheckInfo> getCheckInfoBetween(String startDate, String endDate);
}
