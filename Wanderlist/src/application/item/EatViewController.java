package application.item;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import model.System.ApplicationSystem;
import model.Trip.Eat;
import model.Trip.Item;
import model.Trip.See;

public class EatViewController {
	
	ApplicationSystem database = ApplicationSystem.getInstance();

	@FXML
	private Button btn_back;
	
	@FXML
	private Label lbl_name;
	
	@FXML
	private Label lbl_url;
	
	@FXML
	private Label lbl_address;
	
	@FXML
	private Label lbl_starthour;
	
	@FXML
	private Label lbl_endhour;
	
	@FXML
	private Label lbl_note;
	
	@FXML
	private Label lbl_operatingDays;
	
	@FXML
	private CheckBox cb_reservation;
	
	@FXML
	private Button btn_edit;
	
	
	public void SetItemDetails(Item item)
	{
		//Eat eatItem = (Eat) item;
		
		lbl_name.setText(item.getItemName());
		lbl_url.setText(item.getUrl());
		lbl_address.setText(item.getAddress());
		lbl_note.setText(item.getItemNote());
		lbl_starthour.setText(((Eat) item).getStartHour().toString());
		lbl_endhour.setText(((Eat) item).getEndHour().toString());
		//lbl_operatingdays.setText(operatingDaysToString(((Eat) item).getOperatingDays()));
		List<String> daysOfWeekStrings = convertOperatingDaysToStrings(((Eat) item).getOperatingDays());
		lbl_operatingDays.setText(String.join(", ", daysOfWeekStrings));
		cb_reservation.setSelected(((Eat) item).isReservation());
	}
	
	private List<String> convertOperatingDaysToStrings(List<Integer> operatingDays) {
	    List<String> daysOfWeek = new ArrayList<>();
	    for (Integer day : operatingDays) {
	        switch (day) {
	            case 1:
	                daysOfWeek.add("Monday");
	                break;
	            case 2:
	                daysOfWeek.add("Tuesday");
	                break;
	            case 3:
	                daysOfWeek.add("Wednesday");
	                break;
	            case 4:
	                daysOfWeek.add("Thursday");
	                break;
	            case 5:
	                daysOfWeek.add("Friday");
	                break;
	            case 6:
	                daysOfWeek.add("Saturday");
	                break;
	            case 7:
	                daysOfWeek.add("Sunday");
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid day of week: " + day);
	        }
	    }
	    return daysOfWeek;
	}


}
