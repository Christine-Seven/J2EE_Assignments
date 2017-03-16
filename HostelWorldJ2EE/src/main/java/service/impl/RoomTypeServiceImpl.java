package service.impl;

import dao.RoomTypeDao;
import model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RoomTypeService;

/**
 * Created by Seven on 15/03/2017.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    RoomTypeDao roomTypeDao;
    @Override
    public RoomType find(int id) {
        return roomTypeDao.find(id);
    }

    @Override
    public RoomType queryByType(String type) {
        return roomTypeDao.queryByType(type);
    }
}
