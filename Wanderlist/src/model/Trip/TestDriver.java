package model.Trip;

import model.User.User;

public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TripDirectory tripDirectory = new TripDirectory();
		User user = new User("email", "pw");

		System.out.println("---DAY TEST---");

		System.out.println("---create trip---");

		Trip trip = new Trip("NYC trip", "USA", "NYC", 2023, 3, 31, 2023, 4, 2, null);

		for (Day d : trip.getDays().getDays()) {
			System.out.println(d.getDate().getTime());
		}

		System.out.println("---update trip---");
		trip.updateDate(2023, 3, 31, 2023, 4, 5);

		for (Day d : trip.getDays().getDays()) {
			System.out.println(d.getDate().getTime());
		}

		System.out.println("---create item---");

		trip.getWishlist().addItem(Item.Type.Eat, "Krasi");

		for (Item item : trip.getWishlist().getWishList()) {
			System.out.println(item.getItemName() + item.getType());
		}

		System.out.println("---remove item---");

		trip.getWishlist().removeItemFromWishlist(trip.getWishlist().getWishList().get(0));

		System.out.println(trip.getWishlist().getWishList().size());
	}

}
