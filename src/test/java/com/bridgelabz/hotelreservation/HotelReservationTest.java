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
		hotelReservation.printWelcomeMessage();
	}

	@Before
	public void shouldPrintWelcomeMessage() {
		hotelReservation.addHotelDetails();
	}

	/* UC2 Cheapest Hotel with weekday */
	@Test
	public void givenDate_shouldReturn_CheapestHotelNameAndRate() throws Exception {
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-10", "2020-09-11");
		Object[] hotelName = hotelNameList.toArray();
		Object[] arrayExpectedOutput = { "LakeWood", "BridgeWood" };
		Assert.assertArrayEquals(arrayExpectedOutput, hotelName);
	}

	/* UC2 Cheapest Hotel with weekday weekend */
	@Test
	public void givenDate_shouldReturn_CheapestHotelNameLakeWoodAndBridgeWood() throws Exception {
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-11", "2020-09-12");
		Object[] hotelName = hotelNameList.toArray();
		Object[] arrayExpectedOutput = { "LakeWood", "BridgeWood" };
		Assert.assertArrayEquals(arrayExpectedOutput, hotelName);
	}

	/* UC2 Cheapest Hotel with weekend days */
	@Test
	public void givenDateWeekend_shouldReturn_CheapestHotelNameBridgeWood() throws Exception {
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-12", "2020-09-13");
		Object[] hotelName = hotelNameList.toArray();
		Object[] arrayExpectedOutput = { "BridgeWood" };
		Assert.assertArrayEquals(arrayExpectedOutput, hotelName);
	}

	/* UC6 Minimum Cost with maximum Ratings on Weekday*/
	@Test
	public void givenDate_WeekDayshouldReturn_CheapestHotelNameLakeWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-09-14", "2020-09-16");
		Assert.assertEquals("LakeWood", hotelName);
	}

	/* UC6 Minimum Cost with maximum Ratings on Weekend days */
	@Test
	public void givenDateWeekend_shouldReturn_CheapestHotelNameBrideWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-09-12", "2020-09-13");
		Assert.assertEquals("BridgeWood", hotelName);
	}

	/* UC6 Minimum Cost with maximum Ratings on weekday adn weekend day*/
	@Test
	public void givenDateWeekDayWeekend_shouldReturn_CheapestHotelNameGivesBridgeWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-09-11", "2020-09-12");
		Assert.assertEquals("BridgeWood", hotelName);
	}


	/* UC7 Cost with maximum Ratings weekday*/
	@Test
	public void givenDate_WeekDayshouldReturn_CheapestHotelNameRidgeWood() throws Exception {
		String hotelName = hotelReservation.calculateCheapestHotelAndRateByRating("2020-09-14", "2020-09-16");
		Assert.assertEquals("RidgeWood", hotelName);
	}

	/* UC7 Cost with maximum Ratings weekend*/
	@Test
	public void givenDateWeekend_shouldReturn_CheapestHotelNameRidgeeWood() throws Exception {
		String hotelName = hotelReservation.calculateCheapestHotelAndRateByRating("2020-09-12", "2020-09-13");
		Assert.assertEquals("RidgeWood", hotelName);
	}

	/* UC7 Cost with maximum Ratings weekday weekend*/
	@Test
	public void givenDateWeekDayWeekend_shouldReturn_CheapestHotelNameGivenBridgeWood() throws Exception {
		String hotelName = hotelReservation.calculateCheapestHotelAndRateByRating("2020-09-11", "2020-09-12");
		Assert.assertEquals("RidgeWood", hotelName);
	}

}
