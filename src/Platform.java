import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Platform extends MoveableEntity {
	
	private static final int MOVEMENTSPEED = 3;
	private float currentX;
	private float currentY;
	
	private float origX;
	private float origY;
	private int distance;
	private boolean vertical;
	private boolean returning;
	
	public Platform(int x, int y, String path, boolean vertical, int numTiles, int speed) throws SlickException {
		super(x,y,path,true);
		origX = x;
		origY = y;
		distance = numTiles*Tile.SIZE;
		this.vertical = vertical;
		returning = false;
		if (vertical) {
			this.setYSpeed(MOVEMENTSPEED);

		} else {
			this.setXSpeed(MOVEMENTSPEED);
		}
	}
	
	public Platform(int x, int y, Image img, boolean vertical, int numTiles) {
		super(x,y,img,true);
		origX = x;
		origY = y;
		distance = numTiles*Tile.SIZE;
		this.vertical = vertical;
		returning = false;
		if (vertical) {
			this.setYSpeed(MOVEMENTSPEED);

		} else {
			this.setXSpeed(MOVEMENTSPEED);
		}
	}
	
	public void update(GameContainer container, int delta) {
		// If platform moves vertically
		if (vertical) {
			increaseY((float) (getYSpeed() * Tile.SIZE * delta) / 1_000);
			// Check if reached upper bound and adjust position accordingly
			if (returning && this.getY() < origY) {
				setY(origY);
				returning = !returning;
				setYSpeed(-getYSpeed());
			} else {
				// Check if reached lower bound and adjust position accordingly
				if (origY + distance < this.getY()) {
					setY(origY + distance);
					returning = !returning;
					setYSpeed(-getYSpeed());
				}
			}
			
		// If platform moves horizontally
		} else {
				increaseX((float) (getXSpeed() * Tile.SIZE * delta) / 1_000);
				// Check if reached upper bound and adjust position accordingly
				if (returning && this.getX() < origX) {
					setX(origX);
					returning = !returning;
					setXSpeed(-getXSpeed());
				} else {
					// Check if reached lower bound and adjust position accordingly
					if (origX + distance < this.getX()) {
						setX(origX + distance);
						returning = !returning;
						setXSpeed(-getXSpeed());
					}
				}
		}
	}
	
	public void increseX(float increment) {
		currentX += increment;
	}
	
	public void increseY(float increment) {
		currentY += increment;
	}
	
	public void testMethod() {
		
	}
	
}