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
		currentX = x;
		currentY = y;
		origX = x;
		origY = y;
		distance = numTiles*Tile.SIZE;
		this.vertical = vertical;
		returning = false;
	}
	
	public Platform(int x, int y, Image img, boolean vertical, int numTiles) {
		super(x,y,img,true);
		currentX = x;
		currentY = y;
		origX = x;
		origY = y;
		distance = numTiles*Tile.SIZE;
		this.vertical = vertical;
		returning = false;
	}
	
	public void update(int delta) {
		if (vertical) {
			if (returning) {
			increaseY((float) (MOVEMENTSPEED * Tile.SIZE * delta) / 1_000);
			} else {
				increaseY((float) -(MOVEMENTSPEED * Tile.SIZE * delta) / 1_000);
			}
			if (currentY < origY) {
				currentY = origY;
				returning = !returning;
			} else {
				if (origY + distance < currentY ) {
					currentY = origY + distance;
					returning = !returning;
				}
			}
	
		} else {
			if (returning) {
				increaseX((float) (MOVEMENTSPEED * Tile.SIZE * delta) / 1_000);
				} else {
					increaseX((float) -(MOVEMENTSPEED * Tile.SIZE * delta) / 1_000);
				}
				if (currentX < origX) {
					currentX = origX;
					returning = !returning;
				} else {
					if (origX + distance < currentX ) {
						currentX = origX + distance;
						returning = !returning;
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
	
}
