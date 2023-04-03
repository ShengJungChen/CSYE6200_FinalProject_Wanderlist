package application;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Trip.Day;
import model.Trip.Trip;
import model.User.User;

public class Controller_Trip implements Initializable {

	@FXML
	private TextArea InputNote;

	@FXML
	private Button btnAdd;

	@FXML
	private ComboBox<Integer> cmbEndDate;

	@FXML
	private ComboBox<Integer> cmbEndMonth;

	@FXML
	private ComboBox<Integer> cmbEndYear;

	@FXML
	private ComboBox<Integer> cmbStartDate;

	@FXML
	private ComboBox<Integer> cmbStartMonth;

	@FXML
	private ComboBox<Integer> cmbStartYear;

	@FXML
	private TextField inputCity;

	@FXML
	private TextField inputCountry;

	@FXML
	private TextField inputTripName;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		// populate year cmb
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);

		ObservableList<Integer> years = FXCollections.observableArrayList();
		for (int i = thisYear - 5; i <= thisYear + 25; i++) {
			years.add(i);
		}

		cmbStartYear.setItems(years);
		cmbEndYear.setItems(years);

		// populate month cmb
		cmbStartMonth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		cmbEndMonth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
	}

	@FXML
	public void startMonthSet(ActionEvent event) {
		calculateDatesAndPopulateCmb(cmbStartYear, cmbStartMonth, cmbStartDate);
	}

	@FXML
	public void endMonthSet(ActionEvent event) {
		calculateDatesAndPopulateCmb(cmbEndYear, cmbEndMonth, cmbEndDate);
	}

	public void calculateDatesAndPopulateCmb(ComboBox<Integer> yearCmb, ComboBox<Integer> monthCmb,
			ComboBox<Integer> cmb) {

		int year = monthCmb.getValue();
		int month = monthCmb.getValue() - 1;

		Calendar cal = Calendar.getInstance();

		cal.set(year, month, 1);
		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		ObservableList<Integer> dates = FXCollections.observableArrayList();
		for (int i = 1; i <= totalDays; i++) {
			dates.add(i);
		}

		cmb.setItems(dates);
	}

	@FXML
	public void addTrip() {
		// TEST FAKE USER, NEED TO CHANGE LATER
		User user = new User("email", "pw");

		// NEED TO VALIDATE FIELDS
		String tripName = inputTripName.getText();
		String country = inputCountry.getText();
		String city = inputCity.getText();
		String note = InputNote.getText();

		// check if dates has input
		if (cmbStartYear.getSelectionModel().isEmpty() || cmbStartMonth.getSelectionModel().isEmpty()
				|| cmbStartDate.getSelectionModel().isEmpty() || cmbEndYear.getSelectionModel().isEmpty()
				|| cmbEndMonth.getSelectionModel().isEmpty() || cmbEndDate.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("INVALID");
			alert.setHeaderText("Invalid Trip Date");
			alert.setContentText("Trip date cannot be empty.");
			alert.showAndWait();
		} else {
			int startYear = cmbStartYear.getValue();
			int startMonth = cmbStartMonth.getValue();
			int startDate = cmbStartDate.getValue();
			int endYear = cmbEndYear.getValue();
			int endMonth = cmbEndMonth.getValue();
			int endDate = cmbEndDate.getValue();

			Calendar start = Calendar.getInstance();

			start.set(startYear, startMonth - 1, startDate, 0, 0, 0);
			start.set(Calendar.MILLISECOND, 0);

			Calendar end = Calendar.getInstance();

			end.set(endYear, endMonth - 1, endDate, 0, 0, 0);
			end.set(Calendar.MILLISECOND, 0);

			// check if start date is before end date
			if (start.after(end) || start.equals(end)) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("INVALID");
				alert.setHeaderText("Invalid Trip Value");
				alert.setContentText("Trip start time has to be before end time.");
				alert.showAndWait();

			} else {
				user.getTrips().addNewTrip(tripName, country, city, startYear, startMonth, startDate, endYear, endMonth,
						endDate, note);

				// TEST PRINT
				Trip trip = user.getTrips().getTrips().get(0);

				System.out.println(trip.getTripName() + trip.getCountry() + trip.getCity() + trip.getNote());
				for (Day d : trip.getDays().getDays()) {
					System.out.println(d.getDate().getTime());
				}

				// display create message
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("SUCCESS");
				alert.setHeaderText("New Trip Created");
				alert.setContentText("Head back to dashboard to view all your trips.");
				alert.showAndWait();
			}
		}
	}
}
