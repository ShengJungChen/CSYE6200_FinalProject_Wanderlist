package model.Trip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Day implements Serializable {
	private DayList days;
	private Date date;
	private String weekDay;
	private ArrayList<Item> schedule;

	// constructor
	public Day(DayList days, Date date) {
		this.days = days;
		this.date = date;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.date);

		this.weekDay = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		this.schedule = new ArrayList<Item>();
	}

	// getter and setter
	public DayList getDays() {
		return days;
	}

	public Date getDate() {
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
