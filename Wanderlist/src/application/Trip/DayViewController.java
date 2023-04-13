package application.Trip;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import model.System.ApplicationSystem;
import model.Trip.Day;
import model.Trip.Item;
import model.Trip.Trip;

public class DayViewController {

	ApplicationSystem database = ApplicationSystem.getInstance();

	private WishlistController wishlistController;

	Trip trip;
	Day day;

	@FXML
	private ListView<Item> lvDay = new ListView<Item>();
	private ObservableList<Item> olDay = FXCollections.observableArrayList();

	private ListView<Item> lvWishlist;
	private ObservableList<Item> olWishlist;

	@FXML
	private Label lbDate;
	@FXML
	private Label lbWeekday;
	@FXML
	private Button btn_remove;
	@FXML
	private Button btn_view;

	public void setData(WishlistController wishlistController, Day day, String date, String weekday) {
		this.wishlistController = wishlistController;
		this.lvWishlist = wishlistController.getLvWishlist();
		this.olWishlist = wishlistController.getOlWisList();

		this.day = day;
		this.lbDate.setText(date);
		this.lbWeekday.setText(weekday);
		olDay.addAll(day.getSchedule());
		lvDay.setItems(olDay);
	}

	@FXML
	private void moveButtonAction(ActionEvent event) {
		Item selectedItem = lvDay.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			// return item back to wishlist
			olWishlist.add(selectedItem);
			lvWishlist.setItems(olWishlist);
			lvDay.getItems().remove(selectedItem);

			// update object and save
			wishlistController.getTrip().getWishlist().addItem(selectedItem);
			day.getSchedule().remove(selectedItem);
			database.store();
		}
	}

	@FXML
	private void viewButtonAction(ActionEvent event) {
		Item selectedItem = lvDay.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			// GO TO VIEW PAGE
		}
	}

	public void dragOver(DragEvent dragEvent) {
		if (dragEvent.getDragboard().hasContent(Item.DATA_FORMAT)) {
			dragEvent.acceptTransferModes(TransferMode.MOVE);
		}
	}

	public void dragDropped(DragEvent dragEvent) {
		Item player = (Item) dragEvent.getDragboard().getContent(Item.DATA_FORMAT);
		olDay.addAll(player);
		lvDay.setItems(olDay);
		dragEvent.setDropCompleted(true);

		// update wishlist controller
		wishlistController.removeDragedOutItem();

		// update day's schedule and save
		day.getSchedule().add(player);
		database.store();
	}

}
