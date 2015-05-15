import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Camera {
	
	// Current coordinates 
	public int x, y;
	// Maximum allowed coordinates
	public int maxX, maxY;
	// Minimum allowed coordinates
	public int minX, minY;
	public Player player;
	
	public Camera(Player player) {
		this.player = player;	
	}
	
	public static void init() {
		// TODO
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO
	}
	
	public void render(GameContainer container, int delta) throws SlickException {
		// TODO
	}
	
	public void setX(int x) {
		// TODO
	}
	
	public void setY(int y) {
		// TODO
	}

}
