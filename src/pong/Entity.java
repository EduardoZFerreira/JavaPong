package pong;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity{	
	public double x, y, initX, initY;	
	public int WIDTH;
	public int HEIGHT;
	
	public Entity(int x, int y) {
		this.x = x;
		initX = x;
		this.y = y;
		initY = y;
	}
	
	public void reset() {
		x = initX;
		y = initY;
	}
	
	public void render(Graphics g, Color c) {
		g.setColor(c);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
}
