package application.Trip;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Trip.Eat;
import model.Trip.Item;
import model.Trip.Trip;
import model.User.User;

public class WishlistToDayController extends Application {

	@FXML
	private ListView<Item> poolListView = new ListView<Item>();
	@FXML
	private ListView<Item> day1ListView = new ListView<Item>();
	@FXML
	private ListView<Item> day2ListView = new ListView<Item>();

	@FXML
	private Button btn_remove;
	@FXML
	private Button btn_remove2;

	private ObservableList<Item> leftList = FXCollections.observableArrayList();
	private ObservableList<Item> day1List = FXCollections.observableArrayList();
	private ObservableList<Item> day2List = FXCollections.observableArrayList();

	@FXML
	private GridPane rootPane = new GridPane();

	Item dragItem;

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
//		dragdrop();
		populateData();
	}

	@FXML
	private void moveButtonAction(ActionEvent e) {
		Item selectedItem = day1ListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			poolListView.getItems().add(selectedItem);
			day1ListView.getItems().remove(selectedItem);
		}
	}

	@FXML
	private void moveButtonAction2(ActionEvent e) {
		Item selectedItem = day2ListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			poolListView.getItems().add(selectedItem);
			day2ListView.getItems().remove(selectedItem);
		}
	}

	public void dragDetected(MouseEvent event) {
		Item player = poolListView.getSelectionModel().getSelectedItem();
		if (player == null) {
			return;
		}
		dragItem = player;
		Dragboard dragBoard = poolListView.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		content.put(Item.DATA_FORMAT, player);
		dragBoard.setContent(content);
	}

	public void dragOver(DragEvent dragEvent) {
		if (dragEvent.getDragboard().hasContent(Item.DATA_FORMAT)) {
			dragEvent.acceptTransferModes(TransferMode.MOVE);
		}
	}

	public void dragDropped(DragEvent dragEvent) {
		Item player = (Item) dragEvent.getDragboard().getContent(Item.DATA_FORMAT);
		day1List.addAll(player);
		leftList.remove(dragItem);
		day1ListView.setItems(day1List);
		poolListView.setItems(leftList);
		dragEvent.setDropCompleted(true);
	}

//	private void dragdrop() {
//
//		poolListView.setOnDragDetected(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				Item player = poolListView.getSelectionModel().getSelectedItem();
//				if (player == null) {
//					return;
//				}
//				dragItem = player;
//				Dragboard dragBoard = poolListView.startDragAndDrop(TransferMode.MOVE);
//				ClipboardContent content = new ClipboardContent();
//				content.put(Item.DATA_FORMAT, player);
//				dragBoard.setContent(content);
//			}
//		});
//
//		day1ListView.setOnDragOver(new EventHandler<DragEvent>() {
//			@Override
//			public void handle(DragEvent dragEvent) {
//				if (dragEvent.getDragboard().hasContent(Item.DATA_FORMAT)) {
//					dragEvent.acceptTransferModes(TransferMode.MOVE);
//				}
//			}
//		});
//
//		day1ListView.setOnDragDropped(new EventHandler<DragEvent>() {
//			@Override
//			public void handle(DragEvent dragEvent) {
//				Item player = (Item) dragEvent.getDragboard().getContent(Item.DATA_FORMAT);
//				day1List.addAll(player);
//				leftList.remove(dragItem);
//				day1ListView.setItems(day1List);
//				poolListView.setItems(leftList);
//				dragEvent.setDropCompleted(true);
//			}
//		});
//
//		day2ListView.setOnDragOver(new EventHandler<DragEvent>() {
//			@Override
//			public void handle(DragEvent dragEvent) {
//				if (dragEvent.getDragboard().hasContent(Item.DATA_FORMAT)) {
//					dragEvent.acceptTransferModes(TransferMode.MOVE);
//				}
//			}
//		});
//
//		day2ListView.setOnDragDropped(new EventHandler<DragEvent>() {
//			@Override
//			public void handle(DragEvent dragEvent) {
//				Item player = (Item) dragEvent.getDragboard().getContent(Item.DATA_FORMAT);
//				day2List.addAll(player);
//				leftList.remove(player);
//				day2ListView.setItems(day2List);
//				poolListView.setItems(leftList);
//				dragEvent.setDropCompleted(true);
//			}
//		});
//
//	}

	@FXML
	private void populateData() {
//		leftList.addAll("A1", "A2", "A3","A2");
//		day1List.addAll("B1", "B2", "B3");

		User user = new User("qwe", "qwe");
		Trip trip = new Trip(user, "TEST", "TEST", "TEST", 2023, 2, 1, 2023, 2, 2, null);

		leftList.addAll(new Eat(Item.Type.Eat, trip, "Eat"));
		day1List.addAll(new Eat(Item.Type.Eat, trip, "Eat2"));

		poolListView.setItems(leftList);
		day1ListView.setItems(day1List);
	}

}