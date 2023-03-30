package model.Trip;

import java.util.ArrayList;

public class Buy  {
	private ArrayList<Integer> operatingDays;
	private Integer startHour;
	private Integer endHour;
	private ArrayList<String> shoppingList;
	
	//constructor
	public Buy(ArrayList<Integer> operatingDays, Integer startHour, Integer endHour, ArrayList<String> shoppingList) {
		this.operatingDays = operatingDays;
		this.startHour = startHour;
		this.endHour = endHour;
		this.shoppingList = shoppingList;
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

	public ArrayList<String> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(ArrayList<String> shoppingList) {
		this.shoppingList = shoppingList;
	}

	
	
	//add shopping item
	public ArrayList<String> addShoppingItem(Item item,ArrayList<String> shoppingList){
		
		return shoppingList;
	}
	

	//delete shopping item
	public ArrayList<String> deleteShoppingItem(Item item,ArrayList<String> shoppingList){
		
		return shoppingList;
	}
	
}
