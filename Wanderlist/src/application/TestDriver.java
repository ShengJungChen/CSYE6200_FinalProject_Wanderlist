package application;

import model.System.ApplicationSystem;
import model.Trip.Trip;
import model.User.User;

public class TestDriver {

	public static void main(String[] args) {

		ApplicationSystem database = ApplicationSystem.getInstance();

		database.getUserDirectory().addNewUser("anita@gmail.com", "test");

		User user = database.getUserDirectory().getUserByEmail("anita@gmail.com");
		user.getTrips().addNewTrip("TRIP1", "HAHA", "HEY", 2023, 1, 1, 2023, 1, 4, null);

		ApplicationSystem.getDb4oUtil().storeSystem(database);

		for (String s : database.getUserDirectory().getAllEmail()) {
			System.out.println(s);
		}

		for (Trip trip : user.getTrips().getTrips()) {
			System.out.println(trip.getTripName());
		}

	}

}
