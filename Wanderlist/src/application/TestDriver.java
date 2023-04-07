package application;

public class TestDriver {

	public static void main(String[] args) {

		DB4OUtils db4oUtils = DB4OUtils.getInstance();
		ApplicationSystem appSystem = db4oUtils.retrieveSystem();

		appSystem.getUserDirectory().addNewUser("anita@gmail.com", "anita");

		System.out.println(appSystem.getUserDirectory().getUsers().get(0).getUserEmail());

	}

}
