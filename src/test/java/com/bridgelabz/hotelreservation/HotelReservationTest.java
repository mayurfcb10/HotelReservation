package com.bridgelabz.hotelreservation;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class HotelReservationTest 
{
	@Test
	public void shouldPrintWelcomeMessage()  {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.printWelcomeMessage();
	}
}
