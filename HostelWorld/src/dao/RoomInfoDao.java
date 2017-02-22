package dao;

import model.RoomInfo;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface RoomInfoDao {

    /**
     * 新增房间记录
     * @param roomInfo
     */
    public void save(RoomInfo roomInfo);

    /**
     * 修改房间记录
     * @param roomInfo
     */
    public void update(RoomInfo roomInfo);

    /**
     * 获得下一条记录的id号
     * @return
     */
    public long getNextId();

    /**
     * 根据客栈查找记录
     * @param hostelNum
     * @return
     */
    public List<RoomInfo> queryByHostel(String hostelNum);

}
