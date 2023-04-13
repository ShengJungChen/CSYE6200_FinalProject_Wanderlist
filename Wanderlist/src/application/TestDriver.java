package application;

import java.util.ArrayList;

import model.System.ApplicationSystem;
import model.Trip.Item;
import model.Trip.Item.Type;
import model.User.User;

public class TestDriver {

	public static void main(String[] args) {

		ApplicationSystem database = ApplicationSystem.getInstance();

//		database.getUserDirectory().addNewUser("123", "123");

		User user = database.getUserDirectory().getUserByEmail("123");
		user.getTrips().getTrips().get(0).getWishlist().addItem(Type.Buy, "cryingtiger1");

		database.store();

//		ApplicationSystem.getDb4oUtil().storeSystem(database);

//		for (String s : database.getUserDirectory().getAllEmail()) {
//			System.out.println(s);
//		}
//
//		for (Trip trip : user.getTrips().getTrips()) {
//			System.out.println(trip.getTripName());
//		}

		ArrayList<Item> items = user.getTrips().getTrips().get(0).getWishlist().getWishList();

		for (Item item : items) {
			System.out.print(item.getItemName());
		}

	}

}
