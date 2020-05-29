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
	private final double cx=472,cy=197;			//黑球初始化的第一个球的坐标
	private final double u = 0.002;
	private final double C = 0.75;				//撞墙后的球的速度减小的倍数
	public boolean allStill = true;
	public static int score = 0;
	//获取洞口的中心坐标 
	private double holeX[] = { 35, 323, 613};
	private double holeY[] = { 52, 342 };
	Bgm bgm=new Bgm();
	//Bgm bgm2=new Bgm();                                              //6.1
	//Bgm bgm3=new Bgm();
		
	Timeline timeline;
	
	Button btStart = new Button("重新开始");
	//初始化11个球对象，其中设置ball[10]为击杆的白球
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
		//构造一个对象，初始化桌面和1个白球，10个黑球
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
		//添加一个事件，每 xx ms执行一次行为e
		
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
		//制定球移动的规则，速度为水平垂直dx和dy
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
		//制定球的碰撞规则，撞到边缘或者其它球时
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
					
					//两个小球相碰的速度变换规则
					double l1 = ball[i].getCenterX() - ball[j].getCenterX();
					double l2 = ball[i].getCenterY() - ball[j].getCenterY();
					double l3 = Math.hypot(l1, l2);
					double l4 = l1 / l3;// 球心连线与横纵坐标的夹角的正，余弦
					double l5 = l2 / l3;
					double ki, li, kj, lj;
					ki = ball[i].dx - ball[i].dx * l4
							* l4;
					ki -= ball[i].dy * l4 * l5;
					ki += ball[j].dx * l4 * l4;
					ki += ball[j].dy * l4 * l5;// i1球的速度横分量
					li = ball[i].dy - ball[i].dy * l5
							* l5;
					li -= ball[i].dx * l4 * l5;
					li += ball[j].dy * l5 * l5;
					li += ball[j].dx * l4 * l5;// i1球的速度纵分量
					kj = ball[j].dx - ball[j].dx * l4
							* l4;
					kj -= ball[j].dy * l4 * l5;
					kj += ball[i].dx * l4 * l4;
					kj += ball[i].dy * l4 * l5;// j1球的速度横分量
					lj = ball[j].dy - ball[j].dy * l5
							* l5;
					lj -= ball[j].dx * l4 * l5;
					lj += ball[i].dy * l5 * l5;
					lj += ball[i].dx * l4 * l5;// j1球的速度纵分量
					
					ball[i].dx = ki * 0.9;ball[i].dy = li* 0.9;
					ball[j].dx = kj* 0.9;ball[j].dy = lj* 0.9;

					moveBall(ball[j]);
					holeHandle(ball[j]);
				}
				//进行球粘连之后的行动
				if(crashed(ball[i],ball[j])) {
					bgm.plays(6);                                                            //6.1
					ball[i].tag2++;ball[j].tag2++;
				}
				if(crashed(ball[i],ball[j]) && ball[i].tag2>5 && ball[j].tag2>5) {
					bgm.plays(6);                                                           //6.1
					away(ball[i],ball[j]);
				}
			}
			
			//撞到边缘时速度反向
			if((ball[i].getCenterX() < 37+radius || ball[i].getCenterX() > 611-radius) && ball[i].getCenterY()>72 && ball[i].getCenterY()<326) {
				bgm.plays(5);                                                                 //6.1
				ball[i].setdx( ball[i].getdx() * (-1) );
				ball[i].dx = ball[i].dx * C;
				ball[i].dy = ball[i].dy * C;		//撞到边缘时速度减小四分之一
				//new Bgm().plays(5);
				moveBall(ball[i]);
			}
			if((ball[i].getCenterY() < 56+radius || ball[i].getCenterY() > 344-radius) && 
					( (ball[i].getCenterX()>51 && ball[i].getCenterX()<302) || (ball[i].getCenterX()>344 && ball[i].getCenterX()<595)) )  {
				ball[i].setdy(ball[i].getdy() * (-1));
				ball[i].dx = ball[i].dx * C;
				ball[i].dy = ball[i].dy * C;  	//撞到边缘时速度减小四分之一
				moveBall(ball[i]);
				bgm.plays(5);                                                                   //6.1
			}
			
			if(!valid(ball[i])) {
				//当球出现意外时的处理
				moveBall(ball[i]);ball[i].tag1++;
				if(ball[i].tag1>5) {
					inCase(ball[i]);moveBall(ball[i]);moveBall(ball[i]);
					ball[i].tag1 = 0;
				}
			}
			//进洞处理
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
		//若球在桌面上则返回true，防止球卡墙的情况出项
		if(ball.getCenterX() > 37+radius && ball.getCenterX() < 611-radius 
				&& ball.getCenterY() > 56+radius && ball.getCenterY() < 344-radius) {
			return true;
		}
//		else if(ball.getCenterX() < 37+radius && ball.getCenterY())
		else return false;
	}
	public void inCase(Ball ball) {
		//在球卡墙时的计划（给一个初速度冲出来）
		if(ball.getCenterX()<37+radius )ball.dx=1;
		else if(ball.getCenterX()>611-radius)ball.dx=-1;
		else if(ball.getCenterY()<56+radius)ball.dy=1;
		else	ball.dy=-1;
	}
	public void holeHandle(Ball ball) {
		//bgm.plays(7);
		//进洞处理
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
		//在两个球卡在一起时的分开方法
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
