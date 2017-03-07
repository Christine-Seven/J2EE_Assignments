package dao;

import model.CheckInfo;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface CheckInfoDao {

    /**
     * 新增一条入离店记录
     * @param checkInfo
     */
    public void save(CheckInfo checkInfo);

    /**
     * 修改一条入离店记录
     * @param checkInfo
     */
    public void update(CheckInfo checkInfo);

    /**
     * 根据记录编号查找
     * @param checkNum
     * @return
     */
    public CheckInfo find(int checkNum);

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


    /**
     * 获得某两个日期之间的入住记录
     * @param startDate
     * @param endDate
     * @return
     */
    public List<CheckInfo> getCheckInfoBetween(String startDate, String endDate);
}
