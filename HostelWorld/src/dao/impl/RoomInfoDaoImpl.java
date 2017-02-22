package dao.impl;

import dao.BaseDao;
import dao.RoomInfoDao;
import model.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class RoomInfoDaoImpl implements RoomInfoDao {
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
    public void save(RoomInfo roomInfo) {
        baseDao.save(roomInfo);
    }

    @Override
    public void update(RoomInfo roomInfo) {
        baseDao.update(roomInfo);
    }

    @Override
    public long getNextId() {
        return baseDao.getCount("from hostelworld.roomInfo")+1;
    }

    @Override
    public List<RoomInfo> queryByHostel(String hostelNum) {
        String sql="select * from hostelworld.roomInfo as ri where ri.hostelNum=\""+hostelNum+"\";";
        List<RoomInfo> roomInfos=baseDao.querySQL(sql);
        return roomInfos;
    }
}
