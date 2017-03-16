package dao.impl;

import dao.BaseDao;
import dao.CurrentSpareRoomInfoDao;
import model.CurrentSpareRoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */

@Repository
public class CurrentSpareRoomInfoDaoImpl implements CurrentSpareRoomInfoDao {

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
    public void save(CurrentSpareRoomInfo currentSpareRoomInfo) {
        baseDao.save(currentSpareRoomInfo);
    }

    @Override
    public long getNextId() {
        return baseDao.getTotalCount(CurrentSpareRoomInfo.class)+1;
    }

    @Override
    public void update(CurrentSpareRoomInfo currentSpareRoomInfo) {
        baseDao.update(currentSpareRoomInfo);
    }

    @Override
    public List<CurrentSpareRoomInfo> getInfoByHostel(String hostelNum) {
        String sql="select * from hostelworld.currentSpareRoomInfo as csri where csri.hostelNum='"+hostelNum+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getCurrentSpareRoomInfo(objects);
    }

    private List<CurrentSpareRoomInfo> getCurrentSpareRoomInfo(List<Object[]> objects){
        List<CurrentSpareRoomInfo> currentSpareRoomInfos=new ArrayList<>();
        for(Object[] object:objects){
            CurrentSpareRoomInfo currentSpareRoomInfo=new CurrentSpareRoomInfo();
            currentSpareRoomInfo.setHostelNum(String.valueOf(object[0]));
            currentSpareRoomInfo.setSpareNum((int)object[1]);
            currentSpareRoomInfo.setId((int)object[2]);
            currentSpareRoomInfo.setRoomTypeId((int)object[3]);
            currentSpareRoomInfos.add(currentSpareRoomInfo);
        }
        return currentSpareRoomInfos;
    }
}
