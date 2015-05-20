import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends MoveableEntity {
	
	private final int movementSpeed = 10;
	private int speedY;
	
	public Player(int x, int y, String path) throws SlickException {
		this(x,y,new Image(path));
	}
	
	public Player(int x, int y, Image img) {
		super(x,y,img,true);
	}
	
	public void update(GameContainer container, int delta) {
//		System.out.println(this.getX());
//		System.out.println(this.getY());
		handleInput(container.getInput(), delta);
		this.hitbox.setLocation(this.getX(), this.getY());
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
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			speedY += 1;
		}
		increaseY(speedY);
		if (getY()<=0) {
			speedY = 0;
		} else {
			speedY -= 1;
		}
	}
}