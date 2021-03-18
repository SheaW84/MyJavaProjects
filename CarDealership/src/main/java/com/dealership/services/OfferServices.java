package com.dealership.services;

import com.dealership.model.Offer;
import com.dealership.model.OfferStatus;
import com.dealership.repository.OfferRepository;
import com.dealership.repository.OfferRepositoryImpl;

import java.util.List;

public class OfferServices {

   private OfferRepository offerRepository;

   public OfferServices(){
       this.offerRepository = new OfferRepositoryImpl();
   }
   public List<Offer> findAll(){
       return this.offerRepository.findAll();
   }

    public Offer findById( int offer_id){
       return this.offerRepository.findById(offer_id);
    }

   public void submitOffer(Offer offer){
       if(offer != null)
       this.offerRepository.submitOffer(offer);
   }

   public void removeOffer(){
       this.offerRepository.removeOffer();
   }

   public void updateOfferStatus(int offerId, int status){
       this.offerRepository.updateOfferStatus(offerId, status);
   }

   public void rejectPendingOffers(int carId){
       this.offerRepository.rejectPendingOffers(carId);
   }

}
