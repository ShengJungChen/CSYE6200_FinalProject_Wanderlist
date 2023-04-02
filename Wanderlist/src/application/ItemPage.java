package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class ItemPage extends Application {
	
	@FXML
	private Button btn_back;
	
	@FXML
	private Button btn_save;
	
	@FXML
	private VBox popupBox;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("ItemPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("ItemPage");
		primaryStage.setScene(scene);
		primaryStage.show();
		

		

	}
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
	    // Code to navigate to the other FXML file
		FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
		Parent root = loader.load();
		Scene scene = btn_back.getScene();
		scene.setRoot(root);
	}
	


	
	public static void main(String[] args) {
		launch(args);
	}
}
