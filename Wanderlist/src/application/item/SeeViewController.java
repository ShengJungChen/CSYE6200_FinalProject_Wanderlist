package application.item;

import java.io.IOException;

import application.Trip.TripPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Buy;
import model.Trip.Eat;
import model.Trip.Item;
import model.Trip.Play;
import model.Trip.See;
import model.Trip.Trip;
import model.Trip.Item.Type;

public class SeeViewController {
	private Trip trip;
	private Item item;
	
	ApplicationSystem database = ApplicationSystem.getInstance();

	@FXML
	private Button btnBack;
	
	@FXML
	private Label lbl_name;
	
	@FXML
	private Label lbl_url;
	
	@FXML
	private Label lbl_address;
	
	@FXML
	private Label lbl_mean;
	
	@FXML
	private Label lbl_note;
	
	
	@FXML
	private Button btn_edit;
	
	
	public void SetItemDetails(Item item, Trip trip)
	{
		this.item = item;
		this.trip = trip;

		lbl_name.setText(item.getItemName());
		lbl_url.setText(item.getUrl());
		lbl_address.setText(item.getAddress());
		lbl_note.setText(item.getItemNote());
		lbl_mean.setText(((See) item).getTrafficMean());
	}
	
	@FXML
	private void editItemAction (ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditItem_see.fxml"));
		Parent root = loader.load();
		EditItemController_see editSee = loader.getController();
		editSee.setData(trip, item);
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
	
	@FXML
	public void backAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));

		Parent root = loader.load();
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
}
	
	
	
