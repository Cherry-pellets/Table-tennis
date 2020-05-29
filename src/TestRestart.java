import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
public class TestRestart extends Application{
	@Override
	public void start(Stage stage) {
		Restart restart=new Restart();
		Pane pane=new Pane();
		pane.getChildren().add(restart);
		Scene scene=new Scene(pane,500,500);
		stage.setScene(scene);
		stage.show();
	}
	public  static void main(String[] args) {
		launch(args);
	}
}
