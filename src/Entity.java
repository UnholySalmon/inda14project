import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entity {
	
	private int x,y;
	private Image img;
	private boolean solid;
	
	public Entity(int x, int y, Image img, boolean solid) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.solid = solid;
	}
	
	public Entity(int x, int y, String path, boolean solid) throws SlickException {
		this(x,y,new Image(path), solid);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void incrementX(int increment) {
		x += increment;
	}
	
	public void incrementY(int increment) {
		y += increment;
	}
	
	public Image getImage() {
		return img;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public void update(int delta) {}
	
	public void render() {
		img.draw(x,y);
	}
	
	public boolean isColliding(Entity e) {
		if (!solid || !e.solid) return false;
		// handle collision
		return false;
	}
	
}
