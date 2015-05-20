import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Map instances hold the state of all its entities.
 * Objects will load an image,
 * where the color of each pixel determines the looks of the map.
 */
public class Map {
	
	private static Camera camera;	
	private Image img;
	// store all objects in the map
	private static Tile[][] entities;
	private int mapHeight;
	private int mapWidth;
	
	// these determine which color corresponds to which image
	private static Color WALLCOLOR;
	private static Color PLAYERCOLOR;
	private static Image PLAYERIMAGE;
	private static Image WALLIMAGE;
	private static Image BACKGROUND;
	// here we would add more tiles to use in maps
	
	/**
	 * Create a new Map.
	 * 
	 * @param path Path to find map image.
	 */
	public Map(String path) {
		
		try {
			img = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		mapHeight = img.getHeight()*TileEntity.SIZE;
		mapWidth = img.getWidth()*TileEntity.SIZE;
		entities = getEntities(img);
		
		for (Tile[] t : entities) {
			for (Tile tile : t) {
				for (Entity e : tile.getEntities()) {
					if (e == null) continue;
					if (e instanceof Player)
					camera = new Camera(0,0,(Player)e);
					}
				}
			}
	}
	
	/**
	 * Initialize colors and images which correspond with each other.
	 * Call this method before creating maps.
	 */
	public static void init(GameContainer container) {
		WALLCOLOR = new Color(0,0,0);
		PLAYERCOLOR = new Color(255,0,0);
		try {
			BACKGROUND = new Image("res/background.png");
			WALLIMAGE = new Image("res/bricks.png");
			PLAYERIMAGE = new Image("res/playerstand.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Camera.init(container);
		
	}
	
	/**
	 * A help method used by the constructor.
	 * Takes a map image and returns a list of entities.
	 * 
	 * @param img Image
	 * @return A list of entities defined in img.
	 */
	public static Tile[][] getEntities(Image img) {
		int w = img.getWidth(),
			h = img.getHeight();
		
		Tile[][] entities = new Tile[w][h];
		
		// iterate all pixels in img.
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				entities[x][y] = new Tile();
				// compare the color of pixel (x,y)
				if (compareColor(img.getColor(x,y),WALLCOLOR)) {
					entities[x][y].getEntities().add(new TileEntity(x,y,WALLIMAGE,true));
					//entities.add(new TileEntity(x,y,WALLIMAGE,true));
				} else if (compareColor(img.getColor(x,y),PLAYERCOLOR)) {
					Player p = new Player(x*TileEntity.SIZE,y*TileEntity.SIZE,PLAYERIMAGE);
					p.init();
					entities[x][y].getEntities().add(p);
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
	public void update(GameContainer container, int delta) {
		for (Tile[] t : entities) {
			for (Tile tile : t) {
				for (Entity e : tile.getEntities()) {
					e.update(container, delta);
					if (e instanceof MoveableEntity) {
						((MoveableEntity)e).checkCollision();
					}
				}
			}
		}
		camera.update();
		
	}
	
	/**
	 * Render all entities in the map.
	 */
	public void render() {
		BACKGROUND.draw(-camera.getX(),-camera.getY());
		for (Tile[] t : entities) {
			for (Tile tile : t) {
				for (Entity e : tile.getEntities()) {
					if (camera.isEntityOnScreen(e))
						e.render(e.getX() - camera.getX(), e.getY() - camera.getY());
				}
			}
		}
	}
	
	public static  Tile[][] getEntities(){
		return entities;
	}
	
}
