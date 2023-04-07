package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBUtils {
	public static  void changeScene(ActionEvent event, String fxmlFile, String title, String username, String sample) {
		
		Parent root = null;
		
		if(username != null && sample != null) {
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
				root = loader.load();
				LoggedInController loggedInController = loader.getController();
				loggedInController.setUserInformation(username,sample);
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root,960,600));
		stage.show();
	}

}
