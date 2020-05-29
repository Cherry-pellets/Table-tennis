import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestScore extends Application {
	@Override
	public void start(Stage stage) {
		Score score=new Score();
		Scene scene=new Scene(score,500,500);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
