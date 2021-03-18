package com.expensereimbursement.repository;

import com.expensereimbursement.models.Employee;
import com.expensereimbursement.util.ConnectionClosers;
import com.expensereimbursement.util.ConnectionFactory;
import com.expensereimbursement.util.HibernateSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    private static Logger logger = LogManager.getFormatterLogger(EmployeeRepositoryImpl.class);

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        Session s = null;
        Transaction tx=null;

        try{
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();
            employees = s.createQuery("FROM Employee", Employee.class).getResultList();
            tx.commit();
        }catch (HibernateException e){
            logger.info("Something went wrong",e);
            tx.rollback();
        }finally {
            s.close();

        }

        return employees;
    }

    @Override
    public List<Employee> findByManager(String email) {

        return null;
    }

    @Override
    public void updateAddress(Employee e) {

    }

    @Override
    public void updateLastName(Employee e) {

    }

    @Override
    public void updatePassword(String password, String email) {


    }

    @Override
    public void updateAddress(String address) {

    }

    @Override
    public void deleteEmployee(String email) {

    }
}
