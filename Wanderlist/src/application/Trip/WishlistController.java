package application.Trip;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import application.item.ViewItemController;
import application.item.AddNewItemController;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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

	private ObservableList<Item> olWishlist = FXCollections.observableArrayList();

	Item dragItem;

	// item
	public void setTrip(Trip trip) {
		this.trip = trip;

		populateWishlist();
		populateDayList();
	}

	public void populateWishlist() {
		// clear any existing items in the ListView
		lvWishlist.getItems().clear();

		olWishlist.addAll(this.trip.getWishlist().getWishList());
		lvWishlist.setItems(olWishlist);

	}
	//view action, ��type->page

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
				Day day = dayList.get(i);
				Date date = day.getDate();
				String pattern = "yyyy/MM/dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String dateString = simpleDateFormat.format(date);

				// set
				dayViewController.setData(this, day, dateString, dayList.get(i).getWeekDay());
				dayHolder.getChildren().add(vBox);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void newItemAction(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/item/AddNewItemPage.fxml"));

		Parent root = loader.load();
		AddNewItemController addNewItemController = loader.getController();
		addNewItemController.setTrip(this.trip);

		Stage stage = (Stage) btnNewItem.getScene().getWindow();
		stage.setScene(new Scene(root));

	}
	
	//view item
	@FXML
	public void handleViewButtonClick()
	{
		Item selectedItem = lvWishlist.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
            navigateToItemView(selectedItem);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No item selected");
			alert.setContentText("Please select an item to view");
			alert.showAndWait();
		}
	}
	
	public void navigateToItemView(Item item) {
			String itemType = item.getType();
			
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
	                ((SeeViewController) controller).SetItemDetails(item,trip);
	            } else if (controller instanceof EatViewController) {
	                ((EatViewController) controller).SetItemDetails(item, trip);
	            } else if (controller instanceof PlayViewController) {
	                ((PlayViewController) controller).SetItemDetails(item,trip);
	            } else if (controller instanceof BuyViewController) {
	                ((BuyViewController) controller).SetItemDetails(item,trip);
	            }
	            Scene scene = new Scene(root);
	            Stage stage = (Stage) btnDelete.getScene().getWindow();
	            
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
			
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/item/ViewItemPage.fxml"));
//		Parent root = loader.load();
//		ViewItemController viewItemController = loader.getController();
//		viewItemController.setData(trip, selectedItem);
//
//		Stage stage = (Stage) btnView.getScene().getWindow();
//		stage.setScene(new Scene(root));
//		
//		} else {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error");
//			alert.setHeaderText("No item selected");
//			alert.setContentText("Please select an item to view");
//			alert.showAndWait();
//		}
//	}

	// delete a item
	@FXML
	public void deleteSelectedItem(ActionEvent event) {
		Item selectedItem = lvWishlist.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {

			// delete alert
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFRIMATION");
			alert.setHeaderText("Are you sure you want to delete this item?");
			alert.setContentText("This change cannot be recovered.");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				// remove item from listview
				lvWishlist.getItems().remove(selectedItem);
				// remove item from wishlist and save
				trip.getWishlist().removeItemFromWishlist(selectedItem);
				database.store();
			} else {
				event.consume();
			}

		} else {
			// if no item is selected
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No item selected");
			alert.setContentText("Please select an item to delete");
			alert.showAndWait();
		}
	}

	public void dragDetected(MouseEvent event) {
		Item player = lvWishlist.getSelectionModel().getSelectedItem();
		if (player == null) {
			return;
		}
		this.dragItem = player;
		Dragboard dragBoard = lvWishlist.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		content.put(Item.DATA_FORMAT, player);
		dragBoard.setContent(content);
	}

	public ListView<Item> getLvWishlist() {
		return lvWishlist;
	}

	public void removeDragedOutItem() {
		olWishlist.remove(dragItem);
		lvWishlist.setItems(olWishlist);
		// update object
		trip.getWishlist().removeItemFromWishlist(dragItem);
	}

	public void setLvWishlist(ListView<Item> lvWishlist) {
		this.lvWishlist = lvWishlist;
	}

	public ObservableList<Item> getOlWisList() {
		return olWishlist;
	}

	public void setOlWisList(ObservableList<Item> olWisList) {
		this.olWishlist = olWisList;
	}

	public Trip getTrip() {
		return trip;
	}

}
