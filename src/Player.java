import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	
	private final int movementSpeed = 10;
	private int speedY;
	
	public Player(int x, int y, String path) throws SlickException {
		super(x,y,path,true);
	}
	
	public void update(GameContainer container, int delta) {
		System.out.println(this.getX());
		System.out.println(this.getY());
		handleInput(container.getInput(), delta);
	}
	
	private void handleInput(Input input, int delta) {
		if (input.isKeyDown(Input.KEY_LEFT)) {
			int pixelsMovedInX = (movementSpeed * Tile.SIZE * delta) / 1000; 
			increaseX(-pixelsMovedInX); // Negative value
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			int pixelsMovedInX = (movementSpeed * Tile.SIZE * delta) / 1000; 
			increaseX(pixelsMovedInX);
		}
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			speedY += 20;
		}
		increaseY(speedY);
		if (getY()<=0) {
			speedY = 0;
		} else {
			speedY -= 1;
		}
	}
}