import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * The game world loads and stores all maps.
 * It understands which map to update or render.
 */
public class World {
	
	// currentMap keeps track of which map we are currently in
	private static Map currentMap;
	private static Map map1;
	// we would have to declare Map objects here to make more levels
	
	private static boolean paused = true, gameover, gamewon;
	
	/**
	 * Initialize all maps, and store the first map in currentMap
	 */
	public static void init() {
		gameover = false;
		gamewon = false;
		map1 = new Map("res/map1.png");
		currentMap = map1;
	}
	
	/**
	 * Update the current map.
	 * 
	 * @param container Gets input from here
	 * @param delta Has to be passed to the objects.
	 */
	public static void update(GameContainer container, int delta) {
		// if the game's over, let the user restart by pressing R
		if ((gameover || gamewon) && container.getInput().isKeyDown(Input.KEY_R)) {
			restart();
		} 
		if (gameover) return;
		// pause the game when the user presses P
		if (container.getInput().isKeyPressed(Input.KEY_P)) {
			paused = !paused;
		}
		if (paused) return;
		currentMap.update(container, delta);
	}
	
	/**
	 * Render the current map.
	 * 
	 * @param container Gets input from here
	 * @param g Graphics context to draw onto
	 */
	public static void render(GameContainer container, Graphics g) {
		currentMap.render();
		
		if (paused || gameover || gamewon) drawMenuBox(400,200,20,container,g);
		if (gameover) {
			// draw game over text
			String text = "Game Over\nPress R to restart";
			g.setColor(Color.black);
			g.drawString(text,container.getWidth()/2-80,container.getHeight()/2-15);
		} else if (paused) {
			// draw pause text
			String text = "Paused\nPress P to unpause";
			g.setColor(Color.black);
			g.drawString(text,container.getWidth()/2-80,container.getHeight()/2-15);
		} else if (gamewon) {
			String text = "You won the game!\nPress R to restart";
			g.setColor(Color.black);
			g.drawString(text,container.getWidth()/2-80,container.getHeight()/2-15);
		}
	}
	
	/**
	 * Draws a white rectangle box with rounded corners in the middle of the screen.
	 * 
	 * @param width Width
	 * @param height Height
	 * @param cornerRadius Radius of the rounded corners
	 * @param container To find the center of the screen
	 * @param g Graphics context to draw onto
	 */
	public static void drawMenuBox(int width, int height, int cornerRadius, GameContainer container, Graphics g) {
		int screenCenterX = container.getWidth()/2,
			screenCenterY = container.getHeight()/2;
		g.setColor(Color.white);
		g.fillRoundRect(screenCenterX - width/2, screenCenterY - height/2, width, height, cornerRadius);
	}
	
	/**
	 * Sets the world in a game over state.
	 */
	public static void gameover() {
		gameover = true;
	}
	
	public static void winGame() {
		gamewon = true;
	}
	
	/**
	 * Re-initialize the current map and restore game over state.
	 */
	public static void restart() {
		init();
	}
	
}
