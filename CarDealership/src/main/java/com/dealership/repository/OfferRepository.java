package com.dealership.repository;

import com.dealership.model.Offer;

import java.util.ArrayList;
import java.util.List;

public interface OfferRepository {

    /**
     * Find all Offers for a specified vehicle (Employee)
     */
    List<Offer> findAll();

    List<Offer> findAllByCar( int carId);

    Offer findById(int id);

    /**
     * Submit offer for a specific vehicle (User)
     * @param offer
     */
    void submitOffer(Offer offer);

    /**
     * Remove 'Pending' or 'Rejected' offers exclude 'Accepted' Offers (System)
     * @param
     */
    void removeOffer();

    /**
     * Update 'Pending' to 'Rejected' or 'Accepted' (Employee)
     * @param status
     */
    void updateOfferStatus(int offerId,int status);

    void rejectPendingOffers(int carId);

}
