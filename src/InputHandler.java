import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;

public class InputHandler {
	
	private AppGameContainer app; //May not be needed
	private Input input;
	private Player player;
	
	public InputHandler(AppGameContainer app, Player player) {
		this.app = app;
		input = app.getInput();
	}
	
	public void checkInput() {
		if(input.isKeyPressed(Input.KEY_SPACE)) {player.jump();}
		if(input.isKeyDown(Input.KEY_LEFT)) {player.move("left");}
		if(input.isKeyDown(Input.KEY_RIGHT)) {player.move("right");}
	}
}
