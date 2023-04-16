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
import model.Trip.*;

public class EditItemController_buy extends Application {
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

	//shopping list
	@FXML private Button btn_add;
	@FXML private Button btn_delete;
	@FXML private ListView<String> listView;
	@FXML private TextField input;

	Trip trip;
	Item item;
	
	ApplicationSystem database = ApplicationSystem.getInstance();
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EditItem_buy.fxml"));
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
		
		Buy buy = (Buy) item;
		
		inputName.setText(item.getItemName());
		inputUrl.setText(item.getUrl());
		inputAddress.setText(item.getAddress());
		inputNote.setText(item.getItemNote());
		if(buy.getOperatingDays().contains(2)) { //Monday is 2 in Calendar.DAY_OF_WEAK
			mon.setSelected(true);
			}
		if(buy.getOperatingDays().contains(3)) {
			tue.setSelected(true);
			}
		if(buy.getOperatingDays().contains(4)) {
			wed.setSelected(true);
			}
		if(buy.getOperatingDays().contains(5)) {
			thur.setSelected(true);
			}
		if(buy.getOperatingDays().contains(6)) {
			fri.setSelected(true);
			}
		if(buy.getOperatingDays().contains(7)) {
			sat.setSelected(true);
			}
		if(buy.getOperatingDays().contains(1)) { //Sunday is 1 in Calendar.DAY_OF_WEAK
			sun.setSelected(true);
			}
		
		from.setValue(buy.getStartHour());
		to.setValue(buy.getEndHour());
		
		listView.setItems((ObservableList<String>) buy.getShoppingList());
		
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
		
		Buy buy = (Buy) this.item;

		String name = inputName.getText();
		String url = inputUrl.getText();
		String address = inputAddress.getText();
		String note = inputNote.getText();
		ArrayList<String> shoppinglist = (ArrayList<String>) listView.getItems();
		
		int startHour = from.getValue();
		int endHour = to.getValue();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFRIMATION");
		alert.setContentText("test");
		alert.setContentText("Are you sure you want to update this schedule?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			buy.setItemName(name);
			buy.setUrl(url);
			buy.setAddress(address);
			buy.setItemNote(note);
			buy.setOperatingDays(createNewOperatingDays());
			buy.setStartHour(startHour);
			buy.setEndHour(endHour);
			buy.setShoppingList(shoppinglist);
			
			database.store();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EatViewPane.fxml"));
			Parent root = loader.load();
			TripPageController tripPageController = loader.getController();
			tripPageController.setData(trip);

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
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.setScene(new Scene(root));
		}
	}
	
	//adjust shopping list
	
	@FXML
	private void addItem(ActionEvent event) {
		listView.getItems().add(input.getText());
	}
	
	@FXML
	private void deleteItem(ActionEvent event) {
		String selectedItem = listView.getSelectionModel().getSelectedItem();
		if(selectedItem != null) {
			listView.getItems().remove(selectedItem);
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
