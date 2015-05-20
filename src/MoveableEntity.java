import org.newdawn.slick.SlickException;


public class MoveableEntity extends Entity {
	
	public MoveableEntity(int x, int y, String path, boolean solid) throws SlickException {
		super(x,y,path,solid);
	}
	
	/**
	 * Return whether this and e are colliding.
	 * Returns false if either of these are non-solid.
	 * 
	 * @return Are this and e colliding?
	 */
	public boolean isColliding() {
		//this.hitbox.setLocation(x - xOffset, y - yOffset);
		for (Entity entity : Map.getEntities()){
			if (!entity.isSolid()) {
				return false;
			} 
			if(entity.hitbox.intersects(entity.hitbox)){
				System.out.println("Intersect!");	
			}
		}

		return hitbox.contains(this.hitbox);
	}
}
