package dao.impl;

import dao.BaseDao;
import dao.LodgerInfoDao;
import model.LodgerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class LodgerInfoDaoImpl implements LodgerInfoDao {
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
    public void save(LodgerInfo lodgerInfo) {
        baseDao.save(lodgerInfo);
    }

    @Override
    public String getLodgerNum() {
        long currentNum=baseDao.getTotalCount(LodgerInfo.class);
        DecimalFormat df=new DecimalFormat("000000");
        String newNum="N"+df.format(currentNum+1);
        return newNum;
    }


    @Override
    public void update(LodgerInfo lodgerInfo) {
        baseDao.update(lodgerInfo);
    }

    @Override
    public LodgerInfo find(String lodgerNum) {
        LodgerInfo lodgerInfo=(LodgerInfo)baseDao.load(LodgerInfo.class,lodgerNum);
        return lodgerInfo;
    }

    @Override
    public LodgerInfo queryByName(String lodgerName) {
        String sql="select * from hostelworld.lodgerInfo li where li.lodgerName=\""+lodgerName+"\";";
        List<LodgerInfo> lodgerInfos=baseDao.querySQL(sql);
        return lodgerInfos.get(0);
    }

    @Override
    public List<LodgerInfo> getAllLodgerInfo() {
        return baseDao.getAllList(LodgerInfo.class);
    }



}
