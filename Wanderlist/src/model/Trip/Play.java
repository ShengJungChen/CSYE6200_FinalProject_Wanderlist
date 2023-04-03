package model.Trip;

import java.util.ArrayList;

public class Play extends Item {
	private ArrayList<Integer> operatingDays;
	private Integer startHour;
	private Integer endHour;
	private boolean ticket;
	private Double price;

	// constructor
	public Play(Type type, Trip trip, String itemName) {
		super(type, trip, itemName);
	}

	// getter and setter
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

	public boolean isTicket() {
		return ticket;
	}

	public void setTicket(boolean ticket) {
		this.ticket = ticket;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public void updateItem() {
		// TODO Auto-generated method stub

	}

}
