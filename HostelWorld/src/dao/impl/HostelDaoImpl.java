package dao.impl;

import dao.BaseDao;
import dao.HostelDao;
import model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.ApprovalState;

import java.util.List;

/**
 * Created by Seven on 2017/2/14.
 */

@Repository
public class HostelDaoImpl implements HostelDao {

    @Autowired
    private BaseDao baseDao;

//    public void setBaseDao(BaseDaoImpl baseDao) {
//        this.baseDao = baseDao;
//    }
//
//    public BaseDao getBaseDao() {
//        return baseDao;
//    }

    @Override
    public boolean checkHostel(String hostelNum) {
        if(null!=this.queryHostelByNum(hostelNum)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkApprove(String hostelNum) {
        Hostel hostel=this.queryHostelByNum(hostelNum);
        if(hostel.getApprovalState().equals(ApprovalState.approve.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String hostelNum, String hostelPassword) {
        Hostel hostel=this.queryHostelByNum(hostelNum);
        if(hostel.getHostelPassword().equals(hostelPassword)){
            return true;
        }
        return false;
    }

    @Override
    public void addHostel(Hostel hostel) {
        baseDao.save(hostel);
    }

    @Override
    public void deleteHostel(String hostelNum) {
        baseDao.delete(Hostel.class,hostelNum);
    }

    @Override
    public void updateHostel(Hostel hostel) {
        baseDao.update(hostel);
    }

    @Override
    public List<Hostel> queryHostelByProvince(String province) {
        String sql="select * from hostelworld.hostel as h where h.provice=\""+province+"\";";
        List<Hostel> list=baseDao.querySQL(sql);
        return list;
    }

    @Override
    public List<Hostel> queryHostelByCity(String city) {
        String sql="select * from hostelworld.hostel as h where h.city=\""+city+"\";";
        List<Hostel> list=baseDao.querySQL(sql);
        return list;
    }

    @Override
    public List<Hostel> queryHostelByName(String hostelName) {
        String sql="select * from hostelworld.hostel as h where h.hostelName=\""+hostelName+"\";";
        List<Hostel> list=baseDao.querySQL(sql);
        return list;
    }

    @Override
    public Hostel queryHostelByNum(String hostelNum) {
        Hostel hostel=(Hostel) baseDao.load(Hostel.class,hostelNum);
        return hostel;
    }

    @Override
    public List<Hostel> queryHostelByLevel(int level) {
        String sql="select * from hostelworld.hostel as h where h.level="+level+";";
        List<Hostel> list=baseDao.querySQL(sql);
        return list;
    }

    @Override
    public List<Hostel> queryAll() {
        return baseDao.getAllList(Hostel.class);
    }

}
