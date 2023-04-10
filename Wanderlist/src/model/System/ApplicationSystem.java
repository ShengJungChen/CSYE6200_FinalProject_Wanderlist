package model.System;

import model.User.UserDirectory;

public class ApplicationSystem {

	private static final DB4OUtils db4oUtils = DB4OUtils.getInstance();
	private static final ApplicationSystem database = db4oUtils.retrieveSystem();

	private UserDirectory userDirectory;

	public ApplicationSystem() {
		this.userDirectory = new UserDirectory();
	}

	public static DB4OUtils getDb4oUtil() {
		return db4oUtils;
	}

	public static ApplicationSystem getInstance() {
		return database;
	}

	public UserDirectory getUserDirectory() {
		return userDirectory;
	}

	public void store() {
		db4oUtils.storeSystem(this);
	}

}
