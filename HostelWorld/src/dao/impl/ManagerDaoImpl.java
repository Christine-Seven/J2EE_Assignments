package dao.impl;

import dao.BaseDao;
import dao.ManagerDao;
import model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Seven on 2017/2/25.
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean checkManager(String managerNum) {
        Manager manager= (Manager) baseDao.load(Manager.class,managerNum);
        if(null!=manager){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String managerNum, String password) {
        Manager manager= (Manager) baseDao.load(Manager.class,managerNum);
        if(manager.getManagerPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public void save(Manager manager) {
        baseDao.save(manager);
    }

    @Override
    public void update(Manager manager) {
        baseDao.update(manager);
    }

    @Override
    public List<Manager> getAllList() {
        return baseDao.getAllList(Manager.class);
    }

    @Override
    public Manager queryByNum(String managerNum) {
        Manager manager=(Manager) baseDao.load(Manager.class,managerNum);
        return manager;
    }

    @Override
    public String getManagerNum() {
        DecimalFormat df=new DecimalFormat("000000");
        long currentNum=baseDao.getTotalCount(Manager.class);
        String newNum="M"+df.format(currentNum+1);
        return newNum;
    }
}
