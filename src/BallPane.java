import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import java.lang.Math;
import javafx.scene.control.Button;


public class BallPane extends BorderPane{
	private final double radius = 10;
	private final double cx=472,cy=197;			//�����ʼ���ĵ�һ���������
	private final double u = 0.002;
	private final double C = 0.75;				//ײǽ�������ٶȼ�С�ı���
	public boolean allStill = true;
	public static int score = 0;
	//��ȡ���ڵ��������� 
	private double holeX[] = { 35, 323, 613};
	private double holeY[] = { 52, 342 };
	Bgm bgm=new Bgm();
	//Bgm bgm2=new Bgm();                                              //6.1
	//Bgm bgm3=new Bgm();
		
	Timeline timeline;
	
	Button btStart = new Button("���¿�ʼ");
	//��ʼ��11���������������ball[10]Ϊ���˵İ���
	Ball[] ball = new Ball[11];{
	ball[0] = new Ball(cx,cy,radius);
	ball[1] = new Ball(cx+1.73*radius+1,cy+radius,radius);
	ball[2] = new Ball(cx+1.73*radius+1,cy-radius,radius);
	ball[3] = new Ball(cx+3.46*radius+1,cy+2*radius,radius);
	ball[4] = new Ball(cx+3.46*radius+1,cy,radius);
	ball[5] = new Ball(cx+3.46*radius+1,cy-2*radius,radius);
	ball[6] = new Ball(cx+5.19*radius,cy+3*radius,radius);
	ball[7] = new Ball(cx+5.19*radius,cy+radius,radius);
	ball[8] = new Ball(cx+5.19*radius,cy-radius,radius);
    ball[9] = new Ball(cx+5.19*radius,cy-3*radius,radius);
    ball[10] = new Ball(180,198,radius);
	}
	ImageView desk = new ImageView("file:image/desk.jpg");	
	
