package model.Trip;

import java.util.ArrayList;
import java.util.Date;

import model.User.User;

public class Trip {
	private User user;
	private String tripName;
	private Date startDate;
	private Date endDate;
	private String country;
	private String city;
	private User people;
	private String note;
	private ArrayList<Item> wishlist;
	private ArrayList<Day> days;
	
	
	//constructor
	public Trip(User user, String tripName, Date startDate, Date endDate, String country, String city, User people,
			String note, ArrayList<Item> wishlist, ArrayList<Day> days) {
		super();
		this.user = user;
		this.tripName = tripName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
		this.city = city;
		this.people = people;
		this.wishlist = new ArrayList<Item>();
		//this.days = days;
	}

	
	//getter and setter

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getTripName() {
		return tripName;
	}


	public void setTripName(String tripName) {
		this.tripName = tripName;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public User getPeople() {
		return people;
	}


	public void setPeople(User people) {
		this.people = people;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public ArrayList<Item> getWishlist() {
		return wishlist;
	}


	public void setWishlist(ArrayList<Item> wishlist) {
		this.wishlist = wishlist;
	}


	public ArrayList<Day> getDays() {
		return days;
	}


	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}
	
	//update trip
	public void updateTrip(User user, String tripName, Date startDate, Date endDate, String country, String city, User people,
			String note, ArrayList<Item> wishlist, ArrayList<Day> days) {
		setUser(user);
		setTripName(tripName);
		setStartDate(startDate);
		setEndDate(endDate);
		setCountry(country);
		setCity(city);
		setPeople(people);
		setNote(note);
		setWishlist(wishlist);
		setDays(days);
		
		
	}
	
	//delete a trip
	public void deleteTrip() {
		
		
	}
	
	
	//update the date
	
	
	//calculate number of days
	
	
	
	//add item to wishlist
	
	
	//delete item from wishlist
	
	//add item from wishlist
	
	//view item
	
	
	

}
