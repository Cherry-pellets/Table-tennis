import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	double startPositionX,startPositionY;
	double endPositionX,endPositionY;
	//Stage stage = new Stage();
	@Override
	public void start(Stage stage) {
				
		BallPane pane = new BallPane();
		
		Score score=new Score();                                                  //��Ϸ�÷�  
		pane.getChildren().add(score);
		
		Restart restart=new Restart();                                           //���¿�ʼ
		pane.getChildren().add(restart);
		restart.btSure.setOnMouseClicked(e1->{
			Main mmm = new Main();
			mmm.start(new Stage());
			stage.close();
			restart.stage.close();
		});
		
		Bgm bgm=new Bgm();                                                       //bgm1
		bgm.plays(1);                                                        //5.29
		bgm.countDown(pane);//����ʱ
		
		Clock cl=new Clock();                                                 //��Ϸʣ��ʱ��
		cl.display1();
		pane.getChildren().add(cl);
		
//		pane.setOnMouseClicked(e->{
//			System.out.println(e.getX() + " " + e.getY());
//		});                                                             
		
		pane.setOnMousePressed(e->{
			startPositionX = e.getX();
			startPositionY = e.getY();
		});
		pane.setOnMouseReleased(e->{
			bgm.plays(3);                                                          //bgm2
			endPositionX = e.getX();
			endPositionY = e.getY();
			pane.ball[10].dx = (startPositionX - endPositionX) / 70;
			pane.ball[10].dy = (startPositionY - endPositionY) / 70;
		});
		
		Scene scene = new Scene(pane,650,480);                                        //5.29
		stage.setTitle("The Tabel Ball Game!");
		stage.setScene(scene);
		stage.setResizable(false);;
		stage.show();
		
		
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Application.launch(args);
	}

}
