import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Camera {
	
	// Current coordinates 
	private int x, y;
	private static int width, height;
	// Maximum allowed coordinates
	private int maxX, maxY;
	// Minimum allowed coordinates
	private int minX, minY;
	private final int minDistanceToEdge = 200;
	
	public Player player;
	
	public Camera(int x, int y, Player player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}
	
	public static void init(GameContainer container) {
		width = container.getWidth();
		height = container.getHeight();
	}
	
	public void update() {
		if (player.getX() < this.x + minDistanceToEdge) {
			increaseX(player.getX() - (x + minDistanceToEdge));
		} else { 
			if (player.getX() + player.getWidth() > x + width - minDistanceToEdge) {
				increaseX(player.getX() + player.getWidth() - (x + width - minDistanceToEdge));
			}
		if (player.getY() < this.y + minDistanceToEdge) {
			increaseY(player.getY() - (y + minDistanceToEdge));
		} else { 
			if (player.getY() + player.getHeight() > y + height - minDistanceToEdge) {
				increaseY(player.getY() + player.getHeight() - (y + height - minDistanceToEdge));
			}
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
	
	public boolean isEntityOnScreen(Entity e) {
		return 	(e.getX() < x + width && e.getX() + e.getWidth() > x) ||
				(e.getY() < y + height && e.getY() + e.getHeight() > y);

	}
}
