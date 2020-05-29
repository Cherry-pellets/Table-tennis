import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class Score extends Pane{
	static int scores=0;
	static Label label1=new Label("得分：");
	static Label label2=new Label("0");
	static String s="";
	public Score() {
		scores=0;
		//display();
		label1.setTextFill(Color.BLACK);
		label2.setTextFill(Color.RED);
		
		label1.setFont(new Font("SimHei",32));//黑体32号字
    	label2.setFont(new Font("Times New Roman",32));
    	
    	label1.setTranslateY(400);//字体位置偏移量
    	label2.setTranslateY(400);
    	label1.setTranslateX(480);
    	label2.setTranslateX(580);
    	getChildren().add(label1);
    	getChildren().add(label2);
	}
	public static void addScore(int score) {
		//BallPane.score+=score;
		//s=BallPane.score+"";
	    scores+=score;
	    s=scores+"";
		label2.setText(s);
	}
	public static void subScore(int score) {
		scores-=score;
		s=scores+"";
		label2.setText(s);
	}
	/*public void showScore() {
		label2.setText(BallPane.score+"");
	}*/
	public static int getScores() {
		return scores;
	}
}
