import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;

public class Player {
	
	private Input input;
	private AppGameContainer app;
	
	public Player(AppGameContainer app) {
		this.app = app;
		input = app.getInput();
	}
	
	public void checkInput() {
		if(input.isKeyPressed(Input.KEY_SPACE)) {jump();}
		if(input.isKeyDown(Input.KEY_LEFT)) {move("left");}
		if(input.isKeyDown(Input.KEY_RIGHT)) {move("right");}
		
		
	}
	
	private void move(String direction) {
		// TODO
	}
	
	private void jump() {
		// TODO 
	}
}
