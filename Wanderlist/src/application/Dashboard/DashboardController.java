package application.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ApplicationSystem;
import application.DB4OUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Trip.Trip;
import model.User.User;

public class DashboardController implements Initializable {

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnAdd;

	@FXML
	private Label labelGreet;

	@FXML
	private VBox vboxUpcomings;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		DB4OUtils db4oUtils = DB4OUtils.getInstance();
		ApplicationSystem appSystem = db4oUtils.retrieveSystem();

		appSystem.getUserDirectory().addNewUser("anita@gmail.com", "anita");

		System.out.println(appSystem.getUserDirectory().getUsers().get(0).getUserEmail());

		// TEST DATA
		User user = new User("email", "pw");
		user.getTrips().addNewTrip("NYC trip", "USA", "NYC", 2023, 3, 31, 2023, 4, 2, null);
		user.getTrips().addNewTrip("LA", "USA", "Los Angeles", 2023, 11, 1, 2023, 11, 3, null);
		ArrayList<Trip> tripDirectory = user.getTrips().getTrips();
		Trip trip = user.getTrips().getTrips().get(0);

		System.out.println(trip.getTripName());

		// load and display all trips
		for (int i = 0; i < tripDirectory.size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("tripItem.fxml"));

			try {
				HBox hBox = fxmlLoader.load();
				TripItemController tripItemController = fxmlLoader.getController();
				tripItemController.setData(tripDirectory.get(i));
				vboxUpcomings.getChildren().add(hBox);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../../application/Trip/AddTrip.fxml"));
		Stage stage = (Stage) btnAdd.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

}
