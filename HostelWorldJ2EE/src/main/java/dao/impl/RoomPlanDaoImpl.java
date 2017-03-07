package dao.impl;

import dao.BaseDao;
import dao.RoomPlanDao;
import model.RoomPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        List<RoomPlan> roomPlans =baseDao.querySQL(sql);
        return roomPlans;
    }

    @Override
    public List<RoomPlan> queryByHostelAndType(String hostelNum, int roomTypeId) {
        String sql="select * from hostelworld.roomPlan as ri where ri.hostelNum=\""+hostelNum+"\" and roomTypeId="+roomTypeId+";";
        List<RoomPlan> roomPlans=baseDao.querySQL(sql);
        return roomPlans;
    }
}
