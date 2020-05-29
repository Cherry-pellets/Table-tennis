import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
public class Result{
	//Text text=new Text("您的得分："+Score.getScores());;
	BorderPane pane=new BorderPane();
	HBox box=new HBox();
	Button btEnd=new Button("好");
//	Button btAgain=new Button("再玩一局");
	Stage stage=new Stage();
	 public Result() {
			
	 }
	 public void display() {
		 
		 Text text=new Text("您的得分："+Score.getScores());//(x,y,string);
		 
			
			text.setFont(new Font("SimHei",32));//字体，字号
			btEnd.setFont(new Font("SimHei",32));
			//btAgain.setFont(new Font("SimHei",32));
			
			btEnd.setStyle("-fx-background-color: blue");
			//btAgain.setStyle("-fx-background-color: blue");
			
			box.getChildren().add(btEnd);
			//box.getChildren().add(btAgain);
			
			box.setSpacing(20);
			box.setAlignment(Pos.CENTER);
			
			pane.setCenter(text);
			pane.setBottom(box);
			
			Scene scene=new Scene(pane,400,200);
			
			btEnd.setOnMouseClicked(e->{
				stage.close();
			});
			stage.setScene(scene);
			stage.setTitle("提示");
			stage.show();
			      
	 }
}
