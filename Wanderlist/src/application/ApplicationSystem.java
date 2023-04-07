package application;

import model.User.UserDirectory;

public class ApplicationSystem {

	private UserDirectory userDirectory;
	private static DB4OUtils db4oUtil = DB4OUtils.getInstance();

	public ApplicationSystem() {
		this.userDirectory = new UserDirectory();
	}

	public UserDirectory getUserDirectory() {
		return userDirectory;
	}

	public void setUserDirectory(UserDirectory userDirectory) {
		this.userDirectory = userDirectory;
	}

}
