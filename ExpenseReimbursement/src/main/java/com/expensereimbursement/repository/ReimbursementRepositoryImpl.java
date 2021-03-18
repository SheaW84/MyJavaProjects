package com.expensereimbursement.repository;

import com.expensereimbursement.models.Employee;
import com.expensereimbursement.models.ReimbursementRequest;
import com.expensereimbursement.util.HibernateSessionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepositoryImpl implements ReimbursementRepository {

    private static Logger logger = LogManager.getFormatterLogger(ReimbursementRepositoryImpl.class);

    @Override
    public List<ReimbursementRequest> findAll() {
        List<ReimbursementRequest> requests = new ArrayList<>();

        Session s = null;
        Transaction tx = null;
        try{
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();
            requests = s.createQuery("FROM ReimbursementRequest",ReimbursementRequest.class)
                    .getResultList();
            tx.commit();
        }catch (HibernateException e){
            logger.info("Unable to find all requests",e);
            tx.rollback();
        }finally {
            s.close();
        }
        return requests;
    }

    @Override
    public ReimbursementRequest findById(int id) {
        ReimbursementRequest r = null;
        Session s=null;
        Transaction tx =null;
        try{
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();
            r = s.get(ReimbursementRequest.class,id);
            tx.commit();
        }catch(HibernateException e){
            logger.warn("Can't locate the request",e);
            tx.rollback();
        }finally{
            s.close();
        }
        return r;
    }

    @Override
    public List<ReimbursementRequest> findByStatus() {
        return null;
    }

    @Override
    public List<ReimbursementRequest> findByUser(Employee employee) {

        List<ReimbursementRequest> requests = new ArrayList<>();
        Session s = null;
        Transaction tx = null;
        try{
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();
            requests = s.createQuery("FROM ReimbursementRequest r WHERE r.employee =: e",ReimbursementRequest.class)
                    .setParameter("e",employee)
                    .getResultList();

        }catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            s.close();
        }
        return requests;
    }


    @Override
    public void insert(ReimbursementRequest r) {

        Session s = null;
        Transaction tx = null;

        try{
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();
            s.save(r);
            tx.commit();
        }catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            s.close();
        }
    }


    @Override
    public void delete(ReimbursementRequest r) {

        Session s = null;
        Transaction tx = null;
        try{
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();
            s.delete(r);
            tx.commit();
        }catch (HibernateException e){
            logger.warn("There was a problem deleting request",e);
            tx.rollback();
        }finally{
            s.close();
        }
    }

    @Override
    public void update(ReimbursementRequest r) {

        Session s = null;
        Transaction tx =  null;
        try{
            s=HibernateSessionFactory.getSession();
            tx = s.beginTransaction();
            s.update(r);
            tx.commit();
        }catch(HibernateException e){
            logger.warn("There was a problem updating request", e);
            tx.rollback();
        }finally{
            s.close();
        }
    }
}
