package application.item;

import java.io.IOException;
import java.util.ArrayList;

import application.Dashboard.DashboardController;
import application.Trip.TripPageController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Buy;
import model.Trip.Eat;
import model.Trip.Item;
import model.Trip.Item.Type;
import model.Trip.Play;
import model.Trip.See;
import model.Trip.Trip;
import model.User.User;

public class AddNewItemController extends Application {
	
	ApplicationSystem database = ApplicationSystem.getInstance();
	
	private Trip trip;
	
	private buyPane_Controller buyController;
	private eatPane_Controller eatController;
	private playPane_Controller playController;
	private seePane_Controller seeController;
	
	// button
	@FXML	private Button btn_back;
	@FXML	private Button btn_cancel;
	@FXML	private Button btn_save;
	
	// textfield
	@FXML	private TextField name_input;
	@FXML	private TextField url_input;
	@FXML	private TextField address_input;
	@FXML	private BorderPane rootPane;
	@FXML	private Pane pane;
	@FXML	private TextArea note_input;
	
	// item type comboBox with 4 options
	@FXML	private ComboBox<String> typeBox;
	ObservableList<String> typeList = FXCollections.observableArrayList("Eat", "Buy", "Play", "See");
	
	//Type change: diff view & set diff controller
	@FXML private void onTypeBoxChanged() throws IOException {
		if(typeBox.getValue().equals("Eat")) {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddNewItem_eatPane.fxml"));
			
			Pane typepane = loader.load();
			pane.getChildren().setAll(typepane);
			
			this.eatController = loader.getController();
			
			
			
		} else if (typeBox.getValue().equals("Buy")) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddNewItem_buyPane.fxml"));
			
			Pane typepane = loader.load();
			pane.getChildren().setAll(typepane);
			
			this.buyController = loader.getController();
			
		}
		else if (typeBox.getValue().equals("Play")) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddNewItem_playPane.fxml"));
			
			Pane typepane = loader.load();
			pane.getChildren().setAll(typepane);
			
