package application.Trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AddTripController {

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnCancel;

	@FXML
	private Pane paneTrip;

	@FXML
	private Parent gridEdit;

	@FXML
	private EditTripController gridEditController;

	@FXML
	void cancelAction(ActionEvent event) {

	}

	@FXML
	void saveAction(ActionEvent event) {
		gridEditController.addNew();
	}

}
