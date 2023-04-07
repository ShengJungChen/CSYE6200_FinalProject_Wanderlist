package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoggedInController implements Initializable {

	@FXML
	private Button button_logout;
	
	@FXML
	private Label label_welcome;
	
	@FXML
	private Label label_sample;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		button_logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "login.fxml", "Log in!", null, null);
				
			}
			
		});
		
	}
	
	public void setUserInformation(String username, String sample) {
		label_welcome.setText("Welcome" + username + "!");
		label_sample.setText(sample);
		
	}

}
