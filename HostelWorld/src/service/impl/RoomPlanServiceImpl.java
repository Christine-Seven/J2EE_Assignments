package service.impl;

import dao.RoomPlanDao;
import dao.RoomTypeDao;
import model.RoomPlan;
import model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RoomPlanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class RoomPlanServiceImpl implements RoomPlanService {
    @Autowired
    private RoomPlanDao roomPlanDao;
    @Autowired
    private RoomTypeDao roomTypeDao;

    @Override
    public void saveRoomPlan(RoomPlan roomPlan) {
        roomPlanDao.save(roomPlan);
    }

    @Override
    public void update(RoomPlan roomPlan) {
        roomPlanDao.update(roomPlan);
    }

    @Override
    public long getNextId() {
        return roomPlanDao.getNextId();
    }

    @Override
    public List<RoomPlan> queryByHostel(String hostelNum) {
        return roomPlanDao.queryByHostel(hostelNum);
    }

    @Override
    public List<RoomPlan> queryNewestRoomPlan(String hostelNum) {
        List<RoomType> roomTypes=roomTypeDao.getAllType();
        List<RoomPlan> roomPlans=new ArrayList<>();
        for(RoomType roomType:roomTypes){
            String type=roomType.getRoomType();
            RoomPlan roomPlan=this.queryNewestPlanByType(hostelNum,type);
            roomPlans.add(roomPlan);
        }
        return roomPlans;
    }

    @Override
    public RoomPlan queryNewestPlanByType(String hostelNum, String roomType) {
        int roomTypeId=roomTypeDao.queryByType(roomType).getId();
        List<RoomPlan> roomPlans=roomPlanDao.queryByHostelAndType(hostelNum,roomTypeId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        RoomPlan newestPlan=roomPlans.get(0);
        try {
            Date date=sdf.parse(roomPlans.get(0).getDate());
            for(RoomPlan roomPlan:roomPlans){
                Date planDate=sdf.parse(roomPlan.getDate());
                if(planDate.after(date)){
                    date=planDate;
                    newestPlan=roomPlan;
                }
            }
            return newestPlan;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
