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

public class seePane_Controller extends Application {
	
	@FXML private TextField mean;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AddNewItem_seePane.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("test by ariel");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public TextField getMean() {
		return mean;
	}


	public void setMean(TextField mean) {
		this.mean = mean;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
