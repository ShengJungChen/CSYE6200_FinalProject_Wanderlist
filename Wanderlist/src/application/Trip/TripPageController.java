package application.Trip;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
	private Pane paneTrip;

	@FXML
	private VBox paneWishlist;

	@FXML
	private Parent gridEdit;

	@FXML
	private EditTripController gridEditController;

	@FXML
	private Parent gridShow;

	@FXML
	private ShowTripController gridShowController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// set trip info to show mode
		gridEdit.setVisible(false);

	}

	@FXML
	public void editAction(ActionEvent event) {
		gridEdit.setVisible(true);
		gridShow.setVisible(false);
		btnEdit.setDisable(true);
		btnSave.setDisable(false);
		btnCancel.setDisable(false);
	}

	@FXML
	public void saveAction(ActionEvent event) throws IOException {

		// show alert to confirm save
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setHeaderText("Are you sure you want to save this edit?");
		alert.setContentText("Old information will be overwritten.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			// GET DATA
			// save data
			gridEditController.saveUpdate();
			// show mode
			gridShow.setVisible(true);
			gridEdit.setVisible(false);
			btnEdit.setDisable(false);
			btnSave.setDisable(true);
			// JUMP TO END?
			btnCancel.setDisable(true);
		} else {
			event.consume();
		}
	}

	@FXML
	public void cancelAction(ActionEvent event) {
		// show alert for unsaved message
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setHeaderText("Are you sure you want to cancel edit?");
		alert.setContentText("All unsaved information will be lost.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			gridShow.setVisible(true);
			gridEdit.setVisible(false);
			btnEdit.setDisable(false);
			btnSave.setDisable(true);
			// JUMP TO END?
			btnCancel.setDisable(true);
		} else {
			event.consume();
		}
	}

}
