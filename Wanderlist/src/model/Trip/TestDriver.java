package model.Trip;

import java.util.Calendar;

import model.User.User;

public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TripDirectory tripDirectory = new TripDirectory();

		User user = new User("email", "pw");

		System.out.println("---START---");

		Calendar start = Calendar.getInstance();
		start.set(2023, Calendar.MARCH, 31, 0, 0, 0);
		start.set(Calendar.MILLISECOND, 0);

		System.out.println(start.get(Calendar.YEAR));
		System.out.println(start.get(Calendar.MONTH));
		System.out.println(start.get(Calendar.DATE));
		System.out.println(start.get(Calendar.DAY_OF_WEEK));
		System.out.println(start.get(Calendar.MILLISECOND));

		System.out.println(start.getTime());

		System.out.println("---END---");

		Calendar end = Calendar.getInstance();
		end.set(2023, Calendar.APRIL, 2, 0, 0, 0);
		end.set(Calendar.MILLISECOND, 0);

		System.out.println(end.get(Calendar.YEAR));
		System.out.println(end.get(Calendar.MONTH));
		System.out.println(end.get(Calendar.DATE));
		System.out.println(end.get(Calendar.DAY_OF_WEEK));
		System.out.println(end.get(Calendar.MILLISECOND));

		System.out.println(end.getTime());

		System.out.println("---START & END---");

		System.out.println(start.getTime());
		System.out.println(end.getTime());

		System.out.println("---CALCULATE---");

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

		System.out.println("---DAY TEST---");

		Trip trip = new Trip("NYC trip", "USA", "NYC", 2023, 3, 31, 2023, 4, 2);

		for (Day d : trip.getDays().getDays()) {
			System.out.println(d.getDate().getTime());
		}

		trip.updateDate(2023, 3, 31, 2023, 4, 5);

		for (Day d : trip.getDays().getDays()) {
			System.out.println(d.getDate().getTime());
		}

	}

}
