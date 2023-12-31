package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    
    @Mock
    CellPhone cell;
    
    @Mock
    Car car;
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	deliveryDriver = new DeliveryDriver("John", car, cell);
    	
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expectedWaste = true;
        //when
    	when(cell.browseCatMemes()).thenReturn(true);
        //then
    	boolean actualWaste = deliveryDriver.wasteTime();
    	assertEquals(expectedWaste, actualWaste);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expectedRefuel = true;
    	int octane = 80;
        //when
    	when(car.fillTank(octane)).thenReturn(true);
        //then
    	boolean actualRefuel = deliveryDriver.refuel(octane);
    	assertEquals(expectedRefuel, actualRefuel);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expectedContact = true;
    	String phoneNum = "858-723-8551";
        //when
    	when(cell.call(phoneNum)).thenReturn(true);
        //then
    	boolean actualContact = deliveryDriver.contactCustomer(phoneNum);
    	assertEquals(expectedContact,actualContact);
    }

}