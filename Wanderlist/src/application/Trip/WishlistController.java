package application.Trip;

import java.io.IOException;

import application.item.AddNewItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
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
	private ListView<String> lvWishlist;

	@FXML
	public void newItemAction(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/item/AddNewItemPage.fxml"));

		Parent root = loader.load();
		AddNewItemController addNewItemController = loader.getController();

		Stage stage = (Stage) btnNewItem.getScene().getWindow();
		stage.setScene(new Scene(root));

	}
	

	

	//add new item
	public void setTrip(Trip trip) {
		this.trip = trip;
		// JUST FOR TEST
		//System.out.println("TRIP" + this.trip.getTripName());
		
		// Clear any existing items in the ListView
		lvWishlist.getItems().clear();
	    for (Item item : this.trip.getWishlist().getWishList()) {
	        lvWishlist.getItems().add(item.getItemName());
	    }
	}
	 //delete a item
	@FXML
	public void deleteSelectedItem() {
	    String selectedItem = lvWishlist.getSelectionModel().getSelectedItem();
	    if (selectedItem != null) {
	    	//remove item from listview
	        lvWishlist.getItems().remove(selectedItem);
	        
	        //remove item from wishlist
	        for (Item item : trip.getWishlist().getWishList()) {
	            if (item.getItemName().equals(selectedItem)) {
	                trip.getWishlist().removeItemFromWishlist(item);
	                database.store();
	                break;
	            }
	        }
	    }else {
	    	// if no item is selected
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("No item selected");
	        alert.setContentText("Please select an item to delete");
	        alert.showAndWait();
	    }
	}
}
