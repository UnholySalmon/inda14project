import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	
	
	private Input input;
	private final int movementSpeed = 10;
	private int speedY;
	
	public Player(int x, int y, Image img, Input input) throws SlickException {
		super(x,y,img,true);
		this.input = input;
	}
	
	public Player(int x, int y, String path, Input input) throws SlickException {
		super(x,y,path,true);
		this.input = input;
		
	}
	
	public void update(int delta) {
		handleInput(delta);
	}
	
	private void handleInput(int delta) {
		if (input.isKeyDown(Input.KEY_LEFT)) {
			int pixelsMovedInX = (movementSpeed * Tile.SIZE * delta) / 1000; 
			this.increaseX(-pixelsMovedInX); // Negative value
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			int pixelsMovedInX = (movementSpeed * Tile.SIZE * delta) / 1000; 
			this.increaseX(pixelsMovedInX);
		}
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			speedY += 20;
		}
		this.increaseY(speedY);
		if (this.getY()<=0) {
			speedY = 0;
		} else {
			speedY -= 1;
		}
	}
}