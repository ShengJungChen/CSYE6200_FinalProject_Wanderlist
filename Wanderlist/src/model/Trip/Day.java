package model.Trip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Day {
	private DayList days;
	private Calendar date;
	private String weekDay;
	private ArrayList<Item> schedule;

	// constructor
	public Day(DayList days, Calendar date) {
		this.days = days;
		this.date = date;
		this.weekDay = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		this.schedule = new ArrayList<Item>();
	}

	// getter and setter
	public DayList getDays() {
		return days;
	}

	public Calendar getDate() {
		return date;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public ArrayList<Item> getSchedule() {
		return schedule;
	}

	// remove the item from the day

	// view item

}
