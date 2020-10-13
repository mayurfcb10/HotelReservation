package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HotelReservation {

	ArrayList<Hotel> hotelList = new ArrayList<>();

	public void printWelcomeMessage() {
		System.out.println("Welcome to the Hotel Reservation Program");
	}

	public void addHotelDetails() {
		hotelList.add(new Hotel("Lakewood", 110, 90));
		hotelList.add(new Hotel("Bridgewood", 150, 50));
		hotelList.add(new Hotel("Ridgewood", 220, 150));
	}

	private static long countWeekDaysMath(LocalDate start, LocalDate stop) {
		long count = 0;
		final DayOfWeek startW = start.getDayOfWeek();
		final DayOfWeek stopW = stop.getDayOfWeek();

		final long days = ChronoUnit.DAYS.between(start, stop);
		final long daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);

		// adjust for starting and ending on a Sunday:
		count = daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (stopW == DayOfWeek.SUNDAY ? 1 : 0);
		return count;
	}

	/* Find cheapest hotel */
	public ArrayList<String> findCheapestCost(long weekDays, long weekendDays) {
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
		System.out.println("The minimum cost for Hotel is "+ minCostHotel);
		return hotel;
	}

	// Calculated rate based on period of stay at hotel
	public ArrayList<String> calculateCheapestHotelAndRate(String dateOfArrival, String dateOfDeparture) throws Exception {
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		long totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		long weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		long weekendDays = totalDays - weekDays;
		return findCheapestCost(weekDays, weekendDays);
	}

}

