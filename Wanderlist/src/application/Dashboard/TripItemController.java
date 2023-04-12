package application.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Trip.TripPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Trip;

public class TripItemController implements Initializable {

	ApplicationSystem database = ApplicationSystem.getInstance();

	Trip trip;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnView;

	@FXML
	private Label lblDate;

	@FXML
	private Label lblName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void setData(Trip trip) {

		this.trip = trip;

		lblName.setText(trip.getTripName());

		String pattern = "yyyy/MM/dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String start = simpleDateFormat.format(trip.getStartDate().getTime());
		String end = simpleDateFormat.format(trip.getEndDate().getTime());

		lblDate.setText(start + " - " + end);
	}

	@FXML
	public void viewAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));

		Parent root = loader.load();
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

		Stage stage = (Stage) lblName.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	@FXML
	public void deleteAction(ActionEvent event) throws IOException {
		// alert
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setHeaderText("Are you sure you want to delete this trip?");
		alert.setContentText("This action cannot be recovered.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			// delete trip & save to database
			database.getUserDirectory().getUserByEmail("anita@gmail.com").getTrips().deleteTrip(trip);
			database.store();

			// refresh dashboard
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardPage.fxml"));
			Parent root = loader.load();
			DashboardController dashboardController = loader.getController();
			dashboardController.loadPage(trip.getUser());
			Stage stage = (Stage) lblName.getScene().getWindow();
			stage.setScene(new Scene(root));

		} else {
			event.consume();
		}

	}

}
