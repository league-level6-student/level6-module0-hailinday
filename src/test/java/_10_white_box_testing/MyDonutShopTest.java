package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import _08_mocking.models.DeliveryDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    
    @Mock
    DeliveryService deliveryService;
    
    @Mock
    BakeryService bakeryService;
    
    @Mock
    PaymentService paymentService;
    
    @BeforeEach
    void setUp() {
    	 MockitoAnnotations.openMocks(this);
    	 
         myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
    	myDonutShop.openForTheDay();
        when(bakeryService.getDonutsRemaining()).thenReturn(order.getNumberOfDonuts()+1);

        //when
    	myDonutShop.takeOrder(order);
        //then
    	verify(deliveryService, times(1)).scheduleDelivery(order);;
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given

        //when

        //then
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given

        //when

        //then
    }

}