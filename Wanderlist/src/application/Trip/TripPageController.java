package application.Trip;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Dashboard.DashboardController;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Trip.Trip;

public class TripPageController implements Initializable {

	private Trip trip;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnRemove;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnView;

	@FXML
	private HBox paneDays;

	@FXML
	private VBox paneTripDetailHolder;

	@FXML
	private Pane paneTrip;

	@FXML
	private VBox paneWishlist;

	private EditTripController editTripController;

	@FXML
	private VBox wishlistHolder;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setData(Trip trip) throws IOException {

		this.trip = trip;

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("../../application/Trip/ShowTrip.fxml"));

		GridPane pane = fxmlLoader.load();

		ShowTripController showTripController = fxmlLoader.getController();
		showTripController.setData(this.trip);

		paneTripDetailHolder.getChildren().add(pane);

		loadWishlist();

	}

	@FXML
	public void backAction(ActionEvent event) throws IOException {

		// back alert
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setHeaderText("Are you sure you want to cancel edit?");
		alert.setContentText("All unsaved information will be lost.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../application/Dashboard/dashboardPage.fxml"));
			Parent root = loader.load();
			DashboardController dashboardController = loader.getController();
			dashboardController.loadPage(trip.getUser());

			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.setScene(new Scene(root));

		} else {
			event.consume();
		}
	}

	@FXML
	public void editAction(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("../../application/Trip/EditTrip.fxml"));

		GridPane pane = fxmlLoader.load();

		EditTripController editTripController = fxmlLoader.getController();
		editTripController.setData(this.trip);
		this.editTripController = editTripController;

		paneTripDetailHolder.getChildren().clear();
		paneTripDetailHolder.getChildren().add(pane);

		btnEdit.setDisable(true);
		btnSave.setDisable(false);
		btnCancel.setDisable(false);
	}

	@FXML
	public void saveAction(ActionEvent event) throws IOException {

		this.editTripController.saveUpdate(event, trip);

	}

	@FXML
	public void cancelAction(ActionEvent event) throws IOException {
		// show alert for unsaved message
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setHeaderText("Are you sure you want to cancel edit?");
		alert.setContentText("All unsaved information will be lost.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));

			Parent root = loader.load();
			TripPageController tripPageController = loader.getController();
			tripPageController.setData(trip);

			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.setScene(new Scene(root));

		} else {
			event.consume();
		}
	}

	public void loadWishlist() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("../../application/Trip/wishlist.fxml"));

		VBox pane = fxmlLoader.load();

		WishlistController wishlistController = fxmlLoader.getController();

		wishlistHolder.getChildren().add(pane);
	}
}
