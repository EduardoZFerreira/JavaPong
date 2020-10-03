package pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class UserInterface {
	
	private Graphics2D g2d;
	 
	@SuppressWarnings("unused")
	private int WIDTH, HEIGHT;
	
	public UserInterface(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
	}
	
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
	    float[] dash1 = { 2f, 0f, 2f };
		BasicStroke bs1 = new BasicStroke(1, 
		        BasicStroke.CAP_BUTT, 
		        BasicStroke.JOIN_ROUND, 
		        1.0f, 
		        dash1,
		        2f);
		g2d.setColor(Color.WHITE);
		g2d.setStroke(bs1);
	    g2d.drawLine(0, HEIGHT / 2, 200, HEIGHT / 2);
	}
	
	public void renderScore(int playerScore, int enemyScore, Graphics g) {		
		g.setFont(new Font("Arial", Font.BOLD, 9));
		g.drawString("CPU Score: " + enemyScore, 5, (HEIGHT / 2) - 3);
		
		g.setFont(new Font("Arial", Font.BOLD, 9));
		g.drawString("Player Score: " + playerScore, 5, (HEIGHT / 2) + 10 );
	}
	
}
