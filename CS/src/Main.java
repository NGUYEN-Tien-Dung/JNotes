
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.Parent;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
			FXMLLoader  loader = new FXMLLoader(getClass().getResource("Interface.fxml"));
			Parent root = loader.load();
			VBox contenaire = (VBox) loader.getNamespace().get("test");
			Editor abdellah = new Editor();
			contenaire.getChildren().add(abdellah);
			primaryStage.setTitle("Hello World");
			primaryStage.setScene(new Scene(root, 1280, 720));
			primaryStage.show();
			
			abdellah.render();
		} 
	
	public static void main(String[] args) {
		launch(args);
	}
}
