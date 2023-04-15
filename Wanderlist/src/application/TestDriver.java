package application;

import java.util.ArrayList;

import model.System.ApplicationSystem;
import model.Trip.Item;
import model.Trip.Item.Type;
import model.Trip.Trip;
import model.User.User;

public class TestDriver {

	public static void main(String[] args) {

		ApplicationSystem database = ApplicationSystem.getInstance();

		User user = database.getUserDirectory().getUserByEmail("123");

		user.getTrips().getTrips().get(0).getWishlist().addItem(Type.Buy, "cryingtiger3");
		user.getTrips().getTrips().get(0).getWishlist().addItem(null, null);
		
		database.store();
		
//		ApplicationSystem.getDb4oUtil().storeSystem(database);

		user.getTrips().getTrips().get(0).getWishlist().addItem(Type.Buy, "cryingtiger1");

		database.store();

		user.getTrips().getTrips().get(0).getWishlist().addItem(null, null);

		ApplicationSystem.getDb4oUtil().storeSystem(database);

		for (String s : database.getUserDirectory().getAllEmail()) {
			System.out.println(s);
		}

		for (Trip trip : user.getTrips().getTrips()) {
			System.out.println(trip.getTripName());
		}

		ArrayList<Item> items = user.getTrips().getTrips().get(0).getWishlist().getWishList();

		for (Item item : items) {
			System.out.print(item.getItemName());
		}
	}
}
