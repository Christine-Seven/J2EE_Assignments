package dao.impl;

import dao.BaseDao;
import dao.RoomTypeDao;
import model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class RoomTypeDaoImpl implements RoomTypeDao {
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
    public void save(RoomType roomType) {
        baseDao.save(roomType);
    }

    @Override
    public void delete(int id) {
        baseDao.delete(RoomType.class,String.valueOf(id));
    }

    @Override
    public RoomType find(int id) {
        RoomType roomType=(RoomType)baseDao.load(RoomType.class,String.valueOf(id));
        return roomType;
    }

    @Override
    public RoomType queryByType(String type) {
        String sql="select * from hostelworld.roomType rt where rt.roomType=\""+type+"\";";
        List<RoomType> roomTypes=baseDao.querySQL(sql);
        return roomTypes.get(0);
    }

    @Override
    public List<RoomType> getAllType() {
        return baseDao.getAllList(RoomType.class);
    }

    @Override
    public boolean isExist(String type) {
        if(this.queryByType(type)!=null){
            return true;
        }
        return false;
    }

    @Override
    public long getNextId() {
        return baseDao.getTotalCount(RoomType.class)+1;
    }
}
