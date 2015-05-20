import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
	
	private boolean paused = false;
	
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
		if (container.getInput().isKeyDown(Input.KEY_ESCAPE)) System.exit(0);
		if (container.getInput().isKeyPressed(Input.KEY_P))
			paused = !paused;
		if (paused)
			return;
		World.update(container, delta);
	}
	
	/**
	 * A method extended from BasicGame.
	 * Renders World.
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		World.render();
		if (paused) {
			
			int screenCenterX = container.getWidth()/2,
				screenCenterY = container.getHeight()/2;
			int boxWidth = 400, boxHeight = 200;
			
			g.setColor(new Color(170,170,140));
			g.fillRoundRect(screenCenterX - boxWidth/2, screenCenterY - boxHeight/2, boxWidth, boxHeight, 10);
			
			String pausetext = "Paused";
			g.setColor(new Color(30,110,170));
			g.drawString(pausetext,screenCenterX-27,screenCenterY-5);
		}
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
