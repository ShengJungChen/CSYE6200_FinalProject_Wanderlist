package application.Trip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import application.item.BuyViewController;
import application.item.EatViewController;
import application.item.PlayViewController;
import application.item.SeeViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Buy;
import model.Trip.Day;
import model.Trip.Eat;
import model.Trip.Item;
import model.Trip.Play;
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

			String itemType = selectedItem.getType();

			try {
				FXMLLoader loader = new FXMLLoader();
				Parent root;
				switch (itemType) {
				case "Eat":
					loader.setLocation(getClass().getResource("../../application/item/EatViewPane.fxml"));
					break;
				case "Play":
					loader.setLocation(getClass().getResource("../../application/item/PlayViewPane.fxml"));
					break;
				case "See":
					loader.setLocation(getClass().getResource("../../application/item/SeeViewPane.fxml"));
					break;
				case "Buy":
					loader.setLocation(getClass().getResource("../../application/item/BuyViewPane.fxml"));
					break;
				default:
					throw new IllegalStateException("Invalid item type: " + itemType);
				}
				root = loader.load();

				// Get the controller instance and call setItem method
				Object controller = loader.getController();
				if (controller instanceof SeeViewController) {
					((SeeViewController) controller).SetItemDetails(selectedItem, trip);
				} else if (controller instanceof EatViewController) {
					((EatViewController) controller).SetItemDetails(selectedItem, trip);
				} else if (controller instanceof PlayViewController) {
					((PlayViewController) controller).SetItemDetails(selectedItem, trip);
				} else if (controller instanceof BuyViewController) {
					((BuyViewController) controller).SetItemDetails(selectedItem, trip);
				}
				Scene scene = new Scene(root);
				Stage stage = (Stage) lbDate.getScene().getWindow();

				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

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

		// if drag and drop at same day, cancel event
		if (orgViewController == this) {
			orgViewController = null;
			return;
		}

		boolean canDrop = true;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day.getDate());

		int weekdayInt = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("THIS DAY" + weekdayInt);

		Item player = (Item) dragEvent.getDragboard().getContent(Item.DATA_FORMAT);

		// check operating hours
		switch (player.getType()) {
		case "Eat":
			Eat eat = (Eat) player;
			if (!eat.getOperatingDays().contains(weekdayInt))
				canDrop = false;
			break;
		case "Play":
			Play play = (Play) player;
			if (!play.getOperatingDays().contains(weekdayInt))
				canDrop = false;
			break;
		case "Buy":
			Buy buy = (Buy) player;
			if (!buy.getOperatingDays().contains(weekdayInt))
				canDrop = false;
			break;
		}

		if (canDrop == false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Cannot schedule to this day");
			alert.setContentText("Location is not operating on this day. Please choose another day.");
			alert.showAndWait();
			canDrop = true;
			dragEvent.consume();
			return;
		}

		if (orgViewController != null) {
			// dragged from other day

			// update new daylist
			olDay.addAll(dragItem);
			lvDay.setItems(olDay);
			dragEvent.setDropCompleted(true);

			// update old daylist
			orgViewController.getOlDay().remove(dragItem);
			orgViewController.getLvDay().setItems(orgViewController.getOlDay());

			// update object
			orgViewController.getDay().removeItemFromSchedule(dragItem);
			day.getSchedule().add(dragItem);
			// reset reference

			orgViewController = null;
			dragItem = null;
		} else {
			// dragged from wishlist

			// update new daylist
			olDay.addAll(player);
			lvDay.setItems(olDay);
			dragEvent.setDropCompleted(true);

			// update wishlist controller
			wishlistController.removeDragedOutItem();
			day.getSchedule().add(player);
		}

		// save change
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
		if (selectedIndex <= 0) {
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
		if (selectedIndex == schedule.size() - 1 || selectedIndex == -1) {
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
