package application.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.Trip.TripPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Eat;
import model.Trip.Item;
import model.Trip.Play;
import model.Trip.See;
import model.Trip.Trip;

public class PlayViewController {
	private Trip trip;
	private Item item;
	
	ApplicationSystem database = ApplicationSystem.getInstance();
	
	@FXML
	private Button btn_back;
	
	@FXML
	private Button btn_edit;
	
	@FXML
	private Label lbl_url;
	
	@FXML
	private Label lbl_name;
	
	@FXML
	private Label lbl_address;
	
	@FXML
	private Label lbl_operatingDays;
	
	@FXML
	private Label lbl_note;
	
	@FXML
	private Label lbl_price;
	
	@FXML
	private CheckBox cb_ticket;
	
	@FXML
	private Label lbl_starthour;
	
	@FXML
	private Label lbl_endhour;
	
	public void SetItemDetails(Item item, Trip trip)
	{
		this.item = item;
		this.trip = trip;
		
		lbl_name.setText(item.getItemName());
		lbl_url.setText(item.getUrl());
		lbl_address.setText(item.getAddress());
		lbl_note.setText(item.getItemNote());
		lbl_starthour.setText(((Play) item).getStartHour().toString());
		lbl_endhour.setText(((Play) item).getEndHour().toString());
		List<String> daysOfWeekStrings = convertOperatingDaysToStrings(((Play) item).getOperatingDays());
		lbl_operatingDays.setText(String.join(", ", daysOfWeekStrings));
		lbl_price.setText(((Play) item).getPrice().toString());
		cb_ticket.setSelected(((Play) item).isTicket());
	}
	
	private List<String> convertOperatingDaysToStrings(List<Integer> operatingDays) {
	    List<String> daysOfWeek = new ArrayList<>();
	    for (Integer day : operatingDays) {
	        switch (day) {
	            case 2:
	                daysOfWeek.add("Monday");
	                break;
	            case 3:
	                daysOfWeek.add("Tuesday");
	                break;
	            case 4:
	                daysOfWeek.add("Wednesday");
	                break;
	            case 5:
	                daysOfWeek.add("Thursday");
	                break;
	            case 6:
	                daysOfWeek.add("Friday");
	                break;
	            case 7:
	                daysOfWeek.add("Saturday");
	                break;
	            case 1:
	                daysOfWeek.add("Sunday");
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid day of week: " + day);
	        }
	    }
	    return daysOfWeek;
	}
	
	@FXML
	private void editItemAction (ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditItem_play.fxml"));
		Parent root = loader.load();
		EditItemController_play editPlay = loader.getController();
		editPlay.setData(trip, item);
		Stage stage = (Stage) btn_back.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
	
	@FXML
	public void backAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));

		Parent root = loader.load();
		TripPageController tripPageController = loader.getController();
		tripPageController.setData(trip);

		Stage stage = (Stage) btn_back.getScene().getWindow();
		stage.setScene(new Scene(root));
	}


}
