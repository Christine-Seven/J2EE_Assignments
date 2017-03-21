package dao.impl;

import dao.BaseDao;
import dao.RoomPlanDao;
import model.RoomPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class RoomPlanDaoImpl implements RoomPlanDao {
    @Autowired
    private BaseDao baseDao;

//    public void setBaseDao(BaseDao baseDao) {
//        this.baseDao = baseDao;
//    }
//
//    public BaseDao getBaseDao() {
//        return baseDao;
//    }

    @Override
    public void save(RoomPlan roomPlan) {
        baseDao.save(roomPlan);
    }

    @Override
    public void update(RoomPlan roomPlan) {
        baseDao.update(roomPlan);
    }

    @Override
    public long getNextId() {
        return baseDao.getTotalCount(RoomPlan.class)+1;
    }

    @Override
    public List<RoomPlan> queryByHostel(String hostelNum) {
        String sql="select * from hostelworld.roomPlan as ri where ri.hostelNum=\""+hostelNum+"\";";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getRoomPlan(objects);
    }

    @Override
    public List<RoomPlan> queryByHostelAndType(String hostelNum, int roomTypeId) {
        String sql="select * from hostelworld.roomPlan as ri where ri.hostelNum=\""+hostelNum+"\" and roomTypeId="+roomTypeId+";";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getRoomPlan(objects);
    }

    private List<RoomPlan> getRoomPlan(List<Object[]> objects){
        List<RoomPlan> roomPlans=new ArrayList<>();
        for(Object[] object:objects){
            RoomPlan roomPlan=new RoomPlan();
            roomPlan.setId((int)object[0]);
            roomPlan.setHostelNum(String.valueOf(object[1]));
            roomPlan.setRoomTypeId((int)object[2]);
            roomPlan.setRoomNum((int)object[3]);
            roomPlan.setStartDate(String.valueOf(object[4]));
            roomPlan.setEndDate(String.valueOf(object[5]));
            roomPlan.setRoomPrice((double)object[6]);
            roomPlan.setDate(String.valueOf(object[7]));
            roomPlans.add(roomPlan);
        }
        return roomPlans;
    }
}
