package application.Dashboard;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Trip.Trip;

public class TripItemController implements Initializable {

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnView;

	@FXML
	private Label lblDate;

	@FXML
	private Label lblName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void setData(Trip trip) {
		lblName.setText(trip.getTripName());

		String pattern = "yyyy/MM/dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String start = simpleDateFormat.format(trip.getStartDate().getTime());
		String end = simpleDateFormat.format(trip.getEndDate().getTime());

		lblDate.setText(start + " - " + end);
	}

}
