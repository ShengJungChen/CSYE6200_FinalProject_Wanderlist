package application;

import java.util.Calendar;

import model.System.ApplicationSystem;
import model.Trip.Day;
import model.Trip.Trip;
import model.User.User;

public class TestDriver {

	public static void main(String[] args) {

		ApplicationSystem database = ApplicationSystem.getInstance();

		User user = database.getUserDirectory().getUserByEmail("123");

		Trip trip = user.getTrips().getTrips().get(0);

		for (Day day : trip.getDays().getDays()) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(day.getDate());

			int daynum = calendar.get(Calendar.DAY_OF_WEEK);
			System.out.println(day.getDate() + day.getWeekDay() + daynum);
		}

//		user.getTrips().getTrips().get(0).getWishlist().addItem(Type.Buy, "cryingtiger3");
//		user.getTrips().getTrips().get(0).getWishlist().addItem(null, null);
//		
//		database.store();
//		
////		ApplicationSystem.getDb4oUtil().storeSystem(database);
//
//		user.getTrips().getTrips().get(0).getWishlist().addItem(Type.Buy, "cryingtiger1");
//
//		database.store();
//
//		user.getTrips().getTrips().get(0).getWishlist().addItem(null, null);
//
//		ApplicationSystem.getDb4oUtil().storeSystem(database);
//
//		for (String s : database.getUserDirectory().getAllEmail()) {
//			System.out.println(s);
//		}
//
//		for (Trip trip : user.getTrips().getTrips()) {
//			System.out.println(trip.getTripName());
//		}
//
//		ArrayList<Item> items = user.getTrips().getTrips().get(0).getWishlist().getWishList();
//
//		for (Item item : items) {
//			System.out.print(item.getItemName());
//		}
	}
}
