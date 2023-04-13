package application.item;

import java.io.IOException;
import java.util.ArrayList;

import application.Trip.TripPageController;
import application.Trip.WishlistController;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class ViewItemController extends Application {

	ApplicationSystem database = ApplicationSystem.getInstance();

	private Trip trip;
	private Item item;
	private Eat eat;
	private Buy buy;
	private Play play;
	private See see;
	private Type type;
	private buyPane_Controller buyController;
	private eatPane_Controller eatController;
	private playPane_Controller playController;
	private seePane_Controller seeController;

	// button
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_edit;
	@FXML
	private Button btn_cancel;
	@FXML
	private Button btn_save;

	// text
	@FXML
	private Label itemName;
	@FXML
	private Label itemType;
	@FXML
	private Label itemUrl;
	@FXML
	private Label itemAddress;
	@FXML
	private TextArea itemNote;

	// pane
	@FXML
	private BorderPane rootPane;
	@FXML
	private VBox vBox;
	@FXML
	private Pane firstPane;
	@FXML
	private GridPane gridPane;
	@FXML
	private Pane secondPane;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("ViewItemPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	private void initialize() throws IOException {

	}

	@FXML
	private void onTypeBoxChanged() throws IOException {
//		if (typeBox.getValue().equals("Eat")) {
//			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_eatPane.fxml"));
//			secondPane.getChildren().setAll(eatpane);
//		} else if (typeBox.getValue().equals("Buy")) {
//			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_buyPane.fxml"));
//			secondPane.getChildren().setAll(eatpane);
//		} else if (typeBox.getValue().equals("Play")) {
//			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_playPane.fxml"));
//			secondPane.getChildren().setAll(eatpane);
//		} else if (typeBox.getValue().equals("See")) {
//			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_seePane.fxml"));
//			secondPane.getChildren().setAll(eatpane);
//		}
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public void setData(Item item) throws IOException {
		this.item = item;
		itemType.setText(item.getType());

		if (item.getType() == "Eat") {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("AddNewItem_eatPane.fxml"));
			Pane pane = fxmlLoader.load();
			this.eatController = fxmlLoader.getController();
			secondPane.getChildren().add(pane);
			setEatItem(this.eat);
			
		} 
		else if (item.getType() == "Buy") {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("AddNewItem_buyPane.fxml"));
			Pane pane = fxmlLoader.load();
			this.buyController = fxmlLoader.getController();
			secondPane.getChildren().add(pane);
			setBuyItem(this.buy);
		}
		else if (item.getType() == "Play") {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("AddNewItem_playPane.fxml"));
			Pane pane = fxmlLoader.load();
			this.playController = fxmlLoader.getController();
			secondPane.getChildren().add(pane);
			setPlayItem(play);
		}
		else if (item.getType() == "See") {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("AddNewItem_seePane.fxml"));
			Pane pane = fxmlLoader.load();
			this.seeController = fxmlLoader.getController();
			secondPane.getChildren().add(pane);
			setSeeItem(see);
		}
		
	}
	
	public void setEatItem(Eat eat)  throws IOException{
		this.eat = eat;
		itemName.setText(eat.getItemName());
		itemUrl.setText(eat.getUrl());
		itemAddress.setText(eat.getAddress());
		itemNote.setText(eat.getItemNote());
		
		if(eat.getOperatingDays().contains(1)) {
			eatController.getMon().setSelected(true);
			}
		if(eat.getOperatingDays().contains(2)) {
			eatController.getTue().setSelected(true);
			}
		if(eat.getOperatingDays().contains(3)) {
			eatController.getWed().setSelected(true);
			}
		if(eat.getOperatingDays().contains(4)) {
			eatController.getThur().setSelected(true);
			}
		if(eat.getOperatingDays().contains(5)) {
			eatController.getFri().setSelected(true);
			}
		if(eat.getOperatingDays().contains(6)) {
			eatController.getSat().setSelected(true);
			}
		if(eat.getOperatingDays().contains(7)) {
			eatController.getSun().setSelected(true);
			}
		
		eatController.getFrom().setValue(eat.getStartHour());
		eatController.getTo().setValue(eat.getEndHour());
		eatController.getReservation().setSelected(eat.isReservation());
		
		eatController.getMon().setDisable(true);
		eatController.getTue().setDisable(true);
		eatController.getWed().setDisable(true);
		eatController.getThur().setDisable(true);
		eatController.getFri().setDisable(true);
		eatController.getSat().setDisable(true);
		eatController.getSun().setDisable(true);
		eatController.getFrom().setDisable(true);
		eatController.getTo().setDisable(true);
		eatController.getReservation().setDisable(true);
	}

	public void setBuyItem(Buy buy)  throws IOException{
		this.buy = buy;
		itemName.setText(buy.getItemName());
		itemUrl.setText(buy.getUrl());
		itemAddress.setText(buy.getAddress());
		itemNote.setText(buy.getItemNote());
		if(buy.getOperatingDays().contains(1)) {
			buyController.getMon().setSelected(true);
			}
		if(buy.getOperatingDays().contains(2)) {
			buyController.getTue().setSelected(true);
			}
		if(buy.getOperatingDays().contains(3)) {
			buyController.getWed().setSelected(true);
			}
		if(buy.getOperatingDays().contains(4)) {
			buyController.getThur().setSelected(true);
			}
		if(buy.getOperatingDays().contains(5)) {
			buyController.getFri().setSelected(true);
			}
		if(buy.getOperatingDays().contains(6)) {
			buyController.getSat().setSelected(true);
			}
		if(buy.getOperatingDays().contains(7)) {
			buyController.getSun().setSelected(true);
			}
		
		buyController.getFrom().setValue(buy.getStartHour());
		buyController.getTo().setValue(buy.getEndHour());
		buyController.getListView().setItems((ObservableList<String>) buy.getShoppingList());
		
		buyController.getMon().setDisable(true);
		buyController.getTue().setDisable(true);
		buyController.getWed().setDisable(true);
		buyController.getThur().setDisable(true);
		buyController.getFri().setDisable(true);
		buyController.getSat().setDisable(true);
		buyController.getSun().setDisable(true);
		buyController.getFrom().setDisable(true);
		buyController.getTo().setDisable(true);
		buyController.getListView().setDisable(true);
		buyController.getBtn_add().setVisible(false);
		buyController.getBtn_delete().setVisible(false);

	}
	
	public void setPlayItem(Play play)  throws IOException{
		this.play = play;
		itemName.setText(play.getItemName());
		itemUrl.setText(play.getUrl());
		itemAddress.setText(play.getAddress());
		itemNote.setText(play.getItemNote());
		if(play.getOperatingDays().contains(1)) {
			playController.getMon().setSelected(true);
			}
		if(play.getOperatingDays().contains(2)) {
			playController.getTue().setSelected(true);
			}
		if(play.getOperatingDays().contains(3)) {
			playController.getWed().setSelected(true);
			}
		if(play.getOperatingDays().contains(4)) {
			playController.getThur().setSelected(true);
			}
		if(play.getOperatingDays().contains(5)) {
			playController.getFri().setSelected(true);
			}
		if(play.getOperatingDays().contains(6)) {
			playController.getSat().setSelected(true);
			}
		if(play.getOperatingDays().contains(7)) {
			playController.getSun().setSelected(true);
			}
		
		playController.getFrom().setValue(play.getStartHour());
		playController.getTo().setValue(play.getEndHour());
		
		
		playController.getMon().setDisable(true);
		playController.getTue().setDisable(true);
		playController.getWed().setDisable(true);
		playController.getThur().setDisable(true);
		playController.getFri().setDisable(true);
		playController.getSat().setDisable(true);
		playController.getSun().setDisable(true);
		playController.getFrom().setDisable(true);
		playController.getTo().setDisable(true);
		playController.getTicket().setDisable(true);
		playController.getPrice().setDisable(true);

	}
	
	public void setSeeItem(See see)  throws IOException{
		this.see = see;
		itemName.setText(see.getItemName());
		itemUrl.setText(see.getUrl());
		itemAddress.setText(see.getAddress());
		itemNote.setText(see.getItemNote());
		seeController.getMean().setText(see.getTrafficMean());
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
	private void editButtonAction(ActionEvent event) throws IOException {
		btn_edit.setDisable(true);
		btn_cancel.setDisable(false);
		btn_save.setDisable(false);
		Pane defaultPane = FXMLLoader.load(getClass().getResource("AddNewItem_defaultPane.fxml"));
		vBox.getChildren().setAll(defaultPane);
	}

	@FXML
	private void cancelButtonAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Changes you made will not be saved.");
		alert.showAndWait();
		btn_edit.setDisable(false);
		btn_cancel.setDisable(true);
		btn_save.setDisable(true);
		vBox.getChildren().setAll(firstPane, secondPane);
	}

	@FXML
	private void saveButtonAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("All changes saved.");
		alert.showAndWait();
		btn_edit.setDisable(false);
		btn_cancel.setDisable(true);
		btn_save.setDisable(true);
	}

}
