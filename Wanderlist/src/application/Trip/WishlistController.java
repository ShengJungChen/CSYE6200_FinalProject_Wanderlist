package application.Trip;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.item.AddNewItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Day;
import model.Trip.Item;
import model.Trip.Trip;

public class WishlistController {

	ApplicationSystem database = ApplicationSystem.getInstance();

	Trip trip;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnNewItem;

	@FXML
	private Button btnView;

	@FXML
	private HBox dayHolder;

	@FXML
	private ListView<Item> lvWishlist;

	@FXML
	public void newItemAction(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/item/AddNewItemPage.fxml"));

		Parent root = loader.load();
		AddNewItemController addNewItemController = loader.getController();

		Stage stage = (Stage) btnNewItem.getScene().getWindow();
		stage.setScene(new Scene(root));

	}

	// item
	public void setTrip(Trip trip) {
		this.trip = trip;
		// JUST FOR TEST
		// System.out.println("TRIP" + this.trip.getTripName());

		// Clear any existing items in the ListView
		lvWishlist.getItems().clear();
		for (Item item : this.trip.getWishlist().getWishList()) {
			lvWishlist.getItems().add(item);
		}

		populateDayList();
	}

	// delete a item
	@FXML
	public void deleteSelectedItem() {
		Item selectedItem = lvWishlist.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			// remove item from listview
			lvWishlist.getItems().remove(selectedItem);

			// remove item from wishlist
			trip.getWishlist().removeItemFromWishlist(selectedItem);
		} else {
			// if no item is selected
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No item selected");
			alert.setContentText("Please select an item to delete");
			alert.showAndWait();
		}
	}

	public void populateDayList() {

		dayHolder.getChildren().clear();

		ArrayList<Day> dayList = trip.getDays().getDays();

		for (int i = 0; i < dayList.size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("DayView.fxml"));

			try {
				VBox vBox = fxmlLoader.load();
				DayViewController dayViewController = fxmlLoader.getController();

				// get date string
				Date date = dayList.get(i).getDate();
				String pattern = "yyyy/MM/dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String dateString = simpleDateFormat.format(date);

				// set
				dayViewController.setData(dateString, dayList.get(i).getWeekDay());

				dayHolder.getChildren().add(vBox);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
