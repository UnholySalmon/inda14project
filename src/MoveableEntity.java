import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class MoveableEntity extends Entity {
	
	private float xspeed = 0, yspeed = 0;
	
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
	
	public void setXSpeed(float xspeed) {
		this.xspeed = xspeed;
	}
	
	public void setYSpeed(float yspeed) {
		this.yspeed = yspeed;
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
		boolean collisionY = isCollidingY(e),
			collisionX = isCollidingX(e);
		return collisionX || collisionY;
	}
	
	private boolean isCollidingX(Entity e) {
		//hitbox.setCenterX(hitbox.getCenterX()+xspeed);
		//if (e instanceof MoveableEntity)
		//	e.hitbox.setCenterX(e.hitbox.getCenterX()+((MoveableEntity)e).xspeed);
		
		Rectangle thishitbox = getHitbox();
		Rectangle ehitbox = e.getHitbox();
		
		if (thishitbox.intersects(ehitbox)) {
			if (xspeed > 0) setX(ehitbox.getMinX()-getWidth()-1);
			else if (xspeed < 0) setX(ehitbox.getMaxX()+1);
			return true;
		}
		
		//hitbox.setCenterX(hitbox.getCenterX()-xspeed);
		//if (e instanceof MoveableEntity)
		//	e.hitbox.setCenterX(e.hitbox.getCenterX()-((MoveableEntity)e).xspeed);
		return false;
	}
	
	private boolean isCollidingY(Entity e) {
		//hitbox.setCenterY(hitbox.getCenterY()+yspeed);
		//if (e instanceof MoveableEntity)
		//	e.hitbox.setCenterY(e.hitbox.getCenterY()+((MoveableEntity)e).yspeed);
		
		Rectangle thishitbox = getHitbox();
		Rectangle ehitbox = e.getHitbox();
		
		if (thishitbox.intersects(ehitbox)) {
			if (yspeed > 0) setY(ehitbox.getMinY()-getHeight()-1);
			else if (yspeed < 0) setY(ehitbox.getMaxY()+1);
			return true;
		}
		
		//hitbox.setCenterY(hitbox.getCenterY()-yspeed);
		//if (e instanceof MoveableEntity)
		//	e.hitbox.setCenterY(e.hitbox.getCenterY()-((MoveableEntity)e).yspeed);
		return false;
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
