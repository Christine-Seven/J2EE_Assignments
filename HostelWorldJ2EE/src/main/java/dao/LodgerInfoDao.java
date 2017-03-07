package dao;

import model.LodgerInfo;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface LodgerInfoDao {

    /**
     * 新增一个住客
     * @param lodgerInfo
     */
    public void save(LodgerInfo lodgerInfo);

    /**
     * 获得可用住户编号
     * 若为会员，则为会员编号
     * 否则新生成
     * @return
     */
    public String getLodgerNum();

    /**
     * 修改住客信息
     * @param lodgerInfo
     */
    public void update(LodgerInfo lodgerInfo);

    /**
     * 根据住客编号查找
     * @param lodgerNum
     * @return
     */
    public LodgerInfo find(String lodgerNum);

    /**
     * 根据住客姓名查找
     * @param lodgerName
     * @return
     */
    public LodgerInfo queryByName(String lodgerName);

    /**
     * 获得所有住客信息
     * @return
     */
    public List<LodgerInfo> getAllLodgerInfo() ;
}
