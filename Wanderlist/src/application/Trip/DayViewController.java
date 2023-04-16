package application.Trip;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
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
	@FXML
	private Button btn_up;
	@FXML
	private Button btn_down;

	private static DayViewController orgViewController;
	private static Item dragItem;

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

	// TEST FOR DAY TO DAY DRAG
	public void dragDetected(MouseEvent event) {
		Item player = lvDay.getSelectionModel().getSelectedItem();
		if (player == null) {
			return;
		}
		this.dragItem = player;
		Dragboard dragBoard = lvDay.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		content.put(Item.DATA_FORMAT, player);
		dragBoard.setContent(content);

		orgViewController = this;

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

		if (orgViewController != null) {
			// update daylist
			orgViewController.getOlDay().remove(dragItem);
			orgViewController.getLvDay().setItems(orgViewController.getOlDay());
			// update object
			orgViewController.getDay().removeItemFromSchedule(dragItem);
			day.getSchedule().add(dragItem);
			// reset reference
			orgViewController = null;
		} else {
			// update wishlist controller
			wishlistController.removeDragedOutItem();
			day.getSchedule().add(player);
		}

		// update day's schedule and save
		database.store();
	}

	public Day getDay() {
		return this.day;
	}

	public ListView<Item> getLvDay() {
		return lvDay;
	}

	public ObservableList<Item> getOlDay() {
		return olDay;
	}

	public void upAction(ActionEvent event) {
		// change data
		int selectedIndex = lvDay.getSelectionModel().getSelectedIndex();

		ArrayList<Item> schedule = day.getSchedule();
		if (selectedIndex == 0) {
			event.consume();
			return;
		}

		Collections.swap(schedule, selectedIndex, selectedIndex - 1);

		// change display

		olDay.clear();
		olDay.addAll(day.getSchedule());
		lvDay.setItems(olDay);
		lvDay.getSelectionModel().select(selectedIndex);
		lvDay.getSelectionModel().select(selectedIndex - 1);

		// save change
		database.store();
	}

	public void downAction(ActionEvent event) {
		// change data
		int selectedIndex = lvDay.getSelectionModel().getSelectedIndex();

		ArrayList<Item> schedule = day.getSchedule();
		if (selectedIndex == schedule.size() - 1) {
			event.consume();
			return;
		}

		Collections.swap(schedule, selectedIndex, selectedIndex + 1);

		// change display

		olDay.clear();
		olDay.addAll(day.getSchedule());
		lvDay.setItems(olDay);
		lvDay.getSelectionModel().select(selectedIndex);
		lvDay.getSelectionModel().select(selectedIndex + 1);

		// save change
		database.store();
	}

}
