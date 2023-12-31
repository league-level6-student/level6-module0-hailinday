package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	int hourlyWage = 20;
    	int numHours = 2;
    	int expected = 40;
        //when
    	double actual = payroll.calculatePaycheck(hourlyWage, numHours);
        //then
    	assertEquals(expected,actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	double centsPerMile = .575;
    	int milesTraveled = 2;
    	double expected = 2*(0.575);
        //when
    	double actual = payroll.calculateMileageReimbursement(milesTraveled);
        //then
    	assertEquals(expected,actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String employeeName = "John";
    	double hourlyWage = 15;
    	String expected = "Hello John, We are pleased to offer you an hourly wage of 15.0";
        //when
    	String actual = payroll.createOfferLetter(employeeName, hourlyWage);
        //then
    	assertEquals(expected,actual);
    }

}