import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
public class Restart extends Pane{
	Button btRestart=new Button("重新开始");
	Button btSure=new Button("是");
	Button btDeny=new Button("否");
	Stage stage=new Stage();
	public Restart() {
		btRestart.setScaleX(3);
		btRestart.setScaleY(3);
		//btRestart.autosize();
		btRestart.setTranslateX(320);
		btRestart.setTranslateY(420);
		btRestart.setStyle("-fx-background-color: orange");
		
		getChildren().add(btRestart);
		btRestart.setOnMouseClicked(e->{
			ifSure();
		});
	}
	public void ifSure() {
		BorderPane pane=new BorderPane();
		HBox box=new HBox();
		
		Text text=new Text("是否确定重新开始游戏？");//(x,y,string);
		
		
		text.setFont(new Font("SimHei",32));//字体，字号
		btSure.setFont(new Font("SimHei",32));
		btDeny.setFont(new Font("SimHei",32));
		
		btSure.setStyle("-fx-background-color: gray");
		btDeny.setStyle("-fx-background-color: blue");
		
		box.getChildren().add(btSure);
		box.getChildren().add(btDeny);
		box.setSpacing(20);
		box.setAlignment(Pos.CENTER);
		
		pane.setCenter(text);
		pane.setBottom(box);
		
		Scene scene=new Scene(pane,400,200);
		
		stage.setScene(scene);
		stage.setTitle("提示");
		stage.show();
		
		btDeny.setOnMouseClicked(e2->{
			stage.close();
		});        
			
	}	
		
}
