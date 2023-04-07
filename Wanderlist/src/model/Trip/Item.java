package model.Trip;

public abstract class Item {

	private Type type;

	public enum Type {

		Eat, Play, See, Buy

	}

	private Trip trip;
	private String itemName;
	private String url;
	private String address;
	private String itemNote;

	// constructor
	public Item(Type type, Trip trip, String itemName) {
		this.type = type;
		this.trip = trip;
		this.itemName = itemName;
	}

	// getter and setter

	public Trip getTrip() {
		return trip;
	}

	public String getType() {
		return type.name();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getItemNote() {
		return itemNote;
	}

	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}

	// update item
	public abstract void updateItem();

}
