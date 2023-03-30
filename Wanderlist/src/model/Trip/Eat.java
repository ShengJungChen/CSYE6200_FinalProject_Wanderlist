package model.Trip;

import java.util.ArrayList;

public class Eat {
	private ArrayList<Integer> operatingDays;
	private Integer startHour;
	private Integer endHour;
	private boolean reservation;
	
	//constructor
	public Eat(ArrayList<Integer> operatingDays, Integer startHour, Integer endHour, boolean reservation) {
		super();
		this.operatingDays = operatingDays;
		this.startHour = startHour;
		this.endHour = endHour;
		this.reservation = reservation;
	}
	
	//getter and setter
	public ArrayList<Integer> getOperatingDays() {
		return operatingDays;
	}
	public void setOperatingDays(ArrayList<Integer> operatingDays) {
		this.operatingDays = operatingDays;
	}
	public Integer getStartHour() {
		return startHour;
	}
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}
	public Integer getEndHour() {
		return endHour;
	}
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}
	public boolean isReservation() {
		return reservation;
	}
	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}
	
	

}
