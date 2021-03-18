package com.expensereimbursement.repository;

import com.expensereimbursement.models.Employee;
import com.expensereimbursement.models.ReimbursementRequest;

import java.util.List;

public interface ReimbursementRepository {

    List<ReimbursementRequest> findAll();

    ReimbursementRequest findById(int id);

    List<ReimbursementRequest> findByStatus();

    List<ReimbursementRequest> findByUser(Employee e);

    void insert(ReimbursementRequest r);

    void delete(ReimbursementRequest r);

    void update(ReimbursementRequest r);

}
