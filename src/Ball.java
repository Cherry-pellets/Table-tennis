import javafx.scene.shape.Circle;
import java.lang.Math;

public class Ball extends Circle{
	double dx=0,dy=0;
	double v;
	double angle;
	int tag1 = 0;  //卡墙的标志位
	int tag2 = 0;  //两个球黏在一起的标志位
	boolean holed;
	//public double x = this.getCenterX(),y = this.getCenterY();
	
	public Ball(double x, double y, double radius) {
		super(x,y,radius);
	}
	
	public double getdx() {
		return dx;
	}
	public double getdy() {
		return dy;
	}
	public void setdx(double dx) {
		this.dx = dx;
	}
	public void setdy(double dy) {
		this.dy = dy;
	}
	public double getV() {
		return Math.sqrt(dx*dx+dy*dy);
	}
	public boolean isHoled() {
		return this.holed;
	}
	public void setHoled(boolean bool) {
		this.holed = bool;
	}
	public double getAngle() {
		if(dx>0 && dy>0)	return Math.atan(dy/dx);
		else if(dx>0 && dy<0)	return Math.atan(dy/dx);
		else if(dx<0 && dy>0)	return Math.atan(dy/dx)+Math.PI;
		else	return Math.atan(dy/dx)+Math.PI;
	}
}
