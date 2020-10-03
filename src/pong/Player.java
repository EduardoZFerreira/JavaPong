package pong;

public class Player extends Entity {

	public boolean right, left;
	
	
	public Player(int x, int y) {
		super(x, y);
		WIDTH = 40;
		HEIGHT = 5;
	}
	
	public void tick() {
		if(left) {
			x--;
		}
		if(right) {
			x++;
		}
		
		if(x + WIDTH > Game.WIDTH) {
			x = Game.WIDTH - WIDTH;
		}
		if(x < 0) {
			x = 0;
		}		
	}
	
}
