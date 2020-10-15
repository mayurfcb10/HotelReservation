package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	 /*Date Validation*/
    private static boolean validateDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
	
	/* Method to validate and take the user input*/
    public static void main(String[] args) throws Exception {
        HotelReservation reservation = new HotelReservation();
        Scanner sc = new Scanner(System.in);;
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
            reservation.findCheapestCostWithMaxRatings(arrivalDate, checkoutDate,false);
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
	public String calculateCheapestHotelAndRateByRating(String dateOfArrival, String dateOfDeparture) {
		long cost = 0;
		rating = 0;
		String hotelName = null;
		ArrayList<String> hotel = new ArrayList<>();
		ArrayList<Integer> eachHotelRate = calculateTotalCost(dateOfArrival, dateOfDeparture);
		long minCostHotel = Math.min(eachHotelRate.get(0), Math.min(eachHotelRate.get(1), eachHotelRate.get(2)));
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
	public ArrayList<String> calculateCheapestHotelAndRate(String dateOfArrival, String dateOfDeparture) {
		ArrayList<String> hotel = new ArrayList<>();
		String hotelName = null;
		ArrayList<Integer> eachHotelRate = calculateTotalCost(dateOfArrival, dateOfDeparture);
		long minCostHotel = Math.min(eachHotelRate.get(0), Math.min(eachHotelRate.get(1), eachHotelRate.get(2)));
		if (minCostHotel == eachHotelRate.get(0))
			hotel.add("LakeWood");
		if (minCostHotel == eachHotelRate.get(1))
			hotel.add("BridgeWood");
		if (minCostHotel == eachHotelRate.get(2))
			hotel.add("RidgeWood");
		System.out.println("The minimum cost for Hotel is " + minCostHotel);
		return hotel;
	}

	/* Calculate Total Cost of Each Hotel based on given date */
	public ArrayList<Integer> calculateTotalCost(String dateOfArrival, String dateOfDeparture) {
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		weekendDays = totalDays - weekDays;
		ArrayList<Integer> eachHotelRate = new ArrayList<>();
		for (Hotel hotel : hotelList) {
			eachHotelRate.add((int) (hotel.getRegularRate() * weekDays + hotel.getWeekendRate() * weekendDays));
		}
		return eachHotelRate;

	}

	public ArrayList<Integer> calculateTotalCostRewardCustomer(String dateOfArrival, String dateOfDeparture) {
		LocalDate dateArrival = LocalDate.parse(dateOfArrival);
		LocalDate dateDeparture = LocalDate.parse(dateOfDeparture);
		totalDays = ChronoUnit.DAYS.between(dateArrival, dateDeparture) + 1;
		weekDays = countWeekDaysMath(dateArrival, dateDeparture);
		weekendDays = totalDays - weekDays;
		ArrayList<Integer> eachHotelRateRewardCustomer = new ArrayList<>();
		for (Hotel hotel : hotelList) {
			eachHotelRateRewardCustomer.add((int) ((int) (hotel.getRewardWeekDayRate()) * weekDays
					+ hotel.getRewardWeekendRate() * weekendDays));
		}
		return eachHotelRateRewardCustomer;

	}

	/* UC6 Find cheapest hotel with maximum rating */
	public String findCheapestCostWithMaxRatings(String dateOfArrival, String dateOfDeparture,
			boolean isRewardCustomer) {
		String hotelName = null;
		if (isRewardCustomer == false) {
			int rating = 0;
			ArrayList<Integer> eachHotelRate = calculateTotalCost(dateOfArrival, dateOfDeparture);
			long minCostHotel = Math.min(eachHotelRate.get(0), Math.min(eachHotelRate.get(1), eachHotelRate.get(2)));
			for (int i = 0; i < hotelList.size(); i++) {
				if (minCostHotel == eachHotelRate.get(i) && (hotelList.get(i).getHotelRating() > rating)) {
					rating = hotelList.get(i).getHotelRating();
					hotelName = hotelList.get(i).getHotelName();
				}
			}
			System.out.println("The minimum cost for Hotel " + hotelName + " is " + minCostHotel
					+ " and rating of hotel is " + rating);
		} else {
			int rating = 0;
			ArrayList<Integer> eachHotelRateRewardCustomer = calculateTotalCostRewardCustomer(dateOfArrival, dateOfDeparture);
			long minCostHotel = Math.min(eachHotelRateRewardCustomer.get(0),
					Math.min(eachHotelRateRewardCustomer.get(1), eachHotelRateRewardCustomer.get(2)));
			for (int i = 0; i < hotelList.size(); i++) {
				if (minCostHotel == eachHotelRateRewardCustomer.get(i)
						&& (hotelList.get(i).getHotelRating() > rating)) {
					rating = hotelList.get(i).getHotelRating();
					hotelName = hotelList.get(i).getHotelName();
				}
			}
			System.out.println("The minimum cost for Hotel " + hotelName + "for Reward Customer is " + minCostHotel
					+ " and rating of hotel is " + rating);
		}
		return hotelName;
	}
}