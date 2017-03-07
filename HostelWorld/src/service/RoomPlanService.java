package service;

import model.RoomPlan;

import java.util.List;

/**
 * Created by Seven on 2017/2/23.
 */
public interface RoomPlanService {

    /**
     * 新增一条计划
     * @param roomPlan
     */
    public void saveRoomPlan(RoomPlan roomPlan);

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
     * 获得客栈所有房型的最新计划
     * @param hostelNum
     * @return
     */
    public List<RoomPlan> queryNewestRoomPlan(String hostelNum);

    /**
     * 获得客栈某一房型的最新计划
     * @param hostelNum
     * @param roomType
     * @return
     */
    public RoomPlan queryNewestPlanByType(String hostelNum, String roomType);

}
