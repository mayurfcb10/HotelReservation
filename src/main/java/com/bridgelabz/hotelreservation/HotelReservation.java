package com.bridgelabz.hotelreservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class HotelReservation {

	ArrayList<Hotel> hotelList = new ArrayList<>();
	public void printWelcomeMessage() {
		System.out.println("Welcome to the Hotel Reservation Program");
	}

	public void addHotelDetails() {
		hotelList.add(new Hotel("Lakewood", 110));
		hotelList.add(new Hotel("Bridgewood", 150));
		hotelList.add(new Hotel("Ridgewood", 220));
	}

	public String calculateCheapestHotelAndRate(String dateOfArrival, String dateOfDeparture) throws Exception {

		Date dateofArrival = convertStringToDate(dateOfArrival);
		Date dateofDeparture = convertStringToDate(dateOfDeparture);
		long totalPeriodOfStay = 0;
		totalPeriodOfStay = (dateofDeparture.getTime() - dateofArrival.getTime());


		int totalDays = (int) TimeUnit.DAYS.convert(totalPeriodOfStay,TimeUnit.MILLISECONDS);
		addHotelDetails();

		 for (int hotel = 0; hotel < hotelList.size(); hotel++) {
	            int totalRate = hotelList.get(hotel).getRegularRate() * (totalDays+1);
	            hotelList.get(hotel).setRegularRate(totalRate);
	        }

		int regularHotelRate = hotelList.stream().min(Comparator.comparing(Hotel::getRegularRate)).get().getRegularRate();
		String hotelName = hotelList.stream().min(Comparator.comparing(Hotel::getRegularRate)).get().getHotelName();

		System.out.println("The Cheapest Hotel is "+hotelName+" with cost for respective date as "+regularHotelRate+"$");
		return hotelName;

	}

	public Date convertStringToDate(String date) throws Exception{
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("ddMMMyyyy").parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}

	/*private Map<String, Integer> hotelRegularCustomer;

    HotelReservation(){
        hotelRegularCustomer = new HashMap<String, Integer>();

    }

    public void addHotelwithRegularCustomerRate() {
        hotelRegularCustomer.put("RidgeWood", 110);
        hotelRegularCustomer.put("BridgeWood", 160);
        hotelRegularCustomer.put("LakeWood", 220);
    }

    public void calculateCheapestHotelAndRate(String dateOfArrival, String dateOfDeparture) throws Exception {

        Date dateofArrival = convertStringToDate(dateOfArrival);
        Date dateofDeparture = convertStringToDate(dateOfDeparture);
        long totalPeriodOfStay = 0;
        if(dateofArrival!=null && dateofDeparture!=null) {
            totalPeriodOfStay = (dateofDeparture.getTime() - dateofArrival.getTime());
        }

        int totalDays = (int) TimeUnit.DAYS.convert(totalPeriodOfStay,TimeUnit.MILLISECONDS);

        for(Map.Entry<String,Integer> entry : hotelRegularCustomer.entrySet()) {
            entry.setValue((entry.getValue() * (totalDays + 1)));
        }

        //int totalcheapestRate = Collections.min(hotelRegularCustomer.values());
        String hotelName = hotelRegularCustomer.entrySet().stream().min(Entry.comparingByValue()).get().getKey();
        int totalRate = hotelRegularCustomer.entrySet().stream().min(Entry.comparingByValue()).get().getValue();
        System.out.println("The cheapest hotel for the given date range is "+hotelName+" "+" and the total rate for the given days stay is : "+totalRate+"$");
    }

    public Date convertStringToDate(String date) throws Exception{
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("ddMMMyyyy").parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }*/

}
