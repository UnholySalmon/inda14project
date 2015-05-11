import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	private Tile t; // For testing purposes
	private ArrayList<Entity> entities;
	//private Player player;
	private InputHandler input;
	
	public Game(String title, Input input) {
		super(title);
		this.input = new InputHandler(input);
	}
	
	public void init(GameContainer container) throws SlickException {
		t = new Tile(2,3,"res/test.png");
		entities = new ArrayList<Entity>();
		//player = new Player(64, 64, "res/pika.png");
		//entities.add(player);
		//input = new InputHandler(,player);
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		for (Entity entity : entities) {
			entity.update();
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		t.draw(); // For testing purposes
		
		for (Entity entity : entities) {
			entity.draw();
		}	
	}
}	

