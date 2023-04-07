package application;

import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;

/**
 *
 * @author anitachen
 */
public class DB4OUtils {

	private static final String FILENAME = Paths.get("Database.db4o").toAbsolutePath().toString();// path to the data
																									// store
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
			config.common().objectClass(ApplicationSystem.class).cascadeOnUpdate(true); // Change to the object you want
																						// to save
			ObjectContainer db = Db4oEmbedded.openFile(config, FILENAME);
			return db;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "NO CONNECTION", "WARNING", JOptionPane.WARNING_MESSAGE);
//            System.out.print(ex.getMessage());
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
		ObjectSet<ApplicationSystem> systems = conn.query(ApplicationSystem.class); // Change to the object you want to
																					// save
		ApplicationSystem system;
		if (systems.size() == 0) {
			system = new ApplicationSystem();
//			system = ConfigureASystem.configure(); // If there's no System in the record, create a new one
		} else {
			system = systems.get(systems.size() - 1);
		}
		conn.close();
		return system;
	}

}
