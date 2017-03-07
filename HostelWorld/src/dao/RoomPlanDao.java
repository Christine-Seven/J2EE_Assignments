package dao;

import model.RoomPlan;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface RoomPlanDao {

    /**
     * 新增计划
     * @param roomPlan
     */
    public void save(RoomPlan roomPlan);

    /**
     * 修改计划
     * @param roomPlan
     */
    public void update(RoomPlan roomPlan);

    /**
     * 获得下一条记录的id号
     * @return
     */
    public long getNextId();

    /**
     * 根据客栈查找计划
     * @param hostelNum
     * @return
     */
    public List<RoomPlan> queryByHostel(String hostelNum);

    /**
     * 获得客栈某一房型的计划
     * @param hostelNum
     * @param roomTypeId
     * @return
     */
    public List<RoomPlan> queryByHostelAndType(String hostelNum,int roomTypeId);

}