			this.playController = loader.getController();
		}
		else if (typeBox.getValue().equals("See")) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddNewItem_seePane.fxml"));
			
			Pane typepane = loader.load();
			pane.getChildren().setAll(typepane);
			
			this.seeController = loader.getController();
		}
	}
	
	public void setTrip(Trip trip) {
		this.trip = trip;
		String tripname = trip.getTripName();
	}
	
	public void addScheduleItem(ActionEvent event, Trip trip) throws IOException {
		// get data
		String itemName = name_input.getText();
		String url = url_input.getText();
		String address = address_input.getText();
		String itemNote = note_input.getText();
		String itemType = typeBox.getValue();

		if(typeBox.getValue().equals("Eat")) {
			Type type = Type.Eat;
			ArrayList<Integer> operatingDays = new ArrayList<Integer>();
			if(eatController.getMon().isSelected()) {
				operatingDays.add(1);
			}
			if(eatController.getTue().isSelected()) {
				operatingDays.add(2);
			}
			if(eatController.getWed().isSelected()) {
				operatingDays.add(3);
			}
			if(eatController.getThur().isSelected()) {
				operatingDays.add(4);
			}
			if(eatController.getFri().isSelected()) {
				operatingDays.add(5);
			}
			if(eatController.getSat().isSelected()) {
				operatingDays.add(6);
			}
			if(eatController.getSun().isSelected()) {
				operatingDays.add(7);
			}
			
			int startHour = eatController.getFrom().getValue();
			int endHour = eatController.getTo().getValue();
			boolean reservation = eatController.getReservation().isSelected();
			
			//set data into eatItem
			
			Eat eat = (Eat) trip.getWishlist().addItem(type, itemName);
			eat.setItemName(itemName);
			eat.setUrl(url);
			eat.setAddress(address);
			eat.setItemNote(itemNote);
			eat.setOperatingDays(operatingDays);
			eat.setStartHour(startHour);
			eat.setEndHour(endHour);
			eat.setReservation(reservation);
			
		} 
		else if (typeBox.getValue().equals("Buy")) {
			Type type = Type.Buy;
			ArrayList<Integer> operatingDays = new ArrayList<Integer>();
				if(buyController.getMon().isSelected()) {
					operatingDays.add(1);
				}
				if(buyController.getTue().isSelected()) {
					operatingDays.add(2);
				}
				if(buyController.getWed().isSelected()) {
					operatingDays.add(3);
				}
				if(buyController.getThur().isSelected()) {
					operatingDays.add(4);
				}
				if(buyController.getFri().isSelected()) {
					operatingDays.add(5);
				}
				if(buyController.getSat().isSelected()) {
					operatingDays.add(6);
				}
				if(buyController.getSun().isSelected()) {
					operatingDays.add(7);
				}
				
				int startHour = buyController.getFrom().getValue();
				int endHour = buyController.getTo().getValue();
				
				ObservableList<String> fxmlList = buyController.getListView().getItems();
				ArrayList<String> shoppingList = new ArrayList<>(fxmlList);
				
				//set data into buyItem
				
				Buy buy = (Buy)trip.getWishlist().addItem(type, itemName);
				buy.setItemName(itemName);
				buy.setUrl(url);
				buy.setAddress(address);
				buy.setItemNote(itemNote);
				buy.setOperatingDays(operatingDays);
				buy.setStartHour(startHour);
				buy.setEndHour(endHour);
				buy.setShoppingList(shoppingList);
		}
		else if (typeBox.getValue().equals("Play")) {
			Type type = Type.Play;
			ArrayList<Integer> operatingDays = new ArrayList<Integer>();
			if(playController.getMon().isSelected()) {
				operatingDays.add(1);
			}
			if(playController.getTue().isSelected()) {
				operatingDays.add(2);
			}
			if(playController.getWed().isSelected()) {
				operatingDays.add(3);
			}
			if(playController.getThur().isSelected()) {
				operatingDays.add(4);
			}
			if(playController.getFri().isSelected()) {
				operatingDays.add(5);
			}
			if(playController.getSat().isSelected()) {
				operatingDays.add(6);
			}
			if(playController.getSun().isSelected()) {
				operatingDays.add(7);
			}
			
			int startHour = playController.getFrom().getValue();
			int endHour = playController.getTo().getValue();
			boolean ticket = playController.getTicket().isSelected();
			double price = 0;
			price = Double.parseDouble(playController.getPrice().getText());
			
			//set data into eatItem
			Play play = (Play) trip.getWishlist().addItem(type, itemName);
			play.setItemName(itemName);
			play.setUrl(url);
			play.setAddress(address);
			play.setItemNote(itemNote);
			play.setOperatingDays(operatingDays);
			play.setStartHour(startHour);
			play.setEndHour(endHour);
			play.setTicket(ticket);
			play.setPrice(price);
			
		}
		else if (typeBox.getValue().equals("See")) {
			Type type = Type.See;
			String mean = seeController.getMean().getText();
			//set data into seeItem
			See see = (See) trip.getWishlist().addItem(type, itemName);
			see.setItemName(itemName);
			see.setUrl(url);
			see.setAddress(address);
			see.setItemNote(itemNote);
			see.setTrafficMean(mean);
		}

		//save changes
		database.store();
			}
	
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AddNewItemPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// cannot add the set method of button
	}

	@FXML
	private void initialize() throws IOException {
	
		typeBox.setItems(typeList);
		typeBox.setOnAction(event -> {
			try {
				onTypeBoxChanged();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	
	
	
	
	@FXML
	public void saveButtonAction(ActionEvent event) throws IOException {
		//check
		String name = name_input.getText();
		String type = typeBox.getValue();
		
		if(name.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please enter the schedule name.");
			alert.showAndWait();
			event.consume();
		}
		if(type == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please select a type.");
			alert.showAndWait();
			event.consume();
		}
		if (!name.isEmpty() && type != null ) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Success");
			alert.setContentText("Your schedule was saved.");
			alert.showAndWait();
			event.consume();
		}
		
		//add item to trip
		addScheduleItem(event, trip);
		System.out.println("success");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));
		Parent root = loader.load();
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

		Stage stage = (Stage) btn_save.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
	
	@FXML
	private void backButtonAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));
		Parent root = loader.load();
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

		Stage stage = (Stage) btn_back.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
	
	@FXML
	private void cancelButtonAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));
		Parent root = loader.load();
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

		Stage stage = (Stage) btn_cancel.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

}
