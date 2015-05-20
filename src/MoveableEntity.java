import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
		return isCollidingX(e) || isCollidingY(e);
	}
	
	private boolean isCollidingX(Entity e) {
		//hitbox.setCenterX(hitbox.getCenterX()+xspeed);
		//if (e instanceof MoveableEntity)
		//	e.hitbox.setCenterX(e.hitbox.getCenterX()+((MoveableEntity)e).xspeed);
		
		if (hitbox.contains(e.hitbox)) {
			if (xspeed > 0) increaseX(hitbox.getMaxX()-e.hitbox.getMinX());
			else increaseX(hitbox.getMinX()-e.hitbox.getMaxX());
			xspeed = 0;
			return true;
		}
		return false;
	}
	
	private boolean isCollidingY(Entity e) {
		//hitbox.setCenterY(hitbox.getCenterY()+yspeed);
		//if (e instanceof MoveableEntity)
		//	e.hitbox.setCenterY(e.hitbox.getCenterY()+((MoveableEntity)e).yspeed);
		
		if (hitbox.contains(e.hitbox)) {
			if (yspeed > 0) increaseY(hitbox.getMaxY()-e.hitbox.getMinY());
			else increaseY(hitbox.getMinY()-e.hitbox.getMaxY());
			yspeed = 0;
			return true;
		}
		return false;
	}
	
	public ArrayList<Entity> checkCollision() {
		ArrayList<Entity> entities = Map.getEntities();
		ArrayList<Entity> collidingEntities = new ArrayList<Entity>();
		for (Entity e : entities)
			if (isColliding(e))
				collidingEntities.add(e);
		//System.out.println(collidingEntities.size());
		return collidingEntities;
	}
}
