import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Moving platform player can stand on in level
 *
 */
public class Platform extends MoveableEntity {
	
	private static final int MOVEMENTSPEED = 3;
	
	private float origX;
	private float origY;
	private int distance;
	private boolean vertical;
	private boolean returning;
	
	/**
	 * Create a new platform object
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param img Image used to represent platform on screen
	 * @param vertical Set to true if platform shall move vertically
	 * @param numTiles Number of tiles platform moves
	 */
	public Platform(int x, int y, Image img, boolean vertical, int numTiles) {
		super(x,y,img,true,false);
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
	/**
	 * Create a new platform object. Alternate constructor using path to 
	 * create a new Iamge object.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param path Path to create image used to represent platform on screen
	 * @param vertical Set to true if platform shall move vertically
	 * @param numTiles Number of tiles platform moves
	 */
	public Platform(int x, int y, String path, boolean vertical, int numTiles) throws SlickException {
		this(x,y,new Image(path),vertical,numTiles);
	}
	
	/**
	 * Update state of platform object
	 * 
	 * @param container Unused but needed to override update from super class
	 * @param delta Time since last update
	 */
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
	
	public boolean isVertical() {
		return vertical;
	}
	
}
