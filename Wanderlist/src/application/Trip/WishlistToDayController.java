package application.Trip;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class WishlistToDayController extends Application {

	@FXML
	private ListView<String> poolListView = new ListView<String>();
	@FXML
	private ListView<String> day1ListView = new ListView<String>();
	@FXML
	private ListView<String> day2ListView = new ListView<String>();

	@FXML
	private Button btn_remove;
	@FXML
	private Button btn_remove2;

	private ObservableList<String> leftList = FXCollections.observableArrayList();
	private ObservableList<String> day1List = FXCollections.observableArrayList();
	private ObservableList<String> day2List = FXCollections.observableArrayList();

	@FXML
	private GridPane rootPane = new GridPane();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("WishlistToDay.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("WanderList");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void initialize() {
		dragdrop();
		populateData();
	}

	@FXML
	private void moveButtonAction(ActionEvent e) {
		String selectedItem = day1ListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			poolListView.getItems().add(selectedItem);
			day1ListView.getItems().remove(selectedItem);
		}
	}

	@FXML
	private void moveButtonAction2(ActionEvent e) {
		String selectedItem = day2ListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			poolListView.getItems().add(selectedItem);
			day2ListView.getItems().remove(selectedItem);
		}
	}

	private void dragdrop() {

		poolListView.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (poolListView.getSelectionModel().getSelectedItem() == null) {
					return;
				}
				Dragboard dragBoard = poolListView.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(poolListView.getSelectionModel().getSelectedItem());
				dragBoard.setContent(content);
			}
		});

		day1ListView.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				if (dragEvent.getDragboard().hasString()) {
					dragEvent.acceptTransferModes(TransferMode.MOVE);
				}
			}
		});

		day1ListView.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				String player = dragEvent.getDragboard().getString();
				day1List.addAll(player);
				leftList.remove(player);
				day1ListView.setItems(day1List);
				poolListView.setItems(leftList);
				dragEvent.setDropCompleted(true);
			}
		});

		day2ListView.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				if (dragEvent.getDragboard().hasString()) {
					dragEvent.acceptTransferModes(TransferMode.MOVE);
				}
			}
		});

		day2ListView.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				String player = dragEvent.getDragboard().getString();
				day2List.addAll(player);
				leftList.remove(player);
				day2ListView.setItems(day2List);
				poolListView.setItems(leftList);
				dragEvent.setDropCompleted(true);
			}
		});

	}

	@FXML
	private void populateData() {
		leftList.addAll("A1", "A2", "A3");
		day1List.addAll("B1", "B2", "B3");

		poolListView.setItems(leftList);
		day1ListView.setItems(day1List);
	}

}