package com.expensereimbursement.service;

import com.expensereimbursement.models.Employee;
import com.expensereimbursement.models.ReimbursementRequest;
import com.expensereimbursement.repository.ReimbursementRepository;
import com.expensereimbursement.repository.ReimbursementRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementRepository reimbursementRepository;

    public ReimbursementService(){
        this.reimbursementRepository = new ReimbursementRepositoryImpl();
    }

    public List<ReimbursementRequest> findAll(){
        return this.reimbursementRepository.findAll();
    }

    public List<ReimbursementRequest> findPending(){
        List<ReimbursementRequest> requests = this.findAll();
        for ( ReimbursementRequest r : requests ){
            if (!r.isResolved()) {
                return requests;
            }
        }
        return null;
    }

    public List<ReimbursementRequest> findResolved(){
        List<ReimbursementRequest> requests = this.findAll();
        for ( ReimbursementRequest r : requests){
            if(r.isResolved()){
                return requests;
            }
        }
        return null;
    }

    public List<ReimbursementRequest> findByEmployee (Employee e){
        List<ReimbursementRequest> requests = this.findAll();
        for ( ReimbursementRequest r : requests){
            if(!r.isResolved() && r.getEmployee().equals(e)){
                return requests;
            }
        }
        return null;
    }

    public List<ReimbursementRequest> findByUser (Employee e){
        return this.reimbursementRepository.findByUser(e);
    }

    public ReimbursementRequest findById(int id){
        return this.reimbursementRepository.findById(id);
    }

    public void insert(ReimbursementRequest r){
        this.reimbursementRepository.insert(r);
    }

    public void delete(ReimbursementRequest r){
        this.reimbursementRepository.delete(r);
    }

    public void update(ReimbursementRequest r){
        this.reimbursementRepository.update(r);
    }

}
