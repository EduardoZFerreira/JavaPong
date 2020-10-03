package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game  extends Canvas implements Runnable, KeyListener{
	
	private boolean isRunning;
	private Thread thread;
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public int playerScore = 0;
	public int enemyScore = 0;
		
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public static UserInterface ui;
	
	public Game() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		isRunning = true;
		addKeyListener(this);
		player = new Player(100, HEIGHT - 5);
		enemy = new Enemy(100, 0);
		ball = new Ball(100, HEIGHT / 2 - 1);
		ui = new UserInterface(WIDTH, HEIGHT);
		initFrame();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public void initFrame() {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}	
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
		score();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g, Color.CYAN);
		enemy.render(g, Color.RED);
		ball.render(g, Color.YELLOW);
		ui.render(g);
		ui.renderScore(playerScore, enemyScore, g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		bs.show();
	}
	
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();
			}
		}
		stop();
	}
	
	public void reset() {
		player.reset();
		enemy.reset();
		ball.reset();
	}
	
	public void score() {
		if(ball.y >= HEIGHT) {
			enemyScore++;
			reset();
		} else if(ball.y < 0) {
			playerScore++;
			reset();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		player.right = e.getKeyCode() == KeyEvent.VK_RIGHT;
		player.left = e.getKeyCode() == KeyEvent.VK_LEFT;		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.right = false;
		player.left = false;	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
