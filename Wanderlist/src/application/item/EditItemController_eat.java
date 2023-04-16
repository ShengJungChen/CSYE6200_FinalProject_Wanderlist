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
import model.Trip.See;
import model.Trip.Trip;
import model.Trip.Eat;

public class EditItemController_eat extends Application {
	//button
	@FXML private Button btnBack;
	@FXML private Button btnCancel;
	@FXML private Button btnSave;
	@FXML private Button btnEdit;
	
	//TextField
	@FXML private TextField inputName;
	@FXML private TextField inputUrl;
	@FXML private TextField inputAddress;
	@FXML private TextArea inputNote;
	
	//operating day
	@FXML private CheckBox mon;
	@FXML private CheckBox tue;
	@FXML private CheckBox wed;
	@FXML private CheckBox thur;
	@FXML private CheckBox fri;
	@FXML private CheckBox sat;
	@FXML private CheckBox sun;
	
	//operating hour
	@FXML private ComboBox<Integer> from;
	@FXML private ComboBox<Integer> to;	

	//reservation
	@FXML private CheckBox reservation;
	
	private Trip trip;
	private Item item;

	ApplicationSystem database = ApplicationSystem.getInstance();
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EditItem_eat.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void initialize() {
		
		from.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));
		to.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));
		from.getSelectionModel().select(0);
		to.getSelectionModel().select(0);

	}
	
	public void setData(Trip trip, Item item) {
		this.trip = trip;
		this.item = item;
		
		Eat eat = (Eat) item;
		
		inputName.setText(item.getItemName());
		inputUrl.setText(item.getUrl());
		inputAddress.setText(item.getAddress());
		inputNote.setText(item.getItemNote());
		if(eat.getOperatingDays().contains(2)) { //Monday is 2 in Calendar.DAY_OF_WEAK
			mon.setSelected(true);
			}
		if(eat.getOperatingDays().contains(3)) {
			tue.setSelected(true);
			}
		if(eat.getOperatingDays().contains(4)) {
			wed.setSelected(true);
			}
		if(eat.getOperatingDays().contains(5)) {
			thur.setSelected(true);
			}
		if(eat.getOperatingDays().contains(6)) {
			fri.setSelected(true);
			}
		if(eat.getOperatingDays().contains(7)) {
			sat.setSelected(true);
			}
		if(eat.getOperatingDays().contains(1)) { //Sunday is 1 in Calendar.DAY_OF_WEAK
			sun.setSelected(true);
			}
		
		from.setValue(eat.getStartHour());
		to.setValue(eat.getEndHour());
		reservation.setSelected(eat.isReservation());
		
	}
	
	private ArrayList<Integer> createNewOperatingDays() {
		ArrayList<Integer> updateDays = new ArrayList<>();
		if(mon.isSelected())
			updateDays.add(2);
		if(tue.isSelected())
			updateDays.add(3);
		if(wed.isSelected())
			updateDays.add(4);
		if(thur.isSelected())
			updateDays.add(5);
		if(fri.isSelected())
			updateDays.add(6);
		if(sat.isSelected())
			updateDays.add(7);
		if(sun.isSelected())
			updateDays.add(1);
		
		return updateDays;
	}
	
	public void saveChange(ActionEvent event) throws IOException {
		
		Eat eat = (Eat) this.item;

		String name = inputName.getText();
		String url = inputUrl.getText();
		String address = inputAddress.getText();
		String note = inputNote.getText();
		
		int startHour = from.getValue();
		int endHour = to.getValue();
		
		boolean reservation = this.reservation.isSelected();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setContentText("test");
		alert.setContentText("Are you sure you want to update this schedule?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			eat.setItemName(name);
			eat.setUrl(url);
			eat.setAddress(address);
			eat.setItemNote(note);
			eat.setOperatingDays(createNewOperatingDays());
			eat.setStartHour(startHour);
			eat.setEndHour(endHour);
			eat.setReservation(reservation);
			
			database.store();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EatViewPane.fxml"));
			Parent root = loader.load();
			EatViewController eatViewController = loader.getController();
			eatViewController.SetItemDetails(eat, trip);

			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.setScene(new Scene(root));
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));
		Parent root = loader.load();
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EatViewPane.fxml"));
			Parent root = loader.load();
			EatViewController eatViewController = loader.getController();
			eatViewController.SetItemDetails(item, trip);

		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.setScene(new Scene(root));
		}
	}

}
