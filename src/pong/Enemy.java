package pong;

public class Enemy extends Entity {
	
	public Enemy(int x, int y) {
		super(x, y);
		WIDTH = 40;
		HEIGHT = 5;
	}
	
	public void tick() {
		x += (Game.ball.x - x - 6) * 0.1;
	}
	
	
}
