import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Platform extends MoveableEntity {
	
	
	
	public Platform(int x, int y, String path, boolean solid, boolean horizontal, int distance) throws SlickException {
		super(x,y,path,solid);
	}
	
	public Platform(int x, int y, Image img, boolean solid, boolean horizontal, int distance)  {
		super(x,y,img,solid);
	}
	
	
}
