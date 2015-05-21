import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * All objects in the game world are represented by Entity.
 * The shared properties allow for easily implemented updating, rendering and collision detection.
 */
public class Entity {
	
	private float x,y;
	private int width,height;
	private Image img;
	private boolean solid, deadly;
	
	/**
	 * Create a new Entity.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param img Image
	 * @param solid Can Entity objects pass through this object?
	 * @param deadly Will Player instances die upon colliding with this object?
	 */
	public Entity(int x, int y, Image img, boolean solid, boolean deadly) {
		this.x = x;
		this.y = y;
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
		this.solid = solid;
		this.deadly = deadly;
	}
	
	/**
	 * Create a new Entity.
	 * A shortcut constructor which accepts
	 * a path to an image rather than an image.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param path Path to image
	 * @param solid Can Entity objects pass through this object?
	 * @param deadly Will Player instances die upon colliding with this object?
	 */
	public Entity(int x, int y, String path, boolean solid, boolean deadly) throws SlickException {
		this(x,y,new Image(path),solid,deadly);
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
	
	public void setImg(Image img) {
		this.img = img;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public boolean isDeadly() {
		return deadly;
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
