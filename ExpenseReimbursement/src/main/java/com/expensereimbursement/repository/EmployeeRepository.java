package com.expensereimbursement.repository;

import com.expensereimbursement.models.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();

    List<Employee> findByManager(String email);

    void updateAddress(Employee e);

    void updateLastName(Employee e);

    void updatePassword(String email, String password);

    void updateAddress(String address);

    void deleteEmployee(String email);

}
