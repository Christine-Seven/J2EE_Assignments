package dao.impl;

import dao.BaseDao;
import dao.HostelDao;
import model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.ApprovalStateEnum;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
        if(hostel.getApprovalState().equals(ApprovalStateEnum.approve.toString())){
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
    public void save(Hostel hostel) {
        baseDao.save(hostel);
    }

    @Override
    public String getHostelNum() {
        long currentNum=baseDao.getTotalCount(Hostel.class);
        DecimalFormat df=new DecimalFormat("000000");
        String newNum="H"+df.format(currentNum+1);
        return newNum;
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
        String sql="select * from hostelworld.hostel as h where h.provice='"+province+"';";
        List<Hostel> list=baseDao.querySQL(sql);
        return list;
    }

    @Override
    public List<Hostel> queryHostelByCity(String city) {
        String sql="select * from hostelworld.hostel as h where h.city='"+city+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        List<Hostel> hostels=this.getHostels(objects);
        return hostels;
    }

    @Override
    public List<Hostel> queryHostelByName(String hostelName) {
        String sql="select * from hostelworld.hostel as h where h.hostelName='"+hostelName+"';";
        List<Object[]> objects=baseDao.querySQL(sql);
        List<Hostel> hostels=this.getHostels(objects);
        return hostels;
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

    private List<Hostel> getHostels(List<Object[]> objects){
        List<Hostel> hostels=new ArrayList<Hostel>();
        for(Object[] object:objects){
            Hostel hostel=new Hostel();
            hostel.setHostelNum(String.valueOf(object[0]));
            hostel.setHostelPassword(String.valueOf(object[1]));
            hostel.setProfit((double)object[2]);
            hostel.setProvince(String.valueOf(object[3]));
            hostel.setCity(String.valueOf(object[4]));
            hostel.setAddress(String.valueOf(object[5]));
            hostel.setHostelInfo(String.valueOf(object[6]));
            hostel.setApprovalState(String.valueOf(object[7]));
            hostel.setLevel((int)object[8]);
            hostel.setHostelName(String.valueOf(object[9]));;
            hostel.setApplyDate(String.valueOf(object[10]));
            hostels.add(hostel);
        }
        return hostels;
    }

}
