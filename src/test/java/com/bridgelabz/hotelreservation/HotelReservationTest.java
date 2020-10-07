package com.bridgelabz.hotelreservation;

import org.junit.Test;

public class HotelReservationTest 
{
	@Test
	public void shouldPrintWelcomeMessage()  {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.printWelcomeMessage();
	}
}
