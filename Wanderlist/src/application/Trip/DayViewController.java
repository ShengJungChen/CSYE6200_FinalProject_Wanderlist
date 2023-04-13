package application.Trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DayViewController {

	@FXML
	private ListView<String> dayListView = new ListView<String>();
	@FXML
	private Label lbDate;
	@FXML
	private Label lbWeekday;
	@FXML
	private Button btn_remove;
	@FXML
	private Button btn_view;

//	public static void main(String[] args) {
//		launch(args);
//	}

//	@Override
//	public void start(Stage primaryStage) throws IOException {
//
//		Parent root = FXMLLoader.load(getClass().getResource("DayView.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setTitle("Wanderlist");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}

	@FXML
	private void moveButtonAction(ActionEvent event) {
		String selectedItem = dayListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			// poolListView.getItems().add(selectedItem);
			dayListView.getItems().remove(selectedItem);
		}
	}

	@FXML
	private void viewButtonAction(ActionEvent event) {
		String selectedItem = dayListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {

		}
	}

	public void setData(String date, String weekday) {
		this.lbDate.setText(date);
		this.lbWeekday.setText(weekday);
	}

}
