 import javafx.scene.layout.BorderPane;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.paint.Color;
import javafx.util.Duration ;

public class Bgm  {
	static ArrayList<MediaPlayer> mediaList=new ArrayList<>();
	//static ArrayList<String> list=new ArrayList();
	int j=2;
	public Bgm() {
		Media media1=new Media(this.getClass().getResource("bgm1/01����.wav").toExternalForm());
		Media media2=new Media(this.getClass().getResource("bgm1/02��ʼ����ʱ.wav").toExternalForm());
		Media media3=new Media(this.getClass().getResource("bgm1/03��ײ����.wav").toExternalForm());
		Media media4=new Media(this.getClass().getResource("bgm1/04������ײ.wav").toExternalForm());
		Media media5=new Media(this.getClass().getResource("bgm1/05��ײ��.wav").toExternalForm());
		Media media6=new Media(this.getClass().getResource("bgm1/06С�ٶ����뾲��.wav").toExternalForm());
		Media media7=new Media(this.getClass().getResource("bgm1/07�����.wav").toExternalForm());
		Media media8=new Media(this.getClass().getResource("bgm1/09��������ʱ.mp3").toExternalForm());
		
		MediaPlayer mediaPlayer1=new MediaPlayer(media1);
		MediaPlayer mediaPlayer2=new MediaPlayer(media2);
		MediaPlayer mediaPlayer3=new MediaPlayer(media3);
		MediaPlayer mediaPlayer4=new MediaPlayer(media4);
		MediaPlayer mediaPlayer5=new MediaPlayer(media5);
		MediaPlayer mediaPlayer6=new MediaPlayer(media6);
		MediaPlayer mediaPlayer7=new MediaPlayer(media7);
		//mediaPlayer7.setCycleCount(Timeline.INDEFINITE);
		MediaPlayer mediaPlayer8=new MediaPlayer(media8);
		
		mediaList.add(mediaPlayer1);
		mediaList.add(mediaPlayer2);
		mediaList.add(mediaPlayer3);
		mediaList.add(mediaPlayer4);
		mediaList.add(mediaPlayer5);
		mediaList.add(mediaPlayer6);
		mediaList.add(mediaPlayer7);
		mediaList.add(mediaPlayer8);
		/*for(int i=1;i<=7;i++) {
			mediaList.get(i-1).setStopTime(Duration.UNKNOWN);;
		}*/
	}
	public  void plays(int n) {
		mediaList.get(n-1).seek(Duration.ZERO);
		mediaList.get(n-1).play();
		/*int i=mediaList.get(n-1).getCurrentCount();
		mediaList.get(n-1).setCycleCount(++i);*/
	}
	public void countDown(BorderPane pane) {//����ʱ
		//Bgm bgm=new Bgm();                                                       //bgm1 start
		Button btStart=new Button("Start");
		btStart.setStyle("-fx-background-color: red");
		btStart.setScaleX(5);
		btStart.setScaleY(5);
		Label[] bt={new Label("1"),new Label("2"),new Label("3")};
		//Button[] bt= {new Button("1"),new Button("2"),new Button("3")};
		                          //,new ImageView("file:image/ball.png")
		pane.setCenter(btStart);
		//btStart.translateXProperty().bind(pane.widthProperty().divide(40));
		//btStart.translateYProperty().bind(pane.heightProperty().divide(80));
		btStart.setOnMouseClicked(e->{
				plays(2);
				btStart.setVisible(false);
				//����ʱ
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						// UI�̸߳��½���
						Platform.runLater(() -> {
							if(j!=2) {
								bt[j+1].setVisible(false);
							}
							try {
								bt[j].setScaleX(8);
								bt[j].setScaleY(8);
								bt[j].setTextFill(Color.RED);
								//bt[j].setStyle();
								pane.setCenter(bt[j]);
								j--;
							}
							catch(Exception e2) {
								Clock cl=new Clock();//��Ϸʣ��ʱ��                                             //5.29
								cl.display2();
								pane.getChildren().add(cl);
								
								timer.cancel();
							}
						});
					}
				};
				// ִ��Ƶ��
				timer.schedule(task, 0,960);
				});  	                                                  //bgm1 end
	}
}
