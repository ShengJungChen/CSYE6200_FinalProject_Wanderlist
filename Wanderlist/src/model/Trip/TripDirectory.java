package model.Trip;

import java.util.ArrayList;

import model.User.User;

public class TripDirectory {

	private User user;
	private ArrayList<Trip> trips;

	// constructor
	public TripDirectory() {
		this.trips = new ArrayList<Trip>();
	}

	// getter and setter
	public ArrayList<Trip> getTrips() {
		return trips;
	}

	public void setTrips(ArrayList<Trip> trips) {
		this.trips = trips;
	}

	// add new trip
	public void addNewTrip(String tripName, String country, String city, int startYear, int startMonth, int startDate,
			int endYear, int endMonth, int endDate) {
		Trip trip = new Trip(tripName, country, city, startYear, startMonth, startDate, endYear, endMonth, endDate);
		this.trips.add(trip);
	}

	// delete trip
	public void deleteTrip(Trip trip) {
		this.trips.remove(trip);
	}

	// view trip

}
