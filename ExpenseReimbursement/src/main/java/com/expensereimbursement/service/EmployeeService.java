package com.expensereimbursement.service;

import com.expensereimbursement.models.Employee;
import com.expensereimbursement.repository.EmployeeRepository;
import com.expensereimbursement.repository.EmployeeRepositoryImpl;

import java.util.List;

public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(){
        this.employeeRepository = new EmployeeRepositoryImpl();
    }

    public List<Employee> findAll(){
        return this.employeeRepository.findAll();
    }

    public boolean isValidEmployee(String email, String password){
        List<Employee> employees = this.findAll();
        for(Employee e : employees){
            if(e.getEmail().equals(email) && e.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean isValidManager(String email, String password){
        List<Employee> employees = this.findAll();
        for (Employee e: employees) {
            if (e.getEmail().equals(email) && e.getPassword().equals(password) && e.isManager() == true) {
                return true;
            }
        }
        return false;
    }

    public Employee findByEmail(String email){
        List<Employee> employees = this.findAll();
        for(Employee e : employees)
            if(e.getEmail().equals(email)){
                return e;
            }
        return null;
    }

    public Employee findManager (String email){
        List<Employee> employees1 = this.findAll();
        for(Employee e : employees1)
            if(e.getEmail().equals(email) && e.isManager()){
                return e;
            }
        return null;
    }


    public void updatePassword(String password, String email){
        this.employeeRepository.updatePassword(email,password);
    }

    public void deleteEmployee(String email){
        this.employeeRepository.deleteEmployee(email);
    }

}
