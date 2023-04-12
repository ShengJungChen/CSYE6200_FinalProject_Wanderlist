package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.User.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SignUpController {
	
	ApplicationSystem database = ApplicationSystem.getInstance();

	@FXML
	private TextField tf_username;
	
	@FXML
	private TextField tf_password;
	
	@FXML
	private Button button_signup;
	
	@FXML
	private Button button_log_in;
	

	@FXML
	public void switchToLoginPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Log In");
		stage.setScene(new Scene(root));
		stage.show();
	}

	// VALIDATE NEW ACCOUNT & SAVE
	@FXML
	public void signUp(ActionEvent event) throws IOException{
		String email = tf_username.getText();
		String password = tf_password.getText();
		
		//validate whether the email or password is empty
		if(email.isEmpty() || password.isEmpty()) {
			showAlert("Invalid Information", "Please enter both an email and password.");
	        //clear the textfield
	        tf_username.clear();
	        tf_password.clear();
			return;
		}
		
		User user = database.getUserDirectory().addNewUser(email, password);
		
		if (user == null) {
	        showAlert("Error", "Email already exists.");
	        //clear the textfield
	        tf_username.clear();
	        tf_password.clear();
	    } else {
	    	showSuccessfulAlert("Success", "Account created successfully.");
	        //clear the textfield
	        tf_username.clear();
	        tf_password.clear();
	        database.store();
	    }
		
	}
	
	private void showAlert(String title, String message) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	
	}
	private void showSuccessfulAlert(String title, String message) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
		
		// switch to login page
	    if(alert.getResult() == ButtonType.OK){
	    	Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) button_signup.getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	}


}
