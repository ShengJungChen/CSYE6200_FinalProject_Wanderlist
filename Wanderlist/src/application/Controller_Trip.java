package application;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_Trip implements Initializable {

	@FXML
	private TextArea InputNote;

	@FXML
	private ComboBox<Integer> cmbEndDate;

	@FXML
	private ComboBox<Integer> cmbEndMonth;

	@FXML
	private ComboBox<Integer> cmbStartDate;

	@FXML
	private ComboBox<Integer> cmbStartMonth;

	@FXML
	private TextField inputCity;

	@FXML
	private TextField inputCountry;

	@FXML
	private TextField inputEndYear;

	@FXML
	private TextField inputStartYear;

	@FXML
	private TextField inputTripName;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// TODO Auto-generated method stub
		cmbStartMonth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		cmbEndMonth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
	}

	@FXML
	public void startMonthSet(ActionEvent event) {

		if (!inputStartYear.getText().isBlank()) {

			int year = Integer.valueOf(inputStartYear.getText());
			int month = cmbStartMonth.getValue() - 1;

			Calendar start = Calendar.getInstance();

			start.set(year, month, 1);
			int totalDays = start.getActualMaximum(Calendar.DAY_OF_MONTH);

			ObservableList<Integer> dates = FXCollections.observableArrayList();
			for (int i = 1; i <= totalDays; i++) {
				dates.add(i);
			}

			cmbStartDate.setItems(dates);
		} else {
			// ALERT FOR EMPTY YEAR INPUT
		}

	}

}
