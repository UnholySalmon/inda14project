import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	private static final float RATIO = 16/9f;
	private static final int HEIGHT = 720, WIDTH = (int) (HEIGHT * RATIO);
	
	public static Input input;
	private static Map map;
	
	public Game(String title) {
		super(title);
	}
	
	public void init(GameContainer container) throws SlickException {
		map = new Map("res/testmap.png");
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		map.update(delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		map.render();	
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("I Would Like To Be The Guy"));
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setTargetFrameRate(300);
		app.start();
		
		input = app.getInput();
	}
	
	public static boolean isKeyPressed(int k) {
		return input.isKeyPressed(k);
	}
	
}
