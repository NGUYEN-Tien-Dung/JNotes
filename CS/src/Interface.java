import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Interface {
	
	@FXML
	private Button b1;
	
	@FXML
	private TextField t1;
	
	@FXML
	void sayHi(ActionEvent event) {
		      t1.setText( "Hi" );
	}
	
}
