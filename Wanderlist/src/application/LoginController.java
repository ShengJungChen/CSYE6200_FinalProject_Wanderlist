package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LoginController {
	
	@FXML
	private TextField tf_username;
	
	@FXML
	private TextField tf_password;
	
	@FXML
	private Button button_login;
	
	@FXML
	private Button button_sign_up;
	
	public void switchToSignUpPage(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("sign-up.fxml"));
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setTitle("Sign Up");
	    stage.setScene(new Scene(root));
	    stage.show();
	}
	
	public void switchToLoginPage(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setTitle("Login");
	    stage.setScene(new Scene(root));
	    stage.show();
	}
	
	public void handleButtonLogout(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setTitle("Login");
	    stage.setScene(new Scene(root));
	    stage.show();
	}

}
