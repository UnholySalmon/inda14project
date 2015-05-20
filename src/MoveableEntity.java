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
				
//				if (this.getXSpeed() >= 0) {
//					this.increaseX(entity.hitbox.getMinX()-this.hitbox.getMaxX()); 
//				} else { 
//					this.increaseX(entity.hitbox.getMaxX()-this.hitbox.getMinX());
//				}
				if (this.getYSpeed() >= 0) {
					this.increaseY(entity.hitbox.getMinY()-this.hitbox.getMaxY()); 
				} else { 
					this.increaseY(entity.hitbox.getMaxY()-this.hitbox.getMinY());
				}
			}
		}

		hitbox.contains(this.hitbox);
	}
}
