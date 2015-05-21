import org.newdawn.slick.GameContainer;


/**
 * Camera objects follows the player around the map and 
 * is also able to check if given entities are on-screen.
 */
public class Camera {
	
	// Current coordinates 
	private int x, y;
	private static int width, height;
	private final int minDistanceToEdgeY = 200;
	private final int minDistanceToEdgeX = 500;
	
	public Player player;
	
	/**
	 * Create a new Camera object.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param player Player object to follow
	 */	
	public Camera(int x, int y, Player player) {
		this.x = (int) player.getX()-width/2;
		this.y = (int) player.getY()-height/2;
		this.player = player;
	}
	
	/**
	 *  Initializes camera dimensions to match game window's.
	 *  
	 *  @param container Gamecontainer to get width and height from
	 */
	public static void init(GameContainer container) {
		width = container.getWidth();
		height = container.getHeight();
	}
	
	/**
	 * Updates this object's state.
	 * Checks if player has gone outside set up bounds and adjusts
	 * position accordingly.
	 * 
	 * @param container Obtain input from this
	 * @param delta Time
	 */
	public void update() {
		if (player.getX() < this.x + minDistanceToEdgeX) {
			increaseX(player.getX() - (x + minDistanceToEdgeX));
		} else { 
			if (player.getX() + player.getWidth() > x + width - minDistanceToEdgeX) {
				increaseX(player.getX() + player.getWidth() - (x + width - minDistanceToEdgeX));
			}
		}
		
		if (player.getY() < this.y + minDistanceToEdgeY) {
			increaseY(player.getY() - (y + minDistanceToEdgeY));
		} else { 
			if (player.getY() + player.getHeight() > y + height - minDistanceToEdgeY) {
				increaseY(player.getY() + player.getHeight() - (y + height - minDistanceToEdgeY));
			}
		}
			
	}
	
	public void increaseX(float increment) {
		x += increment;
	}
	
	public void increaseY(float increment) {
		y += increment;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * Checks if e is on screen
	 * 
	 * @param e Checked entity object
	 * @return True if e is on screen
	 */
	public boolean isEntityOnScreen(Entity e) {
		return 	(e.getX() < x + width && e.getX() + e.getWidth() > x) ||
				(e.getY() < y + height && e.getY() + e.getHeight() > y);

	}
}
