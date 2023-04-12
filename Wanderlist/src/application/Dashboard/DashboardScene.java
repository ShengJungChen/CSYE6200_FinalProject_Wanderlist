package application.Dashboard;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.System.ApplicationSystem;
import model.User.User;

public class DashboardScene extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		ApplicationSystem database = ApplicationSystem.getInstance();
		database.getUserDirectory().addNewUser("anita@gmail.com", "test");

		User user = database.getUserDirectory().getUserByEmail("TEST");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardPage.fxml"));
		Parent root = loader.load();
		DashboardController dashboardController = loader.getController();
		dashboardController.loadPage(user);

		Scene scene = new Scene(root);
		primaryStage.setTitle("Wanderlist");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
