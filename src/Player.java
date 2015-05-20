import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	
	public Player(int x, int y, Image img) throws SlickException {
		super(x,y,img,true);
	}
	
	public Player(int x, int y, String path) throws SlickException {
		super(x,y,path,true);
	}
	
	public void update() {
		// Handle inputs from InputHandler
	}
	
}
