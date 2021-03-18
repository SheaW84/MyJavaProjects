package com.dealership.repository;

import com.dealership.model.Car;
import com.dealership.utility.ConnectionClosures;
import com.dealership.utility.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImp implements CarRepository{

    private static final Logger LOG = LogManager.getFormatterLogger(CarRepositoryImp.class);

    /**
     * View all the cars on the lot
     * @return
     */

    @Override
    public List<Car> findAllOnLot() {

        List<Car> carLot = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from car";

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            while(set.next()) {

                carLot.add(
                        new Car(set.getInt(1),
                                set.getInt(2),
                                set.getString(3),
                                set.getString(4),
                                set.getString(5),
                                set.getDouble(6),
                                set.getInt(7))
                );
            }
        }catch (SQLException e) {
           LOG.info(e);
        }catch (NullPointerException e){
            LOG.info(e);
        } finally {
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }

        return carLot;
    }
    /**
     * Find all owned cars by the owner Id
     */

    @Override
    public List<Car> findAllOwned(int userId) {
        List<Car> carsList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from owned_cars where owner_id = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,userId);
            set = stmt.executeQuery();

            while (set.next()){
                carsList.add(new Car(set.getInt(1),
                        set.getInt(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getDouble(6),
                        set.getInt(7),
                        set.getInt(8)
                ));
            }
        }catch(SQLException e){
        	LOG.info(e);
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }
        return carsList;
    }

    /**
     * Find car by Id
     * @param car_id
     * @return
     */

    @Override
    public Car findById(int car_id) {
        Car car = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from car where car_id = ?";


        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,car_id);
            set = stmt.executeQuery();

            while (set.next()){
                car = new Car(set.getInt(1),
                        set.getInt(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getDouble(6),
                        set.getInt(7)
                );
            }

        }catch(SQLException e){
            LOG.info(e);
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }
        return car;
    }

    /**
     * Update car status
     * @param car_id
     * @param status
     */

    @Override
    public void updateCarStatus(int car_id, int status) {

        Connection conn = null;
        PreparedStatement stmt = null;
        final String SQL = "update car set car_status_n = ? where car_id = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,status);
            stmt.setInt(2,car_id);
            stmt.execute();

        }catch(SQLException e){
            LOG.info(e);
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);

        }

    }

    /**
     * Add car to the lot
     * @param car
     */

    @Override
    public void addToLot(Car car) {

             Connection conn = null;
             PreparedStatement stmt = null;

             final String SQL = "insert into car values(default,?,?,?,?,?,1)";

             try{
                 conn = ConnectionFactory.getConnection();
                 stmt = conn.prepareStatement(SQL);


                 stmt.setInt(1,car.getYear());
                 stmt.setString(2,car.getMake());
                 stmt.setString(3,car.getModel());
                 stmt.setString(4,car.getColor());
                 stmt.setDouble(5,car.getPrice());
                 stmt.execute();

             }catch (SQLException e){
                 LOG.info(e);
             }finally{
                 ConnectionClosures.close(conn);
                 ConnectionClosures.close(stmt);
             }

    }

    /**
     * Remove a car from the lot
     * @param id
     */

    @Override
    public void removeCarFromLot(int id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        final String SQL = "delete from car where car_id = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);

            stmt.setInt(1, id);
            stmt.execute();

        }catch (SQLException e){
            LOG.info(e);
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);

        }
    }

    @Override
    public void updateCarPrice(double price) {

    }

    /**
     * Inserting owned cars
     * @param car
     * @param userid
     */

    @Override
    public void insertOwnedCar(Car car, int userid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        final String SQL = "insert into owned_cars values(default,"+ car.getYear() +",'"+
                car.getMake()+"','"+
                car.getModel()+"','"+
                car.getColor()+"',"+
                car.getPrice()+",?,"+
                car.getStatus()+')';
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1,userid);
            stmt.execute();

        }catch(SQLException e){
            LOG.info(e);
        } finally {
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
        }

    }
}
