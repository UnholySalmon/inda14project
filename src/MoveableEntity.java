import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class MoveableEntity extends Entity {
	
	private float xspeed = 0, yspeed = 0;
	private boolean jumping = true;
	
	public MoveableEntity(int x, int y, String path, boolean solid) throws SlickException {
		super(x,y,path,solid);
	}
	
	public MoveableEntity(int x, int y, Image img, boolean solid) {
		super(x,y,img,solid);
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
	
	public Rectangle getHitbox(float xspeed, float yspeed){
		return new Rectangle(getX()+xspeed,getY()+yspeed,getWidth(),getHeight());
	}
	
	/**
	 * Returns whether two entities are about to collide.
	 * They move this accordingly.
	 * 
	 * @param e Other entity to check
	 * @returns Are this and e colliding?
	 */
	public boolean isColliding(Entity e) {
		if (e == this) return false;
		if (isCollidingX(e,xspeed) && isCollidingY(e,0)) {
			if (xspeed > 0) {
				setX(e.getX()-getWidth());
				xspeed = 0;
				return true;
			} else if (xspeed < 0) {
				setX(e.getX()+e.getWidth());
				xspeed = 0;
				return true;
			}
		} else if (isCollidingY(e,yspeed) && isCollidingX(e,0)) {
			if (yspeed > 0) {
				setY(e.getY()-getHeight());
				jumping = false;
				yspeed = 0;
				return true;
			} else if (yspeed < 0) {
				setY(e.getY()+e.getHeight());
				yspeed = 0;
				return true;
			}
		}
		return false;
	}
	
	private boolean isCollidingX(Entity e, float xspeed) {
		return getX() + getWidth() + xspeed > e.getX()
			&& getX() + xspeed < e.getX() + e.getWidth();
	}
	
	private boolean isCollidingY(Entity e, float yspeed) {
		return getY() + getHeight() + yspeed > e.getY()
			&& getY() + yspeed < e.getY() + e.getHeight();
	}
	
	public ArrayList<Entity> checkCollision() {
		ArrayList<Entity> entities = Map.getEntities();
		ArrayList<Entity> collidingEntities = new ArrayList<Entity>();
		for (Entity e : entities)
			if (isColliding(e))
				collidingEntities.add(e);
		return collidingEntities;
	}
	
}
