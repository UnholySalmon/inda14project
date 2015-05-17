import org.newdawn.slick.Input;

/**
 * The game world loads and stores all maps.
 * It understands which map to update or render.
 */
public class World {
	
	// currentMap keeps track of which map we are currently in
	private static Map currentMap;
	private static Map map1;
	// we would have to declare Map objects here to make more levels
	
	/**
	 * Initialize all maps, and store the first map in currentMap
	 */
	public static void init(Input input) {
		map1 = new Map("res/map1.png", input);
		currentMap = map1;
		
	}
	
	/**
	 * Update the current map.
	 * 
	 * @param delta Has to be passed to the objects.
	 */
	public static void update(int delta) {
		currentMap.update(delta);
	}
	
	/**
	 * Render the current map.
	 */
	public static void render() {
		currentMap.render();
	}
	
}
