package service.impl;

import dao.HostelDao;
import model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HostelService;

import java.util.List;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class HostelServiceImpl implements HostelService {
    @Autowired
    private HostelDao hostelDao;
    @Override
    public boolean checkHostel(String hostelNum) {
        return hostelDao.checkHostel(hostelNum);
    }

    @Override
    public boolean checkApprove(String hostelNum) {
        return hostelDao.checkApprove(hostelNum);
    }

    @Override
    public boolean checkPassword(String hostelNum, String hostelPassword) {
        return hostelDao.checkPassword(hostelNum,hostelPassword);
    }

    @Override
    public void registerHostel(Hostel hostel) {
        hostelDao.save(hostel);
    }

    @Override
    public String getHostelNum() {
        return hostelDao.getHostelNum();
    }

    @Override
    public void updateHostel(Hostel hostel) {
        hostelDao.updateHostel(hostel);
    }

    @Override
    public void deleteHostel(String hostelNum) {
        hostelDao.deleteHostel(hostelNum);
    }

    @Override
    public List<Hostel> queryHostelByProvince(String province) {
        return hostelDao.queryHostelByProvince(province);
    }

    @Override
    public List<Hostel> queryHostelByCity(String city) {
        return hostelDao.queryHostelByCity(city);
    }

    @Override
    public List<Hostel> queryHostelByName(String hostelName) {
        return hostelDao.queryHostelByName(hostelName);
    }

    @Override
    public Hostel queryHostelByNum(String hostelNum) {
        return hostelDao.queryHostelByNum(hostelNum);
    }

    @Override
    public List<Hostel> queryHostelByLevel(int level) {
        return hostelDao.queryHostelByLevel(level);
    }

    @Override
    public List<Hostel> queryAll() {
        return hostelDao.queryAll();
    }
}
