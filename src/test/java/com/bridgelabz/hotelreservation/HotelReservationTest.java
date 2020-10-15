package com.bridgelabz.hotelreservation;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelReservationTest {
	static HotelReservation hotelReservation = new HotelReservation();

	@BeforeClass
	public static void initialize() {
		hotelReservation.addHotelDetails();
		hotelReservation.printWelcomeMessage();
	}

	/*  Cheapest Hotel with weekday */
	@Test
	public void givenDate_shouldReturn_CheapestHotelNameAndRate() throws Exception {
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-10", "2020-09-11",false);
		Object[] hotelName = hotelNameList.toArray();
		Object[] arrayExpectedOutput = { "LakeWood", "BridgeWood" };
		Assert.assertArrayEquals(arrayExpectedOutput, hotelName);
	}

	/*  Cheapest Hotel with weekday weekend */
	@Test
	public void givenDate_shouldReturn_CheapestHotelNameLakeWoodAndBridgeWood() throws Exception {
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-11", "2020-09-12",false);
		ArrayList<String> expectedHotelList = new ArrayList<>();
		expectedHotelList.add("LakeWood");
		expectedHotelList.add("BridgeWood");
		Assert.assertEquals(expectedHotelList, hotelNameList);
	}

	/*  Cheapest Hotel with weekend days */
	@Test
	public void givenDateWeekend_shouldReturn_CheapestHotelNameBridgeWood() throws Exception {
		ArrayList<String> hotelNameList = hotelReservation.calculateCheapestHotelAndRate("2020-09-12", "2020-09-13",false);
		Object[] hotelName = hotelNameList.toArray();
		Object[] arrayExpectedOutput = { "BridgeWood" };
		Assert.assertArrayEquals(arrayExpectedOutput, hotelName);
	}

	/*  Minimum Cost with maximum Ratings on Weekday */
	@Test
	public void givenDate_WeekDayshouldReturn_CheapestHotelNameLakeWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-09-14", "2020-09-16", false);
		Assert.assertEquals("LakeWood", hotelName);
	}

	/*  Minimum Cost with maximum Ratings on Weekend days */
	@Test
	public void givenDateWeekend_shouldReturn_CheapestHotelNameBrideWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-09-12", "2020-09-13", false);
		Assert.assertEquals("BridgeWood", hotelName);
	}

	/*  Minimum Cost with maximum Ratings on weekday and weekend day */
	@Test
	public void givenDateWeekDayWeekend_shouldReturn_CheapestHotelNameGivesBridgeWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-09-11", "2020-09-12", false);
		Assert.assertEquals("BridgeWood", hotelName);
	}

	/*  Cost with maximum Ratings weekday */
	@Test
	public void givenDate_WeekDayshouldReturn_CheapestHotelNameRidgeWood() throws Exception {
		String hotelName = hotelReservation.calculateCheapestHotelAndRateByRating("2020-09-14", "2020-09-16",false);
		Assert.assertEquals("RidgeWood", hotelName);
	}

	/* Cost with maximum Ratings weekend */
	@Test
	public void givenDateWeekend_shouldReturn_CheapestHotelNameRidgeeWood() throws Exception {
		String hotelName = hotelReservation.calculateCheapestHotelAndRateByRating("2020-09-12", "2020-09-13",false);
		Assert.assertEquals("RidgeWood", hotelName);
	}

	/* Cost with maximum Ratings weekday weekend */
	@Test
	public void givenDateWeekDayWeekend_shouldReturn_CheapestHotelNameGivenBridgeWood() throws Exception {
		String hotelName = hotelReservation.calculateCheapestHotelAndRateByRating("2020-09-11", "2020-09-12",false);
		Assert.assertEquals("RidgeWood", hotelName);
	}
	
	@Test
	public void givenDateWeekDayWeekendRewardCustomer_shouldReturn_CheapestHotelNameRidgeWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-09-11", "2020-09-12", true);
		Assert.assertEquals("RidgeWood", hotelName);
	}
	
	@Test
	public void givenDateWeekDayRewardCustomer_shouldReturn_CheapestHotelNameidgeWood() throws Exception {
		String hotelName = hotelReservation.findCheapestCostWithMaxRatings("2020-10-14", "2020-10-16", true);
		Assert.assertEquals("RidgeWood", hotelName);
	}

}
