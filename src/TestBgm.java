import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
public class TestBgm extends Application{//
	@Override
	public void start(Stage primaryStage) {
		Bgm bgm=new Bgm();
		BorderPane pane=new BorderPane();
		bgm.countDown(pane);
		//pane.getChildren().add(btStart);
		primaryStage.setTitle("Bgm");
		primaryStage.setScene(new Scene(pane,500,500));
		primaryStage.show();
		}
	public static void main(String[] args){
		
		Application.launch();
	}
	
	
}
