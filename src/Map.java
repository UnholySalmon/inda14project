import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Map instances hold the state of all its entities.
 * Objects will load an image,
 * where the color of each pixel determines the looks of the map.
 */
public class Map {
	
	private Input input;	
	private Image img;
	// store all objects in the map
	private ArrayList<Entity> entities;
	private int mapHeight;
	private int mapWidth;
	private Camera camera;
	
	// these determine which color corresponds to which image
	private static Color WALLCOLOR;
	private static Image WALLIMAGE;
	private static Image BACKGROUND;
	// here we would add more tiles to use in maps
	
	/**
	 * Create a new Map.
	 * 
	 * @param path Path to find map image.
	 */
	public Map(String path, Input input) {
		
		this.input = input;
		try {
			img = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		mapHeight = img.getHeight()*Tile.SIZE;
		mapWidth = img.getWidth()*Tile.SIZE;
		entities = getEntities(img);
		
		for (Entity e : entities)
			if (e instanceof Player)
				camera = new Camera(0,0,e);
	}
	
	/**
	 * Initialize colors and images which correspond with each other.
	 * Call this method before creating maps.
	 */
	public static void init() {
		WALLCOLOR = new Color(0,0,0);
		try {
			BACKGROUND = new Image("res/background.png");
			WALLIMAGE = new Image("res/wall.png");
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * A help method used by the constructor.
	 * Takes a map image and returns a list of entities.
	 * 
	 * @param img Image
	 * @return A list of entities defined in img.
	 */
	private ArrayList<Entity> getEntities(Image img) {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		int w = img.getWidth(),
			h = img.getHeight();
		//for testing
		try {
			Player player = new Player(1280,360, new Image("res/pika.png"), input);
			entities.add(player);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		// Titerate all pixels in img.
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				// compare the color of pixel (x,y)
				if (compareColor(img.getColor(x,y),WALLCOLOR)) {
					entities.add(new Tile(x,y,WALLIMAGE,true));
				}
			}
		}
		return entities;
	}
	
	/**
	 * Help method used by getEntities to compare color c1 and c2.
	 * Returns false if c1 or c2 is null.
	 * 
	 * @param c1 Color object
	 * @param c2 Color object to compare with
	 * @return Do c1 and c2 share RGB values?
	 */
	private static boolean compareColor(Color c1, Color c2) {
		if (c1 == null || c2 == null) return false;
		return c1.getRed() == c2.getRed()
			&& c1.getGreen() == c2.getGreen()
			&& c1.getBlue() == c2.getBlue();
	}
	
	/**
	 * Update all entities in the map.
	 * 
	 * @param delta Has to be passed to the objects.
	 */
	public void update(int delta) {
		for (Entity e : entities) {
			e.update(delta);
		}
		camera.update();
	}
	
	/**
	 * Render all entities in the map.
	 */
	public void render() {
		BACKGROUND.draw(-camera.getX(),-camera.getY());
		for (Entity e : entities)
			if (camera.isEntityOnScreen(e))
				e.render(e.getX() - camera.getX(), e.getY() - camera.getY());
	}
}
