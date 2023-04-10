package model.System;

import java.nio.file.Paths;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;

public class DB4OUtils {

	// path to the data storage
	private static final String FILENAME = Paths.get("Database.db4o").toAbsolutePath().toString();
	private static DB4OUtils dB4OUtil;

	public synchronized static DB4OUtils getInstance() {
		if (dB4OUtil == null) {
			dB4OUtil = new DB4OUtils();
		}
		return dB4OUtil;
	}

	protected synchronized static void shutdown(ObjectContainer conn) {
		if (conn != null) {
			conn.close();
		}
	}

	private ObjectContainer createConnection() {
		try {
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			config.common().add(new TransparentPersistenceSupport());
			// Controls the number of objects in memory
			config.common().activationDepth(Integer.MAX_VALUE);
			// Controls the depth/level of updation of Object
			config.common().updateDepth(Integer.MAX_VALUE);
			// Register your top most Class here
			config.common().objectClass(ApplicationSystem.class).cascadeOnUpdate(true);
			// Change to the object you want to save
			ObjectContainer db = Db4oEmbedded.openFile(config, FILENAME);
			return db;
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		return null;
	}

	public synchronized void storeSystem(ApplicationSystem system) {
		ObjectContainer conn = createConnection();
		conn.store(system);
		conn.commit();
		conn.close();
	}

	public ApplicationSystem retrieveSystem() {

		ObjectContainer conn = createConnection();
		// Change to the object you want to save
		ObjectSet<ApplicationSystem> systems = conn.query(ApplicationSystem.class);
		ApplicationSystem system;
		if (systems.size() == 0) {
			// If there's no System in the record, create a new one
			system = new ApplicationSystem();
		} else {
			system = systems.get(systems.size() - 1);
		}
		conn.close();
		return system;
	}

}
