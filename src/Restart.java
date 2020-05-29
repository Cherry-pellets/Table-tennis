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
	Button btRestart=new Button("���¿�ʼ");
	Button btSure=new Button("��");
	Button btDeny=new Button("��");
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
		
		Text text=new Text("�Ƿ�ȷ�����¿�ʼ��Ϸ��");//(x,y,string);
		
		
		text.setFont(new Font("SimHei",32));//���壬�ֺ�
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
		stage.setTitle("��ʾ");
		stage.show();
		
		btDeny.setOnMouseClicked(e2->{
			stage.close();
		});        
			
	}	
		
}
