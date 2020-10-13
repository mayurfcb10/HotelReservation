package com.bridgelabz.hotelreservation;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelReservationTest {
	static HotelReservation hotelReservation;

	@BeforeClass
	public static void initialize() {
		hotelReservation = new HotelReservation();
	}

	@Before
	public void shouldPrintWelcomeMessage() {
		hotelReservation.printWelcomeMessage();
	}

	@Test
	public void givenDate_shouldReturn_CheapestHotelNameAndRate() throws Exception {
		hotelReservation.addHotelDetails();
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-11", "2020-09-12");
		Object[] hotelName = hotelNameList.toArray();
		Object[] arrayExpectedOutput = { "LakeWood", "BridgeWood" };
		Assert.assertArrayEquals(arrayExpectedOutput, hotelName);
	}
	
	@Test
	public void givenDateWeekend_shouldReturn_CheapestHotelNameAndRate() throws Exception {
		hotelReservation.addHotelDetails();
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-12", "2020-09-13");
		Object[] hotelName = hotelNameList.toArray();
		Object[] arrayExpectedOutput = {"BridgeWood"};
		Assert.assertArrayEquals(arrayExpectedOutput, hotelName);
	}
}
