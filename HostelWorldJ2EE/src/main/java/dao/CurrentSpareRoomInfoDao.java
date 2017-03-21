package dao;

import model.CurrentSpareRoomInfo;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface CurrentSpareRoomInfoDao {

    /**
     * 新增一条空房记录
     * @param currentSpareRoomInfo
     */
    public void save(CurrentSpareRoomInfo currentSpareRoomInfo);

    /**
     * 获得下一条记录id
     * @return
     */
    public long getNextId();

    /**
     * 修改一条空房记录
     * @param currentSpareRoomInfo
     */
    public void update(CurrentSpareRoomInfo currentSpareRoomInfo);

    /**
     * 根据客栈编号获得其空房记录
     * @param hostelNum
     * @return
     */
    public List<CurrentSpareRoomInfo> getInfoByHostel(String hostelNum);

    /**
     * 根据客栈编号和房间类型获得空房数量
     * @param hostelNum
     * @param roomTypeId
     * @return
     */
    public CurrentSpareRoomInfo getInfoByHostelAndRoomType(String hostelNum,int roomTypeId);
}
