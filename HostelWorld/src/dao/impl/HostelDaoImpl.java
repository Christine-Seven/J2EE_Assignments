package dao.impl;

import dao.HostelDao;
import model.Hostel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Seven on 2017/2/14.
 */
public class HostelDaoImpl implements HostelDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public boolean checkHostel(String hostelNum) {
        //开启session
        Session session=sessionFactory.openSession();

        //开启事务
        session.beginTransaction();

        //查询语句
        Query query=session.createQuery(" from hostel where hostel.hostelNum=:hostelNum\n" +
                " ");

        //设定查询语句中变量的值
        query.setParameter("hostelNum",hostelNum);

        //查询结果
        Hostel hostel=(Hostel) query.uniqueResult();

        //事务提交并关闭session
        session.getTransaction().commit();
        session.close();

        //查询结果不为null，说明存在该hostel，返回true
        if(null!=hostel){
            return true;
        }

        return false;
    }

    @Override
    public boolean checkPassword(String hostelNum, String hostelPassword) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Query query=session.createQuery(" from hostel where hostel.hostelNum=:hostelNum and hostel.hostelPassword=:hostelPassword");
        query.setParameter("hostelNum",hostelNum);
        query.setParameter("hostelPassword",hostelPassword);
        Hostel hostel=(Hostel) query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        if(null!=hostel){
            return true;
        }

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
