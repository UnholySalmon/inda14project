import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map {
	
	private Image img;
	private static ArrayList<Entity> entities;
	private static Image WALLIMAGE;
	public static Color WALLCOLOR = new Color(0,0,0);
	
	public Map(String path) {
		try {
			img = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		entities = getEntities(img);
	}
	
	private ArrayList<Entity> getEntities(Image img) {
		try {
			WALLIMAGE = new Image("res/wall.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		ArrayList<Entity> entities = new ArrayList<Entity>();
		int w = img.getWidth(),
			h = img.getHeight();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (compareColor(img.getColor(x,y),WALLCOLOR)) {
					entities.add(new Tile(x,y,WALLIMAGE,true));
				}
			}
		}
		return entities;
	}
	
	private static boolean compareColor(Color c1, Color c2) {
		return c1.getRed() == c2.getRed()
			&& c1.getGreen() == c2.getGreen()
			&& c1.getBlue() == c2.getBlue();
	}
	
	public void update(int delta) {
		for (Entity e : entities) {
			e.update(delta);
		}
	}
	
	public void render() {
		for (Entity e : entities) {
			e.render();
		}
	}
	
}
