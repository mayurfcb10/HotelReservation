package com.bridgelabz.hotelreservation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelReservationTest 
{
	static HotelReservation hotelReservation;
	@BeforeClass
	public static void initialize() {
		hotelReservation = new HotelReservation();
	}
	
	@Test
	public void shouldPrintWelcomeMessage()  {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.printWelcomeMessage();
	}
	
	 @Test
     public void givenDate_shouldReturnLakeWood()throws Exception {
         HotelReservation hotelReservation = new HotelReservation();
         hotelReservation.addHotelDetails();
         String cheapestHotel = hotelReservation.calculateCheapestHotelAndRate("10Sep2020", "11Sep2020");
         Assert.assertEquals("Lakewood", cheapestHotel);
     }
}
