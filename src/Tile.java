import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile extends Entity {
	
	public static final int SIZE = 32;
	private int row, column;
	
	public Tile(int row, int column, Image img, boolean solid) {
		super(row*SIZE, column*SIZE, img.getSubImage(0,0,SIZE,SIZE), solid);
	}
	
	public Tile(int row, int column, String path, boolean solid) throws SlickException {
		this(row*SIZE, column*SIZE, new Image(path), solid);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
}
