package com.dealership.repository;

import com.dealership.model.Payment;
import com.dealership.utility.ConnectionClosures;
import com.dealership.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository{

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from payment";
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            set = stmt.executeQuery();

            while(set.next()){
                payments.add( new Payment(set.getInt(1),
                        set.getInt(2),
                        set.getInt(3),
                        set.getDouble(4)
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }
        return payments;

    }

    @Override
    public List<Payment> findAllForCar(int carId) {
        List<Payment> payments = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from payment where payment_car_id = ?";
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            set = stmt.executeQuery();

            while(set.next()){
                payments.add( new Payment(set.getInt(1),
                        set.getInt(2),
                        set.getInt(3),
                        set.getDouble(4)
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }
        return payments;
    }


    @Override
    public void submitPayment(int userId, int carId, double amount) {
        Connection conn = null;
        PreparedStatement stmt = null;
        final String SQL = "insert into payment values(default, ?, ?, ?)";
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,userId);
            stmt.setInt(2,carId);
            stmt.setDouble(3,amount);
            stmt.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
        }
    }

    @Override
    public double findPrice(int carId) {

        double price = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select owen_car_price from owned_cars where owned_car_id = ?";
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,carId);
            set = stmt.executeQuery();

            while(set.next()) {

                price = set.getDouble(1);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }
        return price;
    }

    @Override
    public int sumOfPayments(int carId, int userId) {
        int sum = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select count(*) from payment where payment_car_id = ? and payment_user_id = ? ";

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,carId);
            stmt.setInt(2,userId);
            set = stmt.executeQuery();

            while ( set.next()) {
                sum = set.getInt(1);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }
        return sum;
    }

}
