import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
public class TestClock extends Application {
	@Override
	public void start(Stage primaryStage) {
		Clock cl=new Clock();
		cl.display1();
		cl.display2();
		Scene scene=new Scene(cl,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
