package application.Trip;

import java.io.IOException;
import java.util.Optional;

import application.Dashboard.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User.User;

public class AddTripController {

	private User user;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnCancel;

	@FXML
	private Pane paneTrip;

	@FXML
	private Parent gridEdit;

	@FXML
	private EditTripController gridEditController;

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
	public void saveAction(ActionEvent event) throws IOException {
		gridEditController.addNew(event, user);
	}

	@FXML
	public void cancelAction(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setHeaderText("Are you sure you want to cancel?");
		alert.setContentText("All unsaved information will be lost.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../application/Dashboard/dashboardPage.fxml"));

			Parent root = loader.load();
			DashboardController dashboardController = loader.getController();
			dashboardController.loadPage(user);

			Stage stage = (Stage) btnAdd.getScene().getWindow();
			stage.setScene(new Scene(root));

		} else {
			event.consume();
		}
	}

	@FXML
	public void backAction(ActionEvent event) throws IOException {
		cancelAction(event);
	}

}
