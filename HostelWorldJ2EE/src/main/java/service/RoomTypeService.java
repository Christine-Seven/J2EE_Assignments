package service;

import model.RoomType;

/**
 * Created by Seven on 15/03/2017.
 */
public interface RoomTypeService {

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
}
