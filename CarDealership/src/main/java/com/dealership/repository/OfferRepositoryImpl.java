package com.dealership.repository;

import com.dealership.model.Offer;
import com.dealership.utility.ConnectionClosures;
import com.dealership.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferRepositoryImpl implements OfferRepository{

    @Override
    public List<Offer> findAll() {

        List<Offer> offers = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;
        final String SQL =  "select * from offer";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            while (set.next()){
                offers.add( new Offer(set.getInt(1),
                        set.getDouble(2),
                        set.getInt(3),
                        set.getInt(4),
                        set.getInt(5))
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }

        return offers;
    }

    @Override
    public List<Offer> findAllByCar(int carId) {

        List<Offer> offers = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from offer where offer_car_id = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, carId);
            set = stmt.executeQuery();

            while(set.next()){
                offers.add(new Offer(set.getInt(1),
                        set.getDouble(2),
                        set.getInt(3),
                        set.getInt(4),
                        set.getInt(5))
                );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }

        return offers;
    }

    @Override
    public Offer findById( int offer_id) {
        Offer offer = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from offer where offer_id = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,offer_id);
            set = stmt.executeQuery();

            while(set.next()){
                offer = new Offer(set.getInt(1),
                        set.getDouble(2),
                        set.getInt(3),
                        set.getInt(4),
                        set.getInt(5)
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }

    return offer;
    }


    @Override
    public void submitOffer(Offer offer) {

        Connection conn = null;
        PreparedStatement stmt =  null;
        final String SQL = "insert into offer values(default,?, ?, ?, 1)";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);

            stmt.setDouble(1,offer.getAmount());
            stmt.setInt(2,offer.getUserId());
            stmt.setInt(3,offer.getCarId());
            stmt.execute();


        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
        }

    }

    @Override
    public void removeOffer() {
        Connection conn = null;
        Statement stmt= null;
        final String SQL = "delete from offer where offer_status_n != 2";
        try{
            conn= ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            stmt.execute(SQL);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);

        }

    }

    @Override
    public void updateOfferStatus(int offerId, int status) {

        Connection conn = null;
        PreparedStatement stmt = null;
        final String SQL = "update offer set offer_status_n = ? where offer_id = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);

            stmt.setInt(1, status);
            stmt.setInt(2, offerId);
            stmt.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
        }

    }

    @Override
    public void rejectPendingOffers(int carId) {

        Connection conn = null;
        PreparedStatement stmt =null;
        final String SQL = "UPDATE offer SET offer_status_n  = 3 WHERE offer_status_n  = 1 AND offer_car_id = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, carId);
            stmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
        }

    }

}
