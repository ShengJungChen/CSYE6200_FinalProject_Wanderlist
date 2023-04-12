package application.Trip;

import java.io.IOException;

import application.item.AddNewItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WishlistController {

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

//		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/item/AddNewItemPage.fxml"));
//
//		Parent root = loader.load();
//		AddNewItemController addNewItemController = loader.getController();
//		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		stage.setScene(new Scene(root));
//		
		//
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/item/AddNewItemPage.fxml"));

		Parent root = loader.load();
		AddNewItemController addNewItemController = loader.getController();

		Stage stage = (Stage) btnNewItem.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
}
