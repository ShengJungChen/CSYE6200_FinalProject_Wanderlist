package model.Trip;

public abstract  class Item {
	private Trip trip;
	private String itemName;
	private String url;
	private String address;
	private String itemNote;
	
	//constructor
	public Item(Trip trip, String itemName, String url, String address, String itemNote) {
		super();
		this.trip = trip;
		this.itemName = itemName;
		this.url = url;
		this.address = address;
		this.itemNote = itemNote;
	}
	
	//getter and setter

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQddress() {
		return address;
	}

	public void setQddress(String qddress) {
		this.address = qddress;
	}

	public String getItemNote() {
		return itemNote;
	}

	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}
	
	//update item
	public abstract void updateItem();
	

}
