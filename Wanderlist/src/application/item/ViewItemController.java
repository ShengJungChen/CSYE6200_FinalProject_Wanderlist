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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewItemController extends Application {

	// button
	@FXML	private Button btn_back;
	@FXML	private Button btn_edit;
	@FXML	private Button btn_cancel;
	@FXML	private Button btn_save;

	// text
	@FXML	private Label name;
	@FXML	private Label type;
	@FXML	private Label url;
	@FXML	private Label address;
	@FXML	private TextArea note;

	// pane
	@FXML	private BorderPane rootPane;
	@FXML   private VBox vBox;
	@FXML	private Pane firstPane;
	@FXML	private GridPane gridPane;
	@FXML	private Pane secondPane;

	// type comboBox with 4 options
	@FXML	private ComboBox<String> typeBox;
	ObservableList<String> typeList = FXCollections.observableArrayList("Eat", "Buy", "Play", "See");

	// start
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("ViewItemPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	private void initialize() throws IOException {
		typeBox.setItems(typeList);
		// typeBox.setOnAction(event -> onTypeBoxChanged());
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
		if (typeBox.getValue().equals("Eat")) {
			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_eatPane.fxml"));
			secondPane.getChildren().setAll(eatpane);
		} else if (typeBox.getValue().equals("Buy")) {
			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_buyPane.fxml"));
			secondPane.getChildren().setAll(eatpane);
		} else if (typeBox.getValue().equals("Play")) {
			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_playPane.fxml"));
			secondPane.getChildren().setAll(eatpane);
		} else if (typeBox.getValue().equals("See")) {
			Pane eatpane = FXMLLoader.load(getClass().getResource("AddNewItem_seePane.fxml"));
			secondPane.getChildren().setAll(eatpane);
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

	@FXML	private void editButtonAction(ActionEvent event) throws IOException {
		btn_edit.setDisable(true);
		btn_cancel.setDisable(false);
		btn_save.setDisable(false);
		Pane defaultPane = FXMLLoader.load(getClass().getResource("AddNewItem_defaultPane.fxml"));
		vBox.getChildren().setAll(defaultPane);
	}
	
	@FXML	private void cancelButtonAction(ActionEvent event) {
		btn_edit.setDisable(false);
		btn_cancel.setDisable(true);
		btn_save.setDisable(true);
		vBox.getChildren().setAll(firstPane,secondPane);
	}
	
	@FXML	private void saveButtonAction(ActionEvent event) {
		btn_edit.setDisable(false);
		btn_cancel.setDisable(true);
		btn_save.setDisable(true);
	}

}
