package application.Trip;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.item.AddNewItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.Trip.Day;
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

	public void setTrip(Trip trip) {
		this.trip = trip;
		// JUST FOR TEST
		System.out.println("TRIP" + this.trip.getTripName());

		populateDayList();

	}

	public void populateDayList() {

		System.out.println("HERE");

		dayHolder.getChildren().clear();

		ArrayList<Day> dayList = trip.getDays().getDays();

		for (int i = 0; i < dayList.size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("DayView.fxml"));

			try {
				VBox vBox = fxmlLoader.load();
				DayViewController dayViewController = fxmlLoader.getController();

				// get date string
				Date date = dayList.get(i).getDate();
				String pattern = "yyyy/MM/dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String dateString = simpleDateFormat.format(date);

				// set
				dayViewController.setData(dateString);
				dayHolder.getChildren().add(vBox);
				System.out.println("ADD" + i);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
