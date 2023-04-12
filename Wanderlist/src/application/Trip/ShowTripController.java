package application.Trip;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Trip.Trip;

public class ShowTripController implements Initializable {

	@FXML
	private Label textTripCity;

	@FXML
	private Label textTripCountry;

	@FXML
	private Label textTripDate;

	@FXML
	private Label textTripName;

	@FXML
	private Label textTripNote;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setData(Trip trip) {
		textTripName.setText(trip.getTripName());
		textTripCity.setText(trip.getCity());
		textTripCountry.setText(trip.getCountry());
		textTripNote.setText(trip.getNote());

		// set date
		String pattern = "yyyy/MM/dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String start = simpleDateFormat.format(trip.getStartDate().getTime());
		String end = simpleDateFormat.format(trip.getEndDate().getTime());

		textTripDate.setText(start + " - " + end);

	}

}
