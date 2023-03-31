package model.Trip;

import java.util.Calendar;

public class Trip {
	private String tripName;
	private Calendar startDate;
	private Calendar endDate;
	private String country;
	private String city;
	private People people;
	private String note;
	private Wishlist wishlist;
	private DayList days;

	// constructor
	public Trip(String tripName, String country, String city, int startYear, int startMonth, int startDate, int endYear,
			int endMonth, int endDate) {

		this.tripName = tripName;
		this.country = country;
		this.city = city;

		setStartDate(startYear, startMonth, startDate);
		setEndDate(endYear, endMonth, endDate);

		this.wishlist = new Wishlist(this);
		this.days = new DayList(this);
	}

	// getter and setter

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(int year, int month, int date) {

		Calendar start = Calendar.getInstance();

		start.set(year, month - 1, date, 0, 0, 0);
		start.set(Calendar.MILLISECOND, 0);

		this.startDate = start;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(int year, int month, int date) {

		Calendar end = Calendar.getInstance();
		end.set(year, month - 1, date, 0, 0, 0);

		end.set(Calendar.MILLISECOND, 0);

		this.endDate = end;
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

	// delete a trip
	public void deleteTrip() {

	}

	// update the date

	// calculate number of days
	public Integer calculateNubmerOfDays() {

		long difference = this.getEndDate().getTimeInMillis() - this.getStartDate().getTimeInMillis();
		double daysBetween = difference / (1000 * 60 * 60 * 24);

		int num = (int) daysBetween + 1;

		return num;
	}

	// add item to wishlist

	// delete item from wishlist

	// add item from wishlist

	// view item

}
