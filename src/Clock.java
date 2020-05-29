import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Clock extends Pane {

    private Timeline animation;
    private String S = "";
    int time = 180;
    boolean isEnd=true;
    boolean is9Seconds=true;
    BorderPane pane=new BorderPane();
	Result result = new Result();
    
    Label label1=new Label("剩余时间：");
    Label label2=new Label("180");
    Label label3=new Label(" S");
    
    public Clock() {
    	label1.setTextFill(Color.BLACK);
    	label2.setTextFill(Color.RED);
    	label3.setTextFill(Color.RED);
    	
    	label1.setFont(new Font("SimHei",32));//黑体32号字
    	label2.setFont(new Font("Times New Roman",32));
    	label3.setFont(new Font("Times New Roman",32));
    	
    	label1.setTranslateY(400);//字体位置偏移量
    	label2.setTranslateY(400);
    	label3.setTranslateY(400);
    	label2.setTranslateX(150);
    	label3.setTranslateX(200);
    	
    }
    public void addTime(int time) {
    	this.time-=time;
    }
    public void subTime(int time) {
    	this.time+=time;
    }
    public int  getTime() {
    	return time;
    }
    public void display1() {
    	getChildren().add(label1);
    	getChildren().add(label3);
    	
    }
    public void display2() {
        //label2.setFont(javafx.scene.text.Font.font(20));
    	getChildren().add(label2);
        //setAlignment(label2,Pos.CENTER_LEFT);
       //setLeft(label1);
        //setRight(label2);
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
    }
    public void timelabel() {
    	if(time>0) {
    		time--;
    	}
        S = time + "";
        label2.setText(S);
        
        if(time==0&&isEnd) {                                             //0秒时结束，提示结果
        	isEnd=false;
        	Result result=new Result();
        	result.display();
        }
        if(time==6&&is9Seconds) {                                         //9秒倒计时
        	Bgm bgm=new Bgm();
        	bgm.plays(8);
        }
    }
   
}