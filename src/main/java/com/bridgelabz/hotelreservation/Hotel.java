package com.bridgelabz.hotelreservation;

public class Hotel {

	// Parameters
	private String hotelName;
	private int regularRate;

	//Parameterized Constructor
	public Hotel(String hotelName, int regularRate) {
		this.hotelName = hotelName;
		this.regularRate = regularRate;
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
}	