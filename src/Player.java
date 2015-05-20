import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends MoveableEntity {
	

	private final int movementSpeed = 12;
	private int speedY;

	private final float MOVEMENTSPEED = 10;
	private final float JUMPSPEED = 10;

	
	public Player(int x, int y, String path) throws SlickException {
		this(x,y,new Image(path));
	}
	
	public Player(int x, int y, Image img) {
		super(x,y,img,true);
	}
	
	public void update(GameContainer container, int delta) {
		handleInput(container.getInput(), delta);
		this.hitbox.setLocation(this.getX(), this.getY());
	}
	
	private void handleInput(Input input, int delta) {
		float xspeed = 0, yspeed = 0;
		
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
			xspeed -= (MOVEMENTSPEED * (float) Tile.SIZE * delta) / 1_000;
		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
			xspeed += (MOVEMENTSPEED * (float) Tile.SIZE * delta) / 1_000;
		
		increaseX(xspeed);
		increaseY(yspeed);
	}
	
}
