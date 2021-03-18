package com.dealership.services;

import com.dealership.model.Payment;
import com.dealership.repository.PaymentRepositoryImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PaymentServicesTest {

    @InjectMocks
    private static PaymentServices paymentServices;


    @Mock
    private PaymentRepositoryImpl paymentRepositoryImpl;

    @BeforeClass
    public static void setup(){
        paymentServices = new PaymentServices();
    }

    @Before
    public void beforeSetup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllForCar() {
        Mockito.when(paymentRepositoryImpl.findAllForCar(1)).thenReturn(
                Arrays.asList(new Payment( 1, 1,1,500))
        );

    }

    @Test
    public void submitPayment() {
    }

    @Test
    public void testCalculatePayment() {
        double price = 6000;
       assertEquals(100, paymentServices.calculatePayment(price),.001);
    }
}