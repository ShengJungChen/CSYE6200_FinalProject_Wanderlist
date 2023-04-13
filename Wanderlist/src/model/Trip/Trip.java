package model.Trip;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import model.User.User;

public class Trip implements Serializable {
	private User user;
	private String tripName;
	private Date startDate;
	private Date endDate;
	private String country;
	private String city;
	private People people;
	private String note;
	private Wishlist wishlist;
	private DayList days;

	// constructor
	public Trip(User user, String tripName, String country, String city, int startYear, int startMonth, int startDate,
			int endYear, int endMonth, int endDate, String note) {

		this.user = user;

		this.tripName = tripName;
		this.country = country;
		this.city = city;
		this.note = note;

		setStartDate(startYear, startMonth, startDate);
		setEndDate(endYear, endMonth, endDate);

		this.wishlist = new Wishlist(this);
		this.days = new DayList(this);
	}

	// getter and setter

	public User getUser() {
		return user;
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

	public void setStartDate(int year, int month, int date) {

		Calendar start = Calendar.getInstance();

		start.set(year, month - 1, date, 0, 0, 0);
		start.set(Calendar.MILLISECOND, 0);

		Date startDate = start.getTime();

		this.startDate = startDate;
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

	public People getPeople() {
		return people;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(int year, int month, int date) {

		Calendar end = Calendar.getInstance();
		end.set(year, month - 1, date, 0, 0, 0);

		end.set(Calendar.MILLISECOND, 0);

		Date endDate = end.getTime();

		this.endDate = endDate;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public DayList getDays() {
		return days;
	}

	// update trip
//	public void updateTrip(User user, String tripName, Calendar startDate, Calendar endDate, String country,
//			String city, User people, String note, ArrayList<Item> wishlist, ArrayList<Day> days) {
//		setUser(user);
//		setTripName(tripName);
//		setStartDate(startDate);
//		setEndDate(endDate);
//		setCountry(country);
//		setCity(city);
//		setPeople(people);
//		setNote(note);
//		setWishlist(wishlist);
//		setDays(days);
//
//	}

	// update the date
	public void updateDate(int startYear, int startMonth, int startDate, int endYear, int endMonth, int endDate) {
		setStartDate(startYear, startMonth, startDate);
		setEndDate(endYear, endMonth, endDate);
		days.createDays();
	}

	// calculate number of days
	public Integer calculateNubmerOfDays() {

		long difference = this.getEndDate().getTime() - this.getStartDate().getTime();
		double daysBetween = difference / (1000 * 60 * 60 * 24);

		int num = (int) daysBetween + 1;

		return num;
	}

	// add item to wishlist
	public void addItemToWishlist(Item.Type type, String itemName) {
		wishlist.addItem(type, itemName);
	}

	public void addItemToWishlist(Item item) {
		wishlist.addItem(item);
	}

	// delete item from wishlist
	public void removeItemFromWishlist(Item item) {
		wishlist.removeItemFromWishlist(item);
	}

}
