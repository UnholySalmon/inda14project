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
	private static ArrayList<Entity> entities;
	
	// these determine which color corresponds to which image
	private static Color BRICKSCOLOR, DIRTCOLOR, DIRTDEEPCOLOR, EMPTYCOLOR, FAKEBRICKSCOLOR, FINISHCOLOR, LAVACOLOR, LAVADEEPCOLOR, 
	PLATFORMCOLOR, PLAYERCOLOR, SPIKESCOLOR;
	private static Image BACKGROUND, BRICKSIMAGE, DIRTIMAGE, DIRTDEEPIMAGE, EMPTYIMAGE, FINISHIMAGE, 
	LAVAIMAGE, LAVADEEPIMAGE, PLATFORMIMAGE, PLAYERHITBOXIMAGE, SPIKESIMAGE;
	
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
		entities = getEntities(img);
		
		for (Entity e : entities) {
			if (e instanceof Player) {
				camera = new Camera(0,0,(Player)e);
			}
		}
	}
	
	/**
	 * Initialize colors and images which correspond with each other.
	 * Call this method before creating maps.
	 */
	public static void init(GameContainer container) {
		BRICKSCOLOR = new Color(0,0,0);
		DIRTCOLOR = new Color(255,128,0);
		DIRTDEEPCOLOR = new Color(128,128,0);
		EMPTYCOLOR = new Color(0,128,255);
		FAKEBRICKSCOLOR = new Color(64,64,64);
		FINISHCOLOR = new Color(255,128,128);
		LAVACOLOR = new Color(255,0,0);
		LAVADEEPCOLOR = new Color(255,0,255);
		PLATFORMCOLOR = new Color(0,128,0);
		PLAYERCOLOR = new Color(0,0,255);
		SPIKESCOLOR = new Color(128,128,128);
		
		try {
			BACKGROUND = new Image("res/background.png");
			BRICKSIMAGE = new Image("res/bricks.png");
			DIRTIMAGE = new Image("res/dirt.png");
			DIRTDEEPIMAGE = new Image("res/dirtdeep.png");
			EMPTYIMAGE = new Image("res/empty.png");
			FINISHIMAGE = new Image("res/finish.png");
			LAVAIMAGE = new Image("res/lava.png");
			LAVADEEPIMAGE = new Image("res/lavadeep.png");
			PLATFORMIMAGE = new Image("res/platform.png");
			PLAYERHITBOXIMAGE = new Image("res/playerhitbox.png");
			SPIKESIMAGE = new Image("res/spikes.png");
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
	public static ArrayList<Entity> getEntities(Image img) {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		int w = img.getWidth(),
			h = img.getHeight();
		
		// iterate all pixels in img.
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				// compare the color of pixel (x,y) and create corresponding object
				Color pixel = img.getColor(x,y);
				
				if (compareColor(pixel,BRICKSCOLOR)) {
					
					entities.add(new Tile(x,y,BRICKSIMAGE,true,false,false));
					
				} else if (compareColor(pixel,DIRTCOLOR)) {
					
					entities.add(new Tile(x,y,DIRTIMAGE,true,false,false));
					
				} else if (compareColor(pixel,DIRTDEEPCOLOR)) {
					
					entities.add(new Tile(x,y,DIRTDEEPIMAGE,true,false,false));
					
				} else if (compareColor(pixel,EMPTYCOLOR)) {
					
					entities.add(new Tile(x,y,EMPTYIMAGE,true,false,false));
					
				} else if (compareColor(pixel,FAKEBRICKSCOLOR)) {
					
					entities.add(new Tile(x,y,BRICKSIMAGE,false,false,false));
					
				} else if (compareColor(pixel,FINISHCOLOR)) {
					
					entities.add(new Tile(x,y,FINISHIMAGE,true,false,true));
					
				}  else if (compareColor(pixel,LAVACOLOR)) {
					
					entities.add(new Tile(x,y,LAVAIMAGE,true,true,false));
					
				} else if (compareColor(pixel,LAVADEEPCOLOR)) {
					
					entities.add(new Tile(x,y,LAVADEEPIMAGE,true,true,false));
					
				} else if (compareColor(pixel,PLATFORMCOLOR)) {
					
					entities.add(new Platform(x*Tile.SIZE,y*Tile.SIZE,PLATFORMIMAGE,false,5));
					
				} else if (compareColor(pixel,PLAYERCOLOR)) {
					
					Player p = new Player(x*Tile.SIZE,y*Tile.SIZE,PLAYERHITBOXIMAGE);
					p.init();
					entities.add(p);
					
				} else if (compareColor(pixel,SPIKESCOLOR)) {
					
					entities.add(new Tile(x,y,SPIKESIMAGE,true,true,false));
					
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
	public static boolean compareColor(Color c1, Color c2) {
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
		for (Entity e : entities)
			e.update(container, delta);
		camera.update();
	}
	
	/**
	 * Render all entities in the map.
	 */
	public void render() {
		BACKGROUND.draw(-camera.getX(),-camera.getY());
		Player p = null;
		for (Entity e : entities) {
			if (e instanceof Player) {
				p = (Player)e;
			}
			// Renders entity if currently on screen
			if (camera.isEntityOnScreen(e)) {
				e.render(e.getX() - camera.getX(), e.getY() - camera.getY());
			}
		}
		// render player last to keep player in front
		p.render(p.getX() - camera.getX(), p.getY() - camera.getY());
	}
	
	/**
	 * Return a list of entities in the current map.
	 * 
	 * @return The list
	 */
	public static ArrayList<Entity> getEntities(){
		return entities;
	}
	
}
