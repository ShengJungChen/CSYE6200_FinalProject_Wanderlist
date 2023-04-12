package application;

import java.io.IOException;

import application.Dashboard.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.User.User;

public class LoginController {

	ApplicationSystem database = ApplicationSystem.getInstance();

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

	public void logIn(ActionEvent event) throws IOException {

		String email = tf_username.getText();
		String password = tf_password.getText();

		// validate email & password
		User user = database.getUserDirectory().getUserByEmail(email);

		if (user == null) {
			// USER NOT FOUND ALERT

			System.out.println("no user");

			event.consume();
		} else {

			if (!user.getPassword().equals(password)) {
				// PASSWORD INCORRECT ALERT

				System.out.println("wrong pw");

			} else {

				// log into dashboard success
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("../application/Dashboard/dashboardPage.fxml"));
				Parent root = loader.load();
				DashboardController dashboardController = loader.getController();
				dashboardController.loadPage(user);

				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
		}
	}
}
