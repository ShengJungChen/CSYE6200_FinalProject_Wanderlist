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
//
		ApplicationSystem.getDb4oUtil().storeSystem(database);
//
		for (User u : database.getUserDirectory().getUsers()) {
			System.out.println(u.getUserEmail());
		}

		for (Trip trip : user.getTrips().getTrips()) {
			System.out.println(trip.getTripName());
		}

//		DB4OUtils db4oUtils = DB4OUtils.getInstance();
//		ApplicationSystem appSystem = db4oUtils.retrieveSystem();
//
//		appSystem.getUserDirectory().addNewUser("123@gmail.com", "anita");
//
//		System.out.println(appSystem.getUserDirectory().getUsers().get(0).getUserEmail());
//
//		db4oUtils.storeSystem(appSystem);

	}

}
