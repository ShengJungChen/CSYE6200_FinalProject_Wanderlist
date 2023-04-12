package model.User;

import java.util.HashMap;
import java.util.Set;

public class UserDirectory {
	public HashMap<String, User> userHashMap;

	// constructor
	public UserDirectory() {
		this.userHashMap = new HashMap<String, User>();
	}

	// create user
	public User addNewUser(String email, String password) {

		User user = new User(email, password);

		if (userHashMap.containsKey(email)) {
			return null;
		} else {
			userHashMap.put(email, user);
			return user;
		}
	}

	// get user by email
	public User getUserByEmail(String email) {
		return userHashMap.get(email);
	}

	// get all user
	public Set<String> getAllEmail() {
		return userHashMap.keySet();
	}

}
