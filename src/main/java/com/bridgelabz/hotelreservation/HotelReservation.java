package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HotelReservation {
	ArrayList<Hotel> hotelList;
	long minCostLakeWood;
	long minCostBridgeWood;
	long minCostRidgeWood;
	long totalDays;
	long weekDays;
	long weekendDays;
	int rating;

	public HotelReservation() {
		hotelList = new ArrayList<>();
	}

	public void printWelcomeMessage() {
		System.out.println("Welcome to the Hotel Reservation Program");
	}

	public void addHotelDetails() {
		hotelList.add(new Hotel("LakeWood", 3, 110, 90, 80, 80));
		hotelList.add(new Hotel("BridgeWood", 4, 150, 50, 110, 50));
		hotelList.add(new Hotel("RidgeWood", 5, 220, 150, 100, 40));
	}

	/* count week days */
	private static long countWeekDaysMath(LocalDate arrivalDay, LocalDate departureDay) {
		long count = 0;
		final DayOfWeek startDay = arrivalDay.getDayOfWeek();
		final DayOfWeek endDay = departureDay.getDayOfWeek();

		final long days = ChronoUnit.DAYS.between(arrivalDay, departureDay);
		final long daysWithoutWeekends = days - 2 * ((days + startDay.getValue()) / 7);

		// adjust for starting and ending on a Sunday:
		count = daysWithoutWeekends + (startDay == DayOfWeek.SUNDAY ? 1 : 0) + (endDay == DayOfWeek.SUNDAY ? 1 : 0);
		return count;
	}

	/* Find hotel by maximum ratings */
	public String calculateCheapestHotelAndRateByRating(String dateOfArrival, String dateOfDeparture) {
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		weekendDays = totalDays - weekDays;
		long cost = 0;
		rating = 0;
		String hotelName = null;
		minCostLakeWood = (hotelList.get(0).getRegularRate() * weekDays
				+ hotelList.get(0).getWeekendRate() * weekendDays);
		minCostBridgeWood = (hotelList.get(1).getRegularRate() * weekDays
				+ hotelList.get(1).getWeekendRate() * weekendDays);
		minCostRidgeWood = (hotelList.get(2).getRegularRate() * weekDays
				+ hotelList.get(2).getWeekendRate() * weekendDays);
		//long minCostHotel = Math.min(minCostRidgeWood, Math.min(minCostBridgeWood, minCostLakeWood));
		if ((hotelList.get(2).getHotelRating() > rating)) {
			hotelName = hotelList.get(2).getHotelName();
			cost = minCostRidgeWood;
			rating = hotelList.get(2).getHotelRating();
		}
		if ((hotelList.get(1).getHotelRating() > rating)) {
			hotelName = hotelList.get(1).getHotelName();
			cost = minCostBridgeWood;
			rating = hotelList.get(1).getHotelRating();
		}
		if ((hotelList.get(0).getHotelRating() > rating)) {
			hotelName = hotelList.get(0).getHotelName();
			cost = minCostLakeWood;
			rating = hotelList.get(0).getHotelRating();
		}
		System.out.println(
				"The minimum cost for Hotel " + hotelName + " is " + cost + " and rating of hotel is " + rating);
		return hotelName;
	}

	/* Find cheapest hotel */
	public ArrayList<String> calculateCheapestHotelAndRate(String dateOfArrival, String dateOfDeparture) {
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		weekendDays = totalDays - weekDays;
		ArrayList<String> hotel = new ArrayList<>();
		long minCostLakeWood = (hotelList.get(0).getRegularRate() * weekDays
				+ hotelList.get(0).getWeekendRate() * weekendDays);
		long minCostBridgeWood = (hotelList.get(1).getRegularRate() * weekDays
				+ hotelList.get(1).getWeekendRate() * weekendDays);
		long minCostRidgeWood = (hotelList.get(2).getRegularRate() * weekDays
				+ hotelList.get(2).getWeekendRate() * weekendDays);
		long minCostHotel = Math.min(minCostRidgeWood, Math.min(minCostBridgeWood, minCostLakeWood));
		if (minCostHotel == minCostLakeWood)
			hotel.add("LakeWood");
		if (minCostHotel == minCostBridgeWood)
			hotel.add("BridgeWood");
		if (minCostHotel == minCostRidgeWood)
			hotel.add("RidgeWood");
		System.out.println("The minimum cost for Hotel is " + minCostHotel);
		return hotel;
	}

	/* UC6 Find cheapest hotel with maximum rating */

	public String findCheapestCostWithMaxRatings(String dateOfArrival, String dateOfDeparture) {
		int rating = 0;
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		weekendDays = totalDays - weekDays;
		String hotelName = null;
		minCostLakeWood = (hotelList.get(0).getRegularRate() * weekDays
				+ hotelList.get(0).getWeekendRate() * weekendDays);
		minCostBridgeWood = (hotelList.get(1).getRegularRate() * weekDays
				+ hotelList.get(1).getWeekendRate() * weekendDays);
		minCostRidgeWood = (hotelList.get(2).getRegularRate() * weekDays
				+ hotelList.get(2).getWeekendRate() * weekendDays);
		long minCostHotel = Math.min(minCostRidgeWood, Math.min(minCostBridgeWood, minCostLakeWood));
		if (minCostHotel == minCostRidgeWood && (hotelList.get(2).getHotelRating() > rating)) {
			rating = hotelList.get(2).getHotelRating();
			hotelName = hotelList.get(2).getHotelName();
		}
		if (minCostHotel == minCostBridgeWood && (hotelList.get(1).getHotelRating() > rating)) {
			rating = hotelList.get(1).getHotelRating();
			hotelName = hotelList.get(1).getHotelName();
		}
		if (minCostHotel == minCostLakeWood && (hotelList.get(0).getHotelRating() > rating)) {
			rating = hotelList.get(0).getHotelRating();
			hotelName = hotelList.get(0).getHotelName();
		}
		System.out.println("The minimum cost for Hotel " + hotelName + " is " + minCostHotel
				+ " and rating of hotel is " + rating);
		return hotelName;
	}

}
