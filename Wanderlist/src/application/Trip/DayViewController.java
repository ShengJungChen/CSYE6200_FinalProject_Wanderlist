package application.Trip;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.System.ApplicationSystem;

public class DayViewController extends Application {
	
	@FXML private ListView<String> dayListView = new ListView<String>();
	@FXML private Label dayNum;
	@FXML private Button btn_remove;
	@FXML private Button btn_view;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("DayView.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Wanderlist");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	private void moveButtonAction(ActionEvent event) {
		String selectedItem = dayListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			//poolListView.getItems().add(selectedItem);
			dayListView.getItems().remove(selectedItem);
		}
	}
	
	@FXML
	private void viewButtonAction(ActionEvent event) {
		String selectedItem = dayListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			
		}
	}
	
}
