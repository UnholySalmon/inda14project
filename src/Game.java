import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The heart of this project, the main method rests here.
 * Game extends BasicGame and uses its methods to create a window.
 * Game handles World and all inputs.
 */
public class Game extends BasicGame {
	
	// determine window size
	private static final float RATIO = 16/9f;
	private static final int HEIGHT = 720, WIDTH = (int) (HEIGHT * RATIO);
	
	// maximum amount of rendered frames per second
	private static final int MAXFRAMES = 300;
	
	// title of window
	private static final String TITLE = "I Would Like To Be The Guy";
	
	/**
	 * Create a new Game.
	 * 
	 * @param title Title of window.
	 */
	public Game(String title) {
		super(title);
	}
	
	/**
	 * A method extended from BasicGame.
	 * Here, all resources will be loaded.
	 */
	public void init(GameContainer container) throws SlickException {
		Map.init(container);
		World.init();
	}
	
	/**
	 * A method extended from BasicGame.
	 * Updates World, passing along its delta argument.
	 * Delta is the time between frames,
	 * and all movement is taking this into account.
	 * 
	 * @param delta Time
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		World.update(container, delta);
	}
	
	/**
	 * A method extended from BasicGame.
	 * Renders World.
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		World.render();	
	}
	
	
	/**
	 * The program runs from here.
	 * An AppGameContainer is created and obtains a Game object.
	 * Its title, size and max renders per second are determined by class variables.
	 */
	public static void main(String[] args) throws SlickException {
		Game game = new Game(TITLE);
		AppGameContainer app = new AppGameContainer(game);
		app.setDisplayMode(WIDTH,HEIGHT,false);
		app.setTargetFrameRate(MAXFRAMES);
		app.start();
		
	}
	
}
