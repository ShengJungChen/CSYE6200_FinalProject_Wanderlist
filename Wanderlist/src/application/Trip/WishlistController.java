package application.Trip;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class WishlistController {

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnNewItem;

	@FXML
	private Button btnView;

	@FXML
	private HBox dayHolder;

	@FXML
	private ListView<String> lvWishlist;

}
