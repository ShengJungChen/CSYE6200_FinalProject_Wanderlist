package model.Trip;

import java.util.ArrayList;

public class Wishlist {

	private Trip trip;
	private ArrayList<Item> wishlist;

	public Wishlist(Trip trip) {
		this.trip = trip;
		this.wishlist = new ArrayList<>();
	}

	// getter and setter

	public Trip getTrip() {
		return trip;
	}

	public ArrayList<Item> getWishList() {
		return wishlist;
	}

	public void addItem(Item.Type type, String itemName) {

		switch (type) {
		case Eat:
			Eat eatItem = new Eat(type, trip, itemName);
			this.wishlist.add(eatItem);
			break;
		case Buy:
			Buy buyItem = new Buy(type, trip, itemName);
			this.wishlist.add(buyItem);
			break;
		case Play:
			Play playItem = new Play(type, trip, itemName);
			this.wishlist.add(playItem);
			break;
		case See:
			See seeItem = new See(type, trip, itemName);
			this.wishlist.add(seeItem);
			break;
		}
	}

	public void removeItemFromWishlist(Item item) {
		wishlist.remove(item);
	}

	public void moveItemToDay(Item item) {
	}

}
