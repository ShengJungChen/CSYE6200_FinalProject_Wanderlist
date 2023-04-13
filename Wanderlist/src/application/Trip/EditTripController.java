package application.Trip;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Dashboard.DashboardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Trip;
import model.User.User;

public class EditTripController implements Initializable {

	ApplicationSystem database = ApplicationSystem.getInstance();

	Trip trip;

	@FXML
	private TextArea InputNote;

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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		// populate year cmb
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);

		ObservableList<Integer> years = FXCollections.observableArrayList();
		for (int i = 1900; i <= thisYear + 25; i++) {
			years.add(i);
		}

		cmbStartYear.setItems(years);
		cmbStartYear.getSelectionModel().select(thisYear - 1900);
		cmbEndYear.setItems(years);
		cmbEndYear.getSelectionModel().select(thisYear - 1900);

		// populate month cmb
		cmbStartMonth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		cmbEndMonth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

	}

	public void setData(Trip trip) {
		this.trip = trip;
		inputTripName.setText(trip.getTripName());
		inputCity.setText(trip.getCity());
		inputCountry.setText(trip.getCountry());
		InputNote.setText(trip.getNote());

		// set start month
		int startYear = trip.getStartDate().getYear();
		int startMonth = trip.getStartDate().getMonth();
		int startDate = trip.getStartDate().getDate();

		cmbStartYear.getSelectionModel().select(startYear);
		cmbStartMonth.getSelectionModel().select(startMonth);

		calculateDatesAndPopulateCmb(cmbStartYear, cmbStartMonth, cmbStartDate);
		cmbStartDate.getSelectionModel().select(startDate - 1);

		// set end month
		int endYear = trip.getEndDate().getYear();
		int endMonth = trip.getEndDate().getMonth();
		int endDate = trip.getEndDate().getDate();

		cmbEndYear.getSelectionModel().select(endYear);
		cmbEndMonth.getSelectionModel().select(endMonth);

		calculateDatesAndPopulateCmb(cmbEndYear, cmbEndMonth, cmbEndDate);
		cmbEndDate.getSelectionModel().select(endDate - 1);

	}

	@FXML
	void startMonthSet(ActionEvent event) {

		if (cmbStartYear.getValue() == null) {
			event.consume();
		} else {
			calculateDatesAndPopulateCmb(cmbStartYear, cmbStartMonth, cmbStartDate);
		}
	}

	@FXML
	void endMonthSet(ActionEvent event) {
		if (cmbEndYear.getValue() == null) {
			event.consume();
		} else {
			calculateDatesAndPopulateCmb(cmbEndYear, cmbEndMonth, cmbEndDate);
		}
	}

	@FXML
	void startYearSet(ActionEvent event) {

		if (cmbStartMonth.getValue() == null) {
			event.consume();
		} else {
			calculateDatesAndPopulateCmb(cmbStartYear, cmbStartMonth, cmbStartDate);
		}
	}

	@FXML
	void endYearSet(ActionEvent event) {

		if (cmbEndMonth.getValue() == null) {
			event.consume();
		} else {
			calculateDatesAndPopulateCmb(cmbEndYear, cmbEndMonth, cmbEndDate);
		}
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

	public void saveUpdate(ActionEvent event, Trip trip) throws IOException {

		// check if the date field is empty
		if (!validateInput()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("INVALID");
			alert.setHeaderText("Invalid Trip Date");
			alert.setContentText("Trip date cannot be empty.");
			alert.showAndWait();
			event.consume();
		} else {
			// check if date is valid
			if (!validateDate()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("INVALID");
				alert.setHeaderText("Invalid Trip Value");
				alert.setContentText("Trip start time has to be before end time.");
				alert.showAndWait();
				event.consume();
			} else {
				// get data
				String tripName = inputTripName.getText();
				String country = inputCountry.getText();
				String city = inputCity.getText();
				String note = InputNote.getText();

				int startYear = cmbStartYear.getValue();
				int startMonth = cmbStartMonth.getValue();
				int startDate = cmbStartDate.getValue();
				int endYear = cmbEndYear.getValue();
				int endMonth = cmbEndMonth.getValue();
				int endDate = cmbEndDate.getValue();

				// display confirm edit message
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFRIMATION");
				alert.setHeaderText("Are you sure you want to update this trip?");
				alert.setContentText("Old information will be overwritten.");
				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == ButtonType.OK) {
					// update trip & save to database
					trip.setTripName(tripName);
					trip.setCountry(country);
					trip.setCity(city);
					trip.setNote(note);

					// check if date has been updated
					int orgStartYear = trip.getStartDate().getYear() + 1900;
					int orgStartMonth = trip.getStartDate().getMonth() + 1;
					int orgStartDate = trip.getStartDate().getDate();
					int orgEndYear = trip.getEndDate().getYear() + 1900;
					int orgEndMonth = trip.getEndDate().getMonth() + 1;
					int orgEndDate = trip.getEndDate().getDate();

					if (orgStartYear != startYear || orgStartMonth != startMonth || orgStartDate != startDate
							|| orgEndYear != endYear || orgEndMonth != endMonth || orgEndDate != endDate) {
						trip.setStartDate(startYear, startMonth, startDate);
						trip.setEndDate(endYear, endMonth, endDate);
						trip.getDays().createDays();
					}

					database.store();

					// refresh trip page
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/TripPage.fxml"));

					Parent root = loader.load();
					TripPageController tripPageController = loader.getController();
					tripPageController.setData(trip);

					Stage stage = (Stage) inputTripName.getScene().getWindow();
					stage.setScene(new Scene(root));

				} else {
					event.consume();
				}
			}
		}
	}

	public void addNew(ActionEvent event, User user) throws IOException {

		// check if the date field is empty
		if (!validateInput()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("INVALID");
			alert.setHeaderText("Invalid Trip Date");
			alert.setContentText("Trip date cannot be empty.");
			alert.showAndWait();
			event.consume();
		} else {
			// check if date is valid
			if (!validateDate()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("INVALID");
				alert.setHeaderText("Invalid Trip Value");
				alert.setContentText("Trip start time has to be before end time.");
				alert.showAndWait();
				event.consume();
			} else {
				// get data
				String tripName = inputTripName.getText();
				String country = inputCountry.getText();
				String city = inputCity.getText();
				String note = InputNote.getText();

				int startYear = cmbStartYear.getValue();
				int startMonth = cmbStartMonth.getValue();
				int startDate = cmbStartDate.getValue();
				int endYear = cmbEndYear.getValue();
				int endMonth = cmbEndMonth.getValue();
				int endDate = cmbEndDate.getValue();

				user.getTrips().addNewTrip(tripName, country, city, startYear, startMonth, startDate, endYear, endMonth,
						endDate, note);

				// display create message
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("SUCCESS");
				alert.setHeaderText("New Trip Created");
				alert.setContentText("Head back to dashboard to view all your trips.");
				alert.showAndWait();

				// go back to dashboard
				backToDashboard(user);

				// save changes to database
				database.store();
			}
		}
	}

	public boolean validateInput() {
		// check if dates has input
		if (cmbStartYear.getSelectionModel().isEmpty() || cmbStartMonth.getSelectionModel().isEmpty()
				|| cmbStartDate.getSelectionModel().isEmpty() || cmbEndYear.getSelectionModel().isEmpty()
				|| cmbEndMonth.getSelectionModel().isEmpty() || cmbEndDate.getSelectionModel().isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean validateDate() {

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
		if (start.after(end)) {
			return false;
		} else if (start.equals(end)) {
			return true;
		} else {
			return true;
		}
	}

	public void backToDashboard(User user) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Dashboard/dashboardPage.fxml"));

		Parent root = loader.load();
		DashboardController dashboardController = loader.getController();
		dashboardController.loadPage(user);

		Stage stage = (Stage) inputTripName.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

}
