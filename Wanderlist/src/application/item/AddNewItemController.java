package application.item;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddNewItemController extends Application {
	// button
	@FXML	private Button btn_back;
	@FXML	private Button btn_cancel;
	@FXML	private Button btn_save;
	
	// textfield
	@FXML	private TextField name_input;
	@FXML	private TextField url_input;
	@FXML	private TextField address_input;
	@FXML	private BorderPane rootPane;
	@FXML	private Pane pane;
	@FXML	private TextArea note_input;
	
	// item type comboBox with 4 options
	@FXML	private ComboBox<String> typeBox;
	ObservableList<String> typeList = FXCollections.observableArrayList("Eat", "Buy", "Play", "See");
	
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AddNewItemPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
		// cannot add the set method of button
	}

	@FXML
	private void initialize() throws IOException {
	
		typeBox.setItems(typeList);
		//typeBox.setOnAction(event -> onTypeBoxChanged());
		typeBox.setOnAction(event -> {
			try {
				onTypeBoxChanged();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	@FXML
	private void onTypeBoxChanged() throws IOException {
		if(typeBox.getValue().equals("Eat")) {
			Pane typepane = FXMLLoader.load(getClass().getResource("AddNewItem_eatPane.fxml"));
			pane.getChildren().setAll(typepane);
		} else if (typeBox.getValue().equals("Buy")) {
			Pane typepane = FXMLLoader.load(getClass().getResource("AddNewItem_buyPane.fxml"));
			pane.getChildren().setAll(typepane);
		}
		else if (typeBox.getValue().equals("Play")) {
			Pane typepane = FXMLLoader.load(getClass().getResource("AddNewItem_playPane.fxml"));
			pane.getChildren().setAll(typepane);
		}
		else if (typeBox.getValue().equals("See")) {
			Pane typepane = FXMLLoader.load(getClass().getResource("AddNewItem_seePane.fxml"));
			pane.getChildren().setAll(typepane);
		}
	}
	
	@FXML
	private void backButtonAction(ActionEvent event) throws IOException {
		// Code to navigate to the other FXML file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("test_ariel.fxml"));
		Parent root = loader.load();
		Scene scene = btn_back.getScene();
		scene.setRoot(root);
	}
	
	@FXML
	public void saveButtonAction(ActionEvent event) {
		String name = name_input.getText();
		String type = typeBox.getValue();
		if(name.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please enter the schedule name.");
			alert.showAndWait();
		}
		if(type == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Please select a type.");
			alert.showAndWait();
		}
		if (!name.isEmpty() && type != null ) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Success");
			alert.setContentText("Your schedule was saved.");
			alert.showAndWait();
		}
	}

}
