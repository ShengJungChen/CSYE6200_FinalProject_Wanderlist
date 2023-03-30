package model.User;

import java.util.ArrayList;

public class UserDirectory {
	public ArrayList<User> users;
	
	
	//constructor
	public UserDirectory(ArrayList<User> users) {
		super();
		this.users = users;
	}
	
	
	//getter
	public ArrayList<User> getUsers(){
		return users;
	}
	
	

	//create User
	public User addNewUser(String email, String password) {
		User user = new User(email, password);
		return user;
		
	}
	

}
