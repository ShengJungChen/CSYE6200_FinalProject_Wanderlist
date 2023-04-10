package model.Trip;

import java.util.ArrayList;
import java.util.Calendar;

public class DayList {

	private Trip trip;
	private ArrayList<Day> days;

	public DayList(Trip trip) {

		this.trip = trip;
		this.days = new ArrayList<Day>();

		createDays();

	}

	public void createDays() {

		this.days.clear();

		Calendar start = Calendar.getInstance();
		start.setTime(trip.getStartDate());
		int numberOfDays = trip.calculateNubmerOfDays();

		Calendar current = (Calendar) start.clone();

		for (int i = 0; i < numberOfDays; i++) {
			Calendar clone = (Calendar) current.clone();
			Day day = new Day(this, clone.getTime());
			days.add(day);
			current.add(Calendar.DATE, 1);
		}
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public ArrayList<Day> getDays() {
		return days;
	}

	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}

}
