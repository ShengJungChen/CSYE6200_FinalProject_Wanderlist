package model.Trip;

import model.User.User;

public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user = new User("email", "pw");

//		System.out.println("---CALCULATE---");

//		Trip trip = new Trip(user, "NYC trip", start, end, "USA", "NYC");
//
//		System.out.println(trip.getStartDate().get(Calendar.MONTH) + 1);
//		System.out.println(trip.getStartDate().get(Calendar.DATE));
//
//		System.out.println(trip.getEndDate().get(Calendar.MONTH) + 1);
//		System.out.println(trip.getEndDate().get(Calendar.DATE));
//
//		long difference = trip.getEndDate().getTimeInMillis() - trip.getStartDate().getTimeInMillis();
//		System.out.println(trip.getStartDate().getTime().getTime());
//		System.out.println(trip.getEndDate().getTime().getTime());
//
//		double daysBetween = difference / (1000 * 60 * 60 * 24);
//
//		System.out.println(daysBetween);

		System.out.println("---create trip---");

		user.getTrips().addNewTrip("NYC trip", "USA", "NYC", 2023, 3, 31, 2023, 4, 2, null);
		Trip trip = user.getTrips().getTrips().get(0);

		for (Day d : trip.getDays().getDays()) {
			System.out.println(d.getDate().getTime());
		}

		System.out.println("---update trip date---");
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
