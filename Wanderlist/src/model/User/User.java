package model.User;

import java.io.Serializable;

import model.Trip.TripDirectory;

public class User implements Serializable {
	private String email;
	private String password;
	private TripDirectory trips;

	// constructor
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.trips = new TripDirectory(this);
	}

	// getter and setter
	public String getUserEmail() {
		return email;
	}

	public void setUserEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TripDirectory getTrips() {
		return trips;
	}

	public void setTrips(TripDirectory trips) {
		this.trips = trips;
	}

}
