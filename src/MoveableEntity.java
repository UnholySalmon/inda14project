import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class MoveableEntity extends Entity {
	
	public MoveableEntity(int x, int y, String path, boolean solid) throws SlickException {
		super(x,y,path,solid);
	}
	
	public MoveableEntity(int x, int y, Image img, boolean solid) {
		super(x,y,img,solid);
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
			if (!entity.isSolid()) {
				return;
			} 
			if(this.hitbox.intersects(entity.hitbox)){
				//System.out.println("Intersect!");	
			}
		}

		hitbox.contains(this.hitbox);
	}
}
