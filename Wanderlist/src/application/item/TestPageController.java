package application.item;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TestPageController extends Application {

	@FXML
	private Button btn_back;
	@FXML
	private Button btn_logout;
	@FXML	private Button btn_edit;
	@FXML private Button btn_back1;
	@FXML
	private Button btn_save;

	@FXML
	private TextField name_input;
	@FXML
	private TextField url_input;
	@FXML
	private TextField address_input;
	@FXML
	private TextField note_input;


	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("test_ariel.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("test by ariel");
		primaryStage.setScene(scene);
		primaryStage.show();
		//btn_save.setVisible(false);
	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		// Code to navigate to the other FXML file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNewItemPage.fxml"));
		Parent root = loader.load();
		Scene scene = btn_back.getScene();
		scene.setRoot(root);
	}
	
	@FXML
	private void handleButtonAction1(ActionEvent event) throws IOException {
		// Code to navigate to the other FXML file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewItemPage.fxml"));
		Parent root = loader.load();
		Scene scene = btn_back.getScene();
		scene.setRoot(root);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
