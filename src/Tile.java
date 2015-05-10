import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile extends Entity {
	
	public static final int SIZE = 32;
	private int row, column;
	
	public Tile(int row, int column, Image img) {
		super(row*SIZE, column*SIZE, img.getSubImage(0,0,SIZE,SIZE));
	}
	
	public Tile(int row, int column, String path) throws SlickException {
		super(row*SIZE, column*SIZE, path);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
}
