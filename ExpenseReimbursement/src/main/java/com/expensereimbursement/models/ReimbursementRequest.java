package com.expensereimbursement.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "reimbursement_request")
public class ReimbursementRequest {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(generator = "request_id", strategy = GenerationType.IDENTITY)
    @SequenceGenerator( allocationSize = 1, name ="request_id_seq" , sequenceName = "request_id_seq")
    private int id;
    @Column(name = "request_reason")
    private String reason;
    @Column(name = "request_amount")
    private double amount;
    @Column(name = "is_resolved")
    private boolean isResolved;
    @JoinColumn(name = "employee_request_id")
    @ManyToOne
    private Employee employee;

    public ReimbursementRequest(){
        super();
    }

    public ReimbursementRequest(String reason, double amount, boolean isResolved, Employee employee) {
        this.reason = reason;
        this.amount = amount;
        this.isResolved = isResolved;
        this.employee = employee;
    }

    public ReimbursementRequest(int id, String reason, double amount, boolean isResolved, Employee employee) {
        this.id = id;
        this.reason = reason;
        this.amount = amount;
        this.isResolved = isResolved;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployeeId(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementRequest that = (ReimbursementRequest) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && isResolved == that.isResolved && employee == that.employee && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reason, amount, isResolved, employee);
    }

    @Override
    public String toString() {
        return "ReimbursementRequest{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", amount=" + amount +
                ", isResolved=" + isResolved +
                ", employeeId=" + employee +
                '}';
    }
}
