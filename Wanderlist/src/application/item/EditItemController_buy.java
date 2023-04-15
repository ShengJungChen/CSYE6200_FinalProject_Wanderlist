package application.item;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditItemController_buy extends Application {
	//button
	@FXML private Button btnBack;
	@FXML private Button btnCancel;
	@FXML private Button btnSave;
	@FXML private Button btnEdit;
	
	//TextField
	@FXML private TextField inputName;
	@FXML private TextField inputUrl;
	@FXML private TextField inputAddress;
	@FXML private TextArea inputNote;
	
	//operating day
	@FXML private CheckBox mon;
	@FXML private CheckBox tue;
	@FXML private CheckBox wed;
	@FXML private CheckBox thur;
	@FXML private CheckBox fri;
	@FXML private CheckBox sat;
	@FXML private CheckBox sun;
	
	//operating hour
	@FXML private ComboBox<Integer> from;
	@FXML private ComboBox<Integer> to;	

	//shopping list
	@FXML private Button btn_add;
	@FXML private Button btn_delete;
	@FXML private ListView<String> listView;
	@FXML private TextField input;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EditItem_buy.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void initialize() {
		
		from.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));
		to.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));
		from.getSelectionModel().select(0);
		to.getSelectionModel().select(0);

	}
	
	@FXML
	private void addItem(ActionEvent event) {
		listView.getItems().add(input.getText());
	}
	
	@FXML
	private void deleteItem(ActionEvent event) {
		String selectedItem = listView.getSelectionModel().getSelectedItem();
		if(selectedItem != null) {
			listView.getItems().remove(selectedItem);
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
