package com.bridgelabz.hotelreservation;

public class Hotel {

	// Parameters
	private String hotelName;
	private int hotelRating;
	private int regularRate;
	private int weekendRate;

	// Parameterized Constructor
	public Hotel(String hotelName, int hotelRating, int regularRate, int weekendRate) {
		this.hotelName = hotelName;
		this.hotelRating = hotelRating;
		this.regularRate = regularRate;
		this.weekendRate = weekendRate;
	}

	// Getter And Setter Methods
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getRegularRate() {
		return regularRate;
	}

	public void setRegularRate(int regularRate) {
		this.regularRate = regularRate;
	}

	public int getWeekendRate() {
		return weekendRate;
	}

	public void setWeekendRate(int weekendRate) {
		this.weekendRate = weekendRate;
	}

	public int getHotelRating() {
		return hotelRating;
	}

	public void setHotelRating(int hotelRating) {
		this.hotelRating = hotelRating;
	}
}