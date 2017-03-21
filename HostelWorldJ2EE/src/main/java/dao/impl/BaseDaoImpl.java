package dao.impl;


import dao.BaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Seven on 2017/2/19.
 */
@Repository
public class BaseDaoImpl implements BaseDao{

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Session getNewSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object load(Class c, String id) {
        Session session=getSession();
        return session.get(c,id);
    }

    @Override
    public Object load(Class c,int id){
        Session session=getSession();
        return session.get(c,id);
    }

    @Override
    public List getAllList(Class c){
        String hql="from "+c.getName();
        Session session=getSession();
        return session.createQuery(hql).list();
    }

    @Override
    public Long getTotalCount(Class c){
        Session session=getNewSession();
        String hql="select count(*) from "+c.getName();
        Long count=(Long) session.createQuery(hql).uniqueResult();
        session.close();
        return count!=null?count.longValue():0;
    }

    @Override
    public void save(Object obj) {
        try {
            Session session=getNewSession();
            session.save(obj);
            session.flush();
            session.clear();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object obj) {
            Session session=getNewSession();
            session.update(obj);
            session.flush();
            session.clear();
            session.close();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void delete(Object obj) {
            Session session=getNewSession();
            session.delete(obj);
            session.flush();
            session.clear();
            session.close();
    }

    @Override
    public void delete(Class c,String id){
        Object obj=getSession().get(c,id);
        if(obj!=null){
            getSession().delete(obj);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void delete(Class c, String[] ids) {
        for(String id:ids){
            Session session=getSession();
            Object obj=session.get(c,id);
            if(obj!=null){
                getSession().delete(obj);
            }
        }
    }

    @Override
    public List query(String hql) {
        Session session=getSession();
        return session.createQuery(hql).list();
    }

    @Override
    public List querySQL(String sql) {
        Session session=getSession();
        return session.createSQLQuery(sql).list();
    }

    @Override
    public Long getCount(String hql) {
        Session session=getNewSession();
        Long count=(Long) session.createQuery(hql).uniqueResult();
        session.close();
        return count!=null? count.longValue():0;
    }

    @Override
    public int excuteBySql(String sql) {
        int result;
        Session session=getSession();
        @SuppressWarnings("deprecation")
        SQLQuery query=session.createSQLQuery(sql);
        result=query.executeUpdate();
        return result;
    }
}
