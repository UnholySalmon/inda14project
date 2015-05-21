import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * All objects in the game world are represented by Entity.
 * The shared properties allow for easily implemented updating, rendering and collision detection.
 */
public class Entity {
	
	private float x,y;
	private int width,height;
	private Image img;
	private boolean solid;
	
	/**
	 * Create a new Entity.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param img Image
	 * @param solid Can Entity objects pass through this object?
	 */
	public Entity(int x, int y, Image img, boolean solid) {
		this.x = x;
		this.y = y;
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
		this.solid = solid;
	}
	
	public Entity(int x, int y, String path, boolean solid) throws SlickException {
		this(x,y,new Image(path), solid);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void increaseX(float increment) {
		x += increment;
	}
	
	public void increaseY(float increment) {
		y += increment;
	}
	
	public Image getImage() {
		return img;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public Rectangle getHitbox(){
		return new Rectangle(x,y,width,height);
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
	
	/**
	 * Update this object, taking the time delta into account.
	 * 
	 * @param delta Time
	 */
	public void update(GameContainer container, int delta) {
		
	}
	
	/**
	 * Render image at (x,y).
	 */
	public void render(float x, float y) {
		img.draw(Math.round(x),Math.round(y));
	}
	
}
