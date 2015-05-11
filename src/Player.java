import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	
	private static final int MOVEMENTSPEED = 1;
	
	public Player(int x, int y, String path) throws SlickException {
		super(x, y, path);
		
	}
	
	public void move(String direction) {
		switch (direction) {
			case "left": this.incrementX(-MOVEMENTSPEED); 
			case "right": this.incrementX(MOVEMENTSPEED);
			default: return;
		}
	}
	
	public void jump() {
		// TODO 
	}

}
