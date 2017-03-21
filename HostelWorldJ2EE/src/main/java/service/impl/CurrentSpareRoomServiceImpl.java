package service.impl;

import dao.CurrentSpareRoomInfoDao;
import model.CurrentSpareRoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CurrentSpareRoomService;

import java.util.List;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class CurrentSpareRoomServiceImpl implements CurrentSpareRoomService {

    @Autowired
    private CurrentSpareRoomInfoDao currentSpareRoomInfoDao;

    @Override
    public List<CurrentSpareRoomInfo> getCurrentSpareRoom(String hostelNum) {
        return currentSpareRoomInfoDao.getInfoByHostel(hostelNum);
    }

    @Override
    public void save(CurrentSpareRoomInfo currentSpareRoomInfo) {
        currentSpareRoomInfoDao.save(currentSpareRoomInfo);
    }

    @Override
    public void update(CurrentSpareRoomInfo currentSpareRoomInfo) {
        currentSpareRoomInfoDao.update(currentSpareRoomInfo);
    }

    @Override
    public CurrentSpareRoomInfo getCurrentSpareRoomByRoomType(String hostelNum, int roomTypeId) {
        return currentSpareRoomInfoDao.getInfoByHostelAndRoomType(hostelNum,roomTypeId);
    }
}
