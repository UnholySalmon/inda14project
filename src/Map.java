import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map {
	
	private Image img;
	private ArrayList<Entity> entities;
	
	public Map(String path) {
		try {
			img = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		entities = getEntities(img);
	}
	
	private Color getColorAtPixel(Image img, int x, int y) {
		return new Color(img.getColor(x,y));
	}
	
	private ArrayList<Entity> getEntities(Image img) {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		int w = img.getWidth(),
			h = img.getHeight();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color c = getColorAtPixel(img,x,y);
				// add entities corresponding to c at x,y
			}
		}
		return entities;
	}
	
}
