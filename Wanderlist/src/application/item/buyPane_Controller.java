package application.item;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class buyPane_Controller extends Application {

	@FXML private Button btn_add;
	@FXML private Button btn_delete;
	@FXML private CheckBox done;
	@FXML private ListView<String> listView;
	@FXML private TextField input;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AddNewItem_buyPane.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("test by ariel");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	private void addItem(ActionEvent event) {
		listView.getItems().add(input.getText());
	}
	
	@FXML
	private void deleteItem(ActionEvent event) {
		int selectedID = listView.getSelectionModel().getSelectedIndex();
		listView.getItems().remove(selectedID);
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
