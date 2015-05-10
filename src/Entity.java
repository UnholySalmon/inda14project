import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entity {
	
	private int x,y;
	private Image img;
	
	public Entity(int x, int y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public Entity(int x, int y, String path) throws SlickException {
		this(x,y,new Image(path));
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return img;
	}
	
	public void draw() {
		img.draw(x,y);
	}
	
}
