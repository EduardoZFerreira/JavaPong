package pong;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;


public class Ball extends Entity{

	public double dx, dy;
	public double speed = 1.6;
	
	public Ball(int x, int y) {
		super(x, y);
		WIDTH = 3;
		HEIGHT = 3;	
		setDirection();
	}
	
	public void setDirection() {
		int angle = new Random().nextInt(120 - 45) + 45;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void reset() {
		x = initX;
		y = initY;
		setDirection();
	}
	
	public void tick() {		
		if(x + WIDTH >= Game.WIDTH || x < 0) {
			dx *= -1;
		}
		
		

		Rectangle bounds = new Rectangle((int)(x + (dx * speed)), (int)(y + (dy * speed)), WIDTH, HEIGHT);		
		Rectangle playerBounds = new Rectangle((int)Game.player.x, (int)Game.player.y, Game.player.WIDTH, Game.player.HEIGHT);
		Rectangle enemyBounds = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.WIDTH, Game.enemy.HEIGHT);
		
		if(bounds.intersects(playerBounds)) {
	        
			setDirection();
			if(dy > 0) {
				dy *= -1;
			}	
		} else if(bounds.intersects(enemyBounds)) {
			
			setDirection();
			if(dy < 0) {
				dy *= -1;
			}	
		} 
		
		x += dx * speed;
		y += dy * speed;
	}
	
}
