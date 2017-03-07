package dao;

import model.RoomType;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface RoomTypeDao {

    /**
     * 新增房间类型
     * @param roomType
     */
    public void save(RoomType roomType);

    /**
     * 删除房间类型
     * @param id
     */
    public void delete(int id);

    /**
     * 根据id查找对应房间类型
     * @param id
     * @return
     */
    public RoomType find(int id);

    /**
     * 根据类型查找对应id
     * @param type
     * @return
     */
    public RoomType queryByType(String type);

    /**
     * 获得所有房间类型
     * @return
     */
    public List<RoomType> getAllType();

    /**
     * 验证该房间类型是否存在
     * @param type
     * @return
     */
    public boolean isExist(String type);

    /**
     * 获得下一个房间类型的id号
     * @return
     */
    public long getNextId();

}
