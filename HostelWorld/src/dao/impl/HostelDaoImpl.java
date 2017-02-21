package dao.impl;

import dao.BaseDao;
import dao.HostelDao;
import model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/14.
 */
@Repository
public class HostelDaoImpl implements HostelDao {

    @Autowired
    private BaseDao baseDao;

    public void setBaseDao(BaseDaoImpl baseDao) {
        this.baseDao = baseDao;
    }

    public BaseDao getBaseDao() {
        return baseDao;
    }

    @Override
    public boolean checkHostel(String hostelNum) {
        return false;
    }

    @Override
    public boolean checkPassword(String hostelNum, String hostelPassword) {

        return false;
    }

    @Override
    public boolean addHostel(Hostel hostel) {

        return false;
    }

    @Override
    public boolean deleteHostel(String hostelNum) {
        return false;
    }

    @Override
    public boolean updateHostel(Hostel hostel) {
        return false;
    }

    @Override
    public List<Hostel> queryHostelByProvince(String province) {
        return null;
    }

    @Override
    public List<Hostel> queryHostelByCity(String city) {
        return null;
    }

    @Override
    public Hostel queryHostelByNum(String hostelNum) {
        return null;
    }

    @Override
    public List<Hostel> queryAll() {
        return null;
    }


}
