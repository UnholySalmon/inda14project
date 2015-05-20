import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Extends Entity, and thus shares its properties.
 * Holds the definitive size of all tile objects.
 */
public class TileEntity extends Entity {
	
	public static final int SIZE = 32;
	private int row, column;
	
	/**
	 * Create a new Tile.
	 * 
	 * @param row Row
	 * @param column Column
	 * @param img Image
	 * @param solid Can Entity objects pass through this object?
	 */
	public TileEntity(int row, int column, Image img, boolean solid) {
		super(row*SIZE, column*SIZE, img.getSubImage(0,0,SIZE,SIZE), solid);
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
	 */
	public TileEntity(int row, int column, String path, boolean solid) throws SlickException {
		this(row*SIZE, column*SIZE, new Image(path), solid);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
}
