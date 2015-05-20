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
		// move this and e's hitboxes
		hitbox.setCenterX(hitbox.getCenterX()+xspeed);
		hitbox.setCenterY(hitbox.getCenterY()+yspeed);
		if (e instanceof MoveableEntity) {
			e.hitbox.setCenterX(e.hitbox.getCenterX()+((MoveableEntity)e).xspeed);
			e.hitbox.setCenterY(e.hitbox.getCenterY()+((MoveableEntity)e).yspeed);
		}
		
		// check for collision here
		// if xspeed > 0, the collision happened at left side of e,
		// so this has to be placed beside e
		
		// if yspeed > 0, the collision happened at upper side of e,
		// so this has to be placed on top of e
		
		//return if any collision happened
		return false;
	}
	
	/**
	 * Return whether this and e are colliding.
	 * Returns false if either of these are non-solid.
	 * 
	 * @return Are this and e colliding?
	 */
	public void checkCollision() {
		//System.out.println("Collision check");
		for (Entity entity : Map.getEntities()){
			if (!entity.isSolid() || entity == this) {
				continue;
			} 
			if(this.hitbox.intersects(entity.hitbox)){
				
				//if (this.getXSpeed() >= 0) {
				//	this.increaseX(entity.hitbox.getMinX()-this.hitbox.getMaxX()); 
				//} else { 
				//	this.increaseX(entity.hitbox.getMaxX()-this.hitbox.getMinX());
				//}

				if (this.getYSpeed() >= 0) {
					this.increaseY(entity.hitbox.getMinY()-this.hitbox.getMaxY()); 
				} else { 
					this.increaseY(entity.hitbox.getMaxY()-this.hitbox.getMinY());
				}
			}
		}

//		hitbox.contains(this.hitbox);
	}
}
