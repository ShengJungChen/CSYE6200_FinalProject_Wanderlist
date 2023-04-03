package model.User;

import model.Trip.TripDirectory;

public class User {
	private String email;
	private String password;
	private TripDirectory Trips;

	// constructor
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.Trips = new TripDirectory(this);
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
		return Trips;
	}

	public void setTrips(TripDirectory Trips) {
		this.Trips = Trips;
	}
}
