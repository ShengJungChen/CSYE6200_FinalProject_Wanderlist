package model.User;

import java.util.ArrayList;

public class UserDirectory {
	public ArrayList<User> users;

	// constructor
	public UserDirectory() {
		this.users = new ArrayList<User>();
	}

	// getter
	public ArrayList<User> getUsers() {
		return users;
	}

	// create User
	public User addNewUser(String email, String password) {
		User user = new User(email, password);
		users.add(user);
		return user;

	}

}