	public BallPane(){
		//����һ�����󣬳�ʼ�������1������10������
		ball[10].setFill(Color.WHITE);

		this.getChildren().add(desk);
		for(int i=0;i<11;i++) {
			this.getChildren().add(ball[i]);
			//ball[i].setFill(Color.WHITE);
		}
		this.getChildren().add(btStart);
		btStart.setOnAction(e->{
			timeline.pause();
		});
		//���һ���¼���ÿ xx msִ��һ����Ϊe
		
		timeline = new Timeline(new KeyFrame(Duration.millis(10),e->pengzhuangBall()));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	public void play() {
		timeline.play();
	}
	public void pause() {
		
	}
	protected boolean isAllStill() {
		for(int i=0;i<11;i++) {
			if(ball[i].dx != 0 || ball[i].dy!=0)return false;
		}
		return true;
	}
	protected void moveBall(Ball ball) {
		//�ƶ����ƶ��Ĺ����ٶ�Ϊˮƽ��ֱdx��dy
		ball.setCenterX(ball.getCenterX()+ball.getdx());
		ball.setCenterY(ball.getCenterY()+ball.getdy());
		if(Math.abs(ball.getV())>0.00005) {

			ball.v = Math.abs(ball.getV()) - u;
			ball.dx = ball.v * Math.cos(ball.getAngle());
			ball.dy = ball.v * Math.sin(ball.getAngle());
		}
		
		else {
			ball.dx = 0;ball.dy=0;
		}
	}
	
	protected void pengzhuangBall() {
		//�ƶ������ײ����ײ����Ե����������ʱ
		for(int i=0;i<11;i++) {
			//boolean bgmplayed=false;                                           //6.1
			for(int j=i+1;j<11;j++) {
				//if(i==j)continue;
				if(ball[i].holed || ball[j].holed) {
					/*if(!bgmplayed) {                                            //6.1
						//bgm.plays(7);
						bgmplayed=true;
					}*/                                                 
					continue;
				}
				if(crashed(ball[i],ball[j])) {
					bgm.plays(6);                                                   //6.1
					
					//����С���������ٶȱ任����
					double l1 = ball[i].getCenterX() - ball[j].getCenterX();
					double l2 = ball[i].getCenterY() - ball[j].getCenterY();
					double l3 = Math.hypot(l1, l2);
					double l4 = l1 / l3;// �����������������ļнǵ���������
					double l5 = l2 / l3;
					double ki, li, kj, lj;
					ki = ball[i].dx - ball[i].dx * l4
							* l4;
					ki -= ball[i].dy * l4 * l5;
					ki += ball[j].dx * l4 * l4;
					ki += ball[j].dy * l4 * l5;// i1����ٶȺ����
					li = ball[i].dy - ball[i].dy * l5
							* l5;
					li -= ball[i].dx * l4 * l5;
					li += ball[j].dy * l5 * l5;
					li += ball[j].dx * l4 * l5;// i1����ٶ��ݷ���
					kj = ball[j].dx - ball[j].dx * l4
							* l4;
					kj -= ball[j].dy * l4 * l5;
					kj += ball[i].dx * l4 * l4;
					kj += ball[i].dy * l4 * l5;// j1����ٶȺ����
					lj = ball[j].dy - ball[j].dy * l5
							* l5;
					lj -= ball[j].dx * l4 * l5;
					lj += ball[i].dy * l5 * l5;
					lj += ball[i].dx * l4 * l5;// j1����ٶ��ݷ���
					
					ball[i].dx = ki * 0.9;ball[i].dy = li* 0.9;
					ball[j].dx = kj* 0.9;ball[j].dy = lj* 0.9;

					moveBall(ball[j]);
					holeHandle(ball[j]);
				}
				//������ճ��֮����ж�
				if(crashed(ball[i],ball[j])) {
					bgm.plays(6);                                                            //6.1
					ball[i].tag2++;ball[j].tag2++;
				}
				if(crashed(ball[i],ball[j]) && ball[i].tag2>5 && ball[j].tag2>5) {
					bgm.plays(6);                                                           //6.1
					away(ball[i],ball[j]);
				}
			}
			
			//ײ����Եʱ�ٶȷ���
			if((ball[i].getCenterX() < 37+radius || ball[i].getCenterX() > 611-radius) && ball[i].getCenterY()>72 && ball[i].getCenterY()<326) {
				bgm.plays(5);                                                                 //6.1
				ball[i].setdx( ball[i].getdx() * (-1) );
				ball[i].dx = ball[i].dx * C;
				ball[i].dy = ball[i].dy * C;		//ײ����Եʱ�ٶȼ�С�ķ�֮һ
				//new Bgm().plays(5);
				moveBall(ball[i]);
			}
			if((ball[i].getCenterY() < 56+radius || ball[i].getCenterY() > 344-radius) && 
					( (ball[i].getCenterX()>51 && ball[i].getCenterX()<302) || (ball[i].getCenterX()>344 && ball[i].getCenterX()<595)) )  {
				ball[i].setdy(ball[i].getdy() * (-1));
				ball[i].dx = ball[i].dx * C;
				ball[i].dy = ball[i].dy * C;  	//ײ����Եʱ�ٶȼ�С�ķ�֮һ
				moveBall(ball[i]);
				bgm.plays(5);                                                                   //6.1
			}
			
			if(!valid(ball[i])) {
				//�����������ʱ�Ĵ���
				moveBall(ball[i]);ball[i].tag1++;
				if(ball[i].tag1>5) {
					inCase(ball[i]);moveBall(ball[i]);moveBall(ball[i]);
					ball[i].tag1 = 0;
				}
			}
			//��������
			holeHandle(ball[i]);
			if(ball[10].holed) {
				//bgm.plays(7);                                                                      //6.1
				ball[10].setCenterX(180);ball[10].setCenterY(198);ball[10].setHoled(false);this.getChildren().add(ball[10]);ball[10].dx=0;ball[10].dy=0;
				//score--;
				Score.subScore(2);
			}
			moveBall(ball[i]);
			allStill = isAllStill();
		}
	}
	public boolean crashed(Ball ball1, Ball ball2) {
		return Math.hypot(ball1.getCenterX()-ball2.getCenterX(), ball1.getCenterY()-ball2.getCenterY()) < radius*2-0.9;
	}
	public boolean valid(Ball ball) {
		//�������������򷵻�true����ֹ��ǽ���������
		if(ball.getCenterX() > 37+radius && ball.getCenterX() < 611-radius 
				&& ball.getCenterY() > 56+radius && ball.getCenterY() < 344-radius) {
			return true;
		}
//		else if(ball.getCenterX() < 37+radius && ball.getCenterY())
		else return false;
	}
	public void inCase(Ball ball) {
		//����ǽʱ�ļƻ�����һ�����ٶȳ������
		if(ball.getCenterX()<37+radius )ball.dx=1;
		else if(ball.getCenterX()>611-radius)ball.dx=-1;
		else if(ball.getCenterY()<56+radius)ball.dy=1;
		else	ball.dy=-1;
	}
	public void holeHandle(Ball ball) {
		//bgm.plays(7);
		//��������
		if(!ball.holed) {
			for(int i1=0;i1<2;i1++) {
				for(int i2=0;i2<3;i2++) {
					if(Math.hypot(holeX[i2]-ball.getCenterX(), holeY[i1]-ball.getCenterY() )< 1*radius) {
						ball.setHoled(true);
						Score.addScore(1);
						bgm.plays(7);                                              //6.1
						this.getChildren().remove(ball);
						score++;
						}
					}
			}
		}
	}
	public int getScore() {
		return this.score;
	}
	public void away(Ball ball1,Ball ball2) {
		//����������һ��ʱ�ķֿ�����
		if(ball1.getCenterX()>ball2.getCenterX()) {
			if(ball1.getCenterY()>ball2.getCenterY()) {
				ball1.setCenterX(ball1.getCenterX()+1);ball1.setCenterY(ball1.getCenterY()+1);
				ball2.setCenterX(ball2.getCenterX()-1);ball2.setCenterY(ball2.getCenterY()-1);
			}
			else {
				ball1.setCenterX(ball1.getCenterX()+1);ball1.setCenterY(ball1.getCenterY()-1);
				ball2.setCenterX(ball2.getCenterX()-1);ball2.setCenterY(ball2.getCenterY()+1);
			}
		}
		else {
			if(ball1.getCenterY()>ball2.getCenterY()) {
				ball1.setCenterX(ball1.getCenterX()-1);ball1.setCenterY(ball1.getCenterY()+1);
				ball2.setCenterX(ball2.getCenterX()+1);ball2.setCenterY(ball2.getCenterY()-1);
			}
			else {
				ball1.setCenterX(ball1.getCenterX()-1);ball1.setCenterY(ball1.getCenterY()-1);
				ball2.setCenterX(ball2.getCenterX()+1);ball2.setCenterY(ball2.getCenterY()+1);
			}
		}
	}
	
}
