import java.util.ArrayList;


public class Tile {
	
	private ArrayList<Entity> heldEntities; 
	
	public Tile() {
		heldEntities = new ArrayList<Entity>();
	}
	
	public ArrayList<Entity> getEntities() {
		return heldEntities;
	}
	
}
