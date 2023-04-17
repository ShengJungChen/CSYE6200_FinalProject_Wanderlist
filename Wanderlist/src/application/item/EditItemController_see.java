package application.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import application.Trip.TripPageController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Item;
import model.Trip.Item.Type;
import model.Trip.See;
import model.Trip.Trip;

public class EditItemController_see extends Application {
	// button
	@FXML
	private Button btnBack;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnEdit;

	// TextField
	@FXML
	private TextField inputName;
	@FXML
	private TextField inputUrl;
	@FXML
	private TextField inputAddress;
	@FXML
	private TextArea inputNote;
	@FXML
	private TextField inputMean;

	private Trip trip;
	private Item item;

	ApplicationSystem database = ApplicationSystem.getInstance();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EditItem_see.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	public void initialize() {

	}

	public void setData(Trip trip, Item item) {
		this.trip = trip;
		this.item = item;
		See see = (See) item;

		inputName.setText(see.getItemName());
		inputUrl.setText(see.getUrl());
		inputAddress.setText(see.getAddress());
		inputNote.setText(see.getItemNote());
		inputMean.setText(see.getTrafficMean());

	}

	public void saveChange(ActionEvent event) throws IOException {

		See see = (See) item;
		String name = inputName.getText();
		String url = inputUrl.getText();
		String address = inputAddress.getText();
		String note = inputNote.getText();
		String mean = inputMean.getText();

		if (name.isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Please enter the schedule name.");
			alert.showAndWait();
			event.consume();
		} else {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFRIMATION");
			alert.setContentText("Are you sure you want to update this schedule?");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				see.setItemName(name);
				see.setUrl(url);
				see.setAddress(address);
				see.setItemNote(note);
				see.setTrafficMean(mean);

				database.store();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeViewPane.fxml"));
				Parent root = loader.load();
				SeeViewController seeViewController = loader.getController();
				seeViewController.SetItemDetails(see, trip);

				Stage stage = (Stage) btnBack.getScene().getWindow();
				stage.setScene(new Scene(root));
			}
		}
	}

	@FXML
	private void backToTripAction(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("BACK");
		alert.setHeaderText("Changes you made will not be saved.");
		alert.setContentText("Are you sure you want to leave editing schedule?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeViewPane.fxml"));
			Parent root = loader.load();
			SeeViewController seeViewController = loader.getController();
			seeViewController.SetItemDetails(item, trip);

			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.setScene(new Scene(root));
		}
	}

	@FXML
	private void cancelAction(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setHeaderText("Changes you made will not be saved.");
		alert.setContentText("Are you sure you want to cancel editing schedule?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeViewPane.fxml"));
			Parent root = loader.load();
			SeeViewController seeViewController = loader.getController();
			seeViewController.SetItemDetails(item, trip);

			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.setScene(new Scene(root));
		}
	}

}
