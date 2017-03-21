package dao.impl;

import dao.BaseDao;
import dao.CheckInfoDao;
import model.CheckInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class CheckInfoDaoImpl implements CheckInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public void save(CheckInfo checkInfo) {
        baseDao.save(checkInfo);
    }

    @Override
    public void update(CheckInfo checkInfo) {
        baseDao.update(checkInfo);
    }

    @Override
    public CheckInfo find(int checkNum) {
        CheckInfo checkInfo=(CheckInfo) baseDao.load(CheckInfo.class,checkNum);
        return checkInfo;
    }

    @Override
    public List<CheckInfo> getCheckInfoByHostel(String hostelNum) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+"\";";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getCheckInfo(objects);
    }

    @Override
    public List<CheckInfo> getCheckInfoByHostelAndLodger(String hostelNum, String lodgerNum) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+
                "\" and lodgerNum=\""+lodgerNum+"\";";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getCheckInfo(objects);
    }

    @Override
    public List<CheckInfo> getCheckInfoByCheckin(String hostelNum,String checkinDate) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+
                "\" and checkinDate=\""+checkinDate+"\";";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getCheckInfo(objects);
    }

    @Override
    public List<CheckInfo> getCheckInfoBycCheckout(String hostelNum,String checkoutDate) {
        String sql="select * from hostelworld.checkInfo as ci where ci.hostelNum=\""+hostelNum+
                "\" and checkoutDate=\""+checkoutDate+"\";";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getCheckInfo(objects);
    }

    @Override
    public List<CheckInfo> getCheckInfoBetween(String startDate, String endDate) {
        String sql="select * from hostelworld.checkInfo as ci where UNIX_TIMESTAMP(ci.checkinDate)<UNIX_TIMESTAMP(\"" +
                endDate +
                "\") AND " +
                "UNIX_TIMESTAMP(ci.checkoutDate)>UNIX_TIMESTAMP(\"" +
                startDate +
                "\");";
        List<Object[]> objects=baseDao.querySQL(sql);
        return this.getCheckInfo(objects);
    }

    private List<CheckInfo> getCheckInfo(List<Object[]> objects){
        List<CheckInfo> checkInfos=new ArrayList<>();
        for(Object[] object:objects){
            CheckInfo checkInfo=new CheckInfo();
            checkInfo.setHostelNum(String.valueOf(object[0]));
            checkInfo.setLodgerName(String.valueOf(object[1]));
            checkInfo.setPaidMoney((double)object[2]);
            checkInfo.setCheckCondition(String.valueOf(object[3]));
            checkInfo.setCheckinDate(String.valueOf(object[4]));
            checkInfo.setCheckoutDate(String.valueOf(object[5]));
            checkInfo.setCheckNum((int)object[6]);
            checkInfo.setRoomTypeId((int)object[7]);
            checkInfo.setRoomNum(String.valueOf(object[8]));
            checkInfo.setOrderNum(String.valueOf(object[9]));
            checkInfos.add(checkInfo);
        }
        return checkInfos;
    }
}
