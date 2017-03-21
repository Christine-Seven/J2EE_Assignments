package service;

import model.CurrentSpareRoomInfo;

import java.util.List;

/**
 * Created by Seven on 2017/2/24.
 */
public interface CurrentSpareRoomService {

    /**
     * 获得当前的空房数目
     * @param hostelNum
     * @return
     */
    public List<CurrentSpareRoomInfo> getCurrentSpareRoom(String hostelNum);

    /**
     * 新增一条空房记录
     * @param currentSpareRoomInfo
     */
    public void save(CurrentSpareRoomInfo currentSpareRoomInfo);

    /**
     * 修改一条空房记录
     * @param currentSpareRoomInfo
     */
    public void update(CurrentSpareRoomInfo currentSpareRoomInfo);

    /**
     *
     * @param hostelNum
     * @param roomTypeId
     * @return
     */
    public CurrentSpareRoomInfo getCurrentSpareRoomByRoomType(String hostelNum,int roomTypeId);
}
