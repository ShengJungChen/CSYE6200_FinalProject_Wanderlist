package application.item;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class playPane_Controller extends Application {

	@FXML private CheckBox mon;
	@FXML private CheckBox tue;
	@FXML private CheckBox wed;
	@FXML private CheckBox thur;
	@FXML private CheckBox fri;
	@FXML private CheckBox sat;
	@FXML private CheckBox sun;
	
	@FXML private ComboBox<Integer> from;
	@FXML private ComboBox<Integer> to;	
	
	@FXML private CheckBox ticket;
	@FXML private TextField price;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AddNewItem_playPane.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("test by ariel");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	public void initialize() {
		
		from.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));
		to.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));

		from.getSelectionModel().select(0);
		to.getSelectionModel().select(0);
		
		price.setText("00");
	}

	

	public CheckBox getMon() {
		return mon;
	}

	public void setMon(CheckBox mon) {
		this.mon = mon;
	}

	public CheckBox getTue() {
		return tue;
	}

	public void setTue(CheckBox tue) {
		this.tue = tue;
	}

	public CheckBox getWed() {
		return wed;
	}

	public void setWed(CheckBox wed) {
		this.wed = wed;
	}

	public CheckBox getThur() {
		return thur;
	}

	public void setThur(CheckBox thur) {
		this.thur = thur;
	}

	public CheckBox getFri() {
		return fri;
	}

	public void setFri(CheckBox fri) {
		this.fri = fri;
	}

	public CheckBox getSat() {
		return sat;
	}

	public void setSat(CheckBox sat) {
		this.sat = sat;
	}

	public CheckBox getSun() {
		return sun;
	}

	public void setSun(CheckBox sun) {
		this.sun = sun;
	}

	public ComboBox<Integer> getFrom() {
		return from;
	}

	public void setFrom(ComboBox<Integer> from) {
		this.from = from;
	}

	public ComboBox<Integer> getTo() {
		return to;
	}

	public void setTo(ComboBox<Integer> to) {
		this.to = to;
	}

	public CheckBox getTicket() {
		return ticket;
	}

	public void setTicket(CheckBox ticket) {
		this.ticket = ticket;
	}

	public TextField getPrice() {
		return price;
	}

	public void setPrice(TextField price) {
		this.price = price;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
