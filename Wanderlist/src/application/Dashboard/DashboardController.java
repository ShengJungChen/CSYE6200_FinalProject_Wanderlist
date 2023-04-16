package application.Dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import application.Trip.AddTripController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Trip.Trip;
import model.User.User;

public class DashboardController {

	User user;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnAdd;

	@FXML
	private Label labelGreet;

	@FXML
	private VBox vboxUpcomings;

	@FXML
	private VBox vboxPast;

	// Load and Display all trips
	public void loadPage(User user) {

		// set basic data
		this.user = user;
		labelGreet.setText("Hello, " + user.getUserEmail() + "!");

		// sort trips by date
		ArrayList<Trip> trips = user.getTrips().getTrips();

		Collections.sort(trips, new Comparator<Trip>() {
			@Override
			public int compare(Trip t1, Trip t2) {
				return t1.getStartDate().compareTo(t2.getStartDate());
			}
		});

		// fill in the dashboard with trips
		Iterator<Trip> tripIterator = trips.iterator();

		while (tripIterator.hasNext()) {
			Trip trip = tripIterator.next();

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("tripItem.fxml"));

			try {
				HBox hBox = fxmlLoader.load();
				TripItemController tripItemController = fxmlLoader.getController();
				tripItemController.setData(trip);
				if (trip.getStartDate().after(new Date())) {
					// upcoming trip
					vboxUpcomings.getChildren().add(hBox);
				} else {
					// past trip
					vboxPast.getChildren().add(hBox);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addAction(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/AddTrip.fxml"));

		Parent root = loader.load();
		AddTripController addTripController = loader.getController();
		addTripController.setUser(user);

		Stage stage = (Stage) btnAdd.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void handleButtonLogout(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setHeaderText("Are you sure you want to log out?");
		alert.setContentText("Your changes have been saved.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			Parent root = FXMLLoader.load(getClass().getResource("../../application/login.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Login");
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			event.consume();
		}
	}
}
