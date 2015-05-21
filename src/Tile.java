import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Extends Entity, and thus shares its properties.
 * Holds the definitive size of all tile objects.
 */
public class Tile extends Entity {
	
	public static final int SIZE = 32;
	private int row, column;
	
	/**
	 * Create a new Tile.
	 * 
	 * @param row Row
	 * @param column Column
	 * @param img Image
	 * @param solid Can Entity objects pass through this object?
	 * @param deadly Will Player instances die upon colliding with this object?
	 */
	public Tile(int row, int column, Image img, boolean solid, boolean deadly) {
		super(row*SIZE, column*SIZE, img, solid, deadly);
	}
	
	/**
	 * Create a new Tile.
	 * A shortcut constructor which accepts
	 * a path to an image rather than an image.
	 * 
	 * @param row Row
	 * @param column Column
	 * @param path Path to image
	 * @param solid Can Entity objects pass through this object?
	 * @param deadly Will Player instances die upon colliding with this object?
	 */
	public Tile(int row, int column, String path, boolean solid, boolean deadly) throws SlickException {
		this(row*SIZE, column*SIZE, new Image(path), solid, deadly);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
}
