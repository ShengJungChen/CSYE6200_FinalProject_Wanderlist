package application.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Trip.AddTripController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User.User;

public class DashboardController implements Initializable {

	User user;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnAdd;

	@FXML
	private Label labelGreet;

	@FXML
	private VBox vboxUpcomings;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	// Load and Display all trips
	public void loadPage(User user) {

		this.user = user;

		labelGreet.setText("Hello, " + user.getUserEmail() + "!");

		for (int i = 0; i < user.getTrips().getTrips().size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("tripItem.fxml"));

			try {
				HBox hBox = fxmlLoader.load();
				TripItemController tripItemController = fxmlLoader.getController();
				tripItemController.setData(user.getTrips().getTrips().get(i));
				vboxUpcomings.getChildren().add(hBox);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addAction(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../application/Trip/AddTrip.fxml"));

		Parent root = loader.load();
		AddTripController addTripController = loader.getController();
		addTripController.setUser(user);

		Stage stage = (Stage) btnAdd.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
}
