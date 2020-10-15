package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	/* Date Validation */
	private static boolean validateDate(String date) {
		try {
			LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}

	/* Method to validate and take the user input */
	public static void main(String[] args) throws Exception {
		HotelReservation reservation = new HotelReservation();
		Scanner sc = new Scanner(System.in);
		;
		System.out.println("Welcome to Hotel Reservation System");
		while (true) {
			System.out.println("Enter the Hotel Name for Adding rates: ");
			String hotelName = sc.next();
			System.out.println("Enter Hotel Rating: ");
			int rating = sc.nextInt();
			System.out.println("Enter the Weekday rate: ");
			int weeklyRate = sc.nextInt();
			System.out.println("Enter the Weekend rate");
			int weekEndRate = sc.nextInt();
			System.out.println("Enter the Weekday rate for reward customer");
			int weeklyRateReward = sc.nextInt();
			System.out.println("Enter the Weekend rate for reward customer");
			int weekEndRateReward = sc.nextInt();
			reservation.addHotelDetails();
			System.out.println("Add more hotel- (Yes/No)");
			String choice = sc.next();
			if (choice.equalsIgnoreCase("Yes"))
				continue;
			else
				break;
		}
		System.out.println("Enter Arrival Date(DD/MM/YYYY):- ");
		String arrivalDate = sc.next();
		System.out.println("Enter Checkout Date(DD/MM/YYYY):- ");
		String checkoutDate = sc.next();
		if (!(validateDate(arrivalDate) && validateDate(checkoutDate)))
			throw new Exception("Invalid date");
		System.out.println("Enter the type of customer (Regular/Reward)");
		String customer = sc.next();
		if (customer.equalsIgnoreCase("Regular"))
			reservation.findCheapestCostWithMaxRatings(arrivalDate, checkoutDate, false);
		else if (customer.equalsIgnoreCase("Reward"))
			reservation.findCheapestCostWithMaxRatings(arrivalDate, checkoutDate, true);
		else
			throw new Exception("Wrong Customer type");
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
	public String calculateCheapestHotelAndRateByRating(String dateOfArrival, String dateOfDeparture,
			boolean isRewardee) {
		long cost = 0;
		rating = 0;
		String hotelName = null;
		ArrayList<Integer> eachHotelRate = calculateTotalCost(dateOfArrival, dateOfDeparture, isRewardee);
		for (int i = 0; i < hotelList.size(); i++) {
			if ((hotelList.get(i).getHotelRating() > rating)) {
				hotelName = hotelList.get(i).getHotelName();
				cost = eachHotelRate.get(i);
				rating = hotelList.get(i).getHotelRating();
			}
		}
		System.out.println(
				"The minimum cost for Hotel " + hotelName + " is " + cost + " and rating of hotel is " + rating);
		return hotelName;
	}

	/* Find cheapest hotel */
	public ArrayList<String> calculateCheapestHotelAndRate(String dateOfArrival, String dateOfDeparture,
			boolean isRewardee) {
		int minCostHotel = hotelList.stream()
				.map(hotel -> calculateTotalCost(hotel, dateOfArrival, dateOfDeparture, isRewardee))
				.min((x, y) -> x - y).get();
		List<String> cheapestHotelList = hotelList.stream()
				.filter(hotel -> minCostHotel == calculateTotalCost(hotel, dateOfArrival, dateOfDeparture, isRewardee))
				.map(hotel -> hotel.getHotelName()).collect(Collectors.toList());
		System.out.println("The minimum cost for Hotel is " + minCostHotel);
		return (ArrayList<String>) cheapestHotelList;
	}

	/* Calculate Total Cost of Each Hotel based on given date */
	public ArrayList<Integer> calculateTotalCost(String dateOfArrival, String dateOfDeparture, boolean isRewardee) {
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		weekendDays = totalDays - weekDays;
		ArrayList<Integer> eachHotelRate = new ArrayList<>();
		for (Hotel hotel : hotelList) {
			if (!isRewardee)
				eachHotelRate.add((int) (hotel.getRegularRate() * weekDays + hotel.getWeekendRate() * weekendDays));
			else
				eachHotelRate.add((int) ((int) (hotel.getRewardWeekDayRate()) * weekDays
						+ hotel.getRewardWeekendRate() * weekendDays));
		}
		return eachHotelRate;

	}

	/* Calculate Total Cost of Each Hotel based on given date */
	public int calculateTotalCost(Hotel hotelName, String dateOfArrival, String dateOfDeparture, boolean isRewardee) {
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		weekendDays = totalDays - weekDays;
		if (isRewardee)
			return (int) (hotelName.getRewardWeekDayRate() * weekDays + hotelName.getRewardWeekendRate() * weekendDays);
		return (int) (hotelName.getRegularRate() * weekDays + hotelName.getWeekendRate() * weekendDays);
	}

	/*  Find cheapest hotel with maximum rating */
	public String findCheapestCostWithMaxRatings(String dateOfArrival, String dateOfDeparture,
			boolean isRewardCustomer) {
		int minCostHotel = hotelList.stream()
				.map(hotel -> calculateTotalCost(hotel, dateOfArrival, dateOfDeparture, isRewardCustomer))
				.min((x, y) -> x - y).get();
		List<Hotel> cheapestHotelList = hotelList.stream().filter(
				hotel -> minCostHotel == calculateTotalCost(hotel, dateOfArrival, dateOfDeparture, isRewardCustomer))
				.collect(Collectors.toList());
		Hotel h = cheapestHotelList.stream().max((x, y) -> x.getHotelRating() - y.getHotelRating()).get();
		String cheapestHotelName = h.getHotelName();
		System.out.println("The minimum cost for Hotel " + cheapestHotelName + " is " + minCostHotel
				+ " and rating of hotel is " + h.getHotelRating());
		return cheapestHotelName;
	}

}
