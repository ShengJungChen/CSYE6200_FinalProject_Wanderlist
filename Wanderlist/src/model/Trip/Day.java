package model.Trip;

import java.util.ArrayList;
import java.util.Date;

public class Day {
	private Trip trip;
	private Date date;
	private Integer weekDay;
	private ArrayList<Item> schedule;
	
	//constructor
	public Day(Trip trip, Date date, Integer weekDay, ArrayList<Item> schedule) {
		super();
		this.trip = trip;
		this.date = date;
		this.weekDay = weekDay;
		this.schedule = schedule;
	}
	
	

	//getter and setter
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}

	public ArrayList<Item> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Item> schedule) {
		this.schedule = schedule;
	}
	

	//remove the item from the day
	
	//view item
	
	
	
	

}
