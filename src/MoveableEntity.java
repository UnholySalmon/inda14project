import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class extends Entity's functionality by adding velocity along both axis.
 * A field jumping keeps track whether objects are airborne.
 * Instances of MoveableEntity can perform collision checks and move accordingly.
 */
public class MoveableEntity extends Entity {
	
	private float xspeed = 0, yspeed = 0;
	private boolean jumping = true;
	
	/**
	 * Create a new MoveableEntity.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param img Image
	 * @param solid Can Entity objects pass through this object?
	 */
	public MoveableEntity(int x, int y, Image img, boolean solid) {
		super(x,y,img,solid);
	}
	
	/**
	 * Create a new MoveableEntity.
	 * A shortcut constructor which accepts
	 * a path to an image rather than an image.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param path Path to image
	 * @param solid Can Entity objects pass through this object?
	 */
	public MoveableEntity(int x, int y, String path, boolean solid) throws SlickException {
		this(x,y,new Image(path),solid);
	}
	
	public float getXSpeed() {
		return xspeed;
	}
	
	public float getYSpeed() {
		return yspeed;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public void setXSpeed(float xspeed) {
		this.xspeed = xspeed;
	}
	
	public void setYSpeed(float yspeed) {
		this.yspeed = yspeed;
	}
	
	/**
	 * Perform collision checks across all entities in the current map.
	 * A list of collided entities is returned.
	 * The list can be used to check if colliding entities should also explode, die etc.
	 * 
	 * @return A list of entities this collided with.
	 */
	public ArrayList<Entity> checkCollision() {
		ArrayList<Entity> entities = Map.getEntities();
		ArrayList<Entity> collidingEntities = new ArrayList<Entity>();
		for (Entity e : entities)
			if (isColliding(e))
				collidingEntities.add(e);
		return collidingEntities;
	}
	
	/**
	 * Returns whether two entities are colliding.
	 * They move this accordingly.
	 * 
	 * Returns false if e == this.
	 * 
	 * @param e Other entity to check
	 * @return Are this and e colliding?
	 */
	public boolean isColliding(Entity e) {
		if (e == this) return false;
		
		// check if this and e are colliding horizontally
		if (isCollidingX(e,xspeed) && isCollidingY(e,0)) {
			
			// if we collided from the left
			if (xspeed > 0) {
				setX(e.getX()-getWidth());
				xspeed = 0;
				return true;
			} else
			// if we collided from the right
			if (xspeed < 0) {
				setX(e.getX()+e.getWidth());
				xspeed = 0;
				return true;
			}
			
		} else
		// check if this and e are colliding vertically
		if (isCollidingY(e,yspeed) && isCollidingX(e,0)) {
						
			// if we collided from above
			if (yspeed > 0) {
				setY(e.getY()-getHeight());
				jumping = false;
				yspeed = 0;
				return true;
			} else
				// if we collided from underneath
			if (yspeed < 0) {
				setY(e.getY()+e.getHeight());
				yspeed = 0;
				return true;
			}
			
		}
		// return false if no collision happened on either axis
		return false;
	}
	
	/**
	 * Check if this and e are colliding horizontally.
	 * Can also predict collision between this and e.
	 * 
	 * @param e Other entity to check
	 * @param xspeed This object's horizontal speed
	 * @return Are this and e colliding horizontally?
	 */
	private boolean isCollidingX(Entity e, float xspeed) {
		return getX() + getWidth() + xspeed > e.getX()
			&& getX() + xspeed < e.getX() + e.getWidth();
	}
	
	/**
	 * Check if this and e are colliding vertically.
	 * Can also predict collision between this and e.
	 * 
	 * @param e Other entity to check
	 * @param yspeed This object's vertical speed
	 * @return Are this and e colliding vertically?
	 */
	private boolean isCollidingY(Entity e, float yspeed) {
		return getY() + getHeight() + yspeed > e.getY()
			&& getY() + yspeed < e.getY() + e.getHeight();
	}
	
}
