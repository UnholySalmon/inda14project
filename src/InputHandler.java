import org.newdawn.slick.Input;

public class InputHandler {
	
	private Input input;
	private Player player;
	
	public InputHandler(Input input) {
		this.input = input;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void checkInput() {
		if(input.isKeyPressed(Input.KEY_SPACE)) {player.jump();}
		if(input.isKeyDown(Input.KEY_LEFT)) {player.move("left");}
		if(input.isKeyDown(Input.KEY_RIGHT)) {player.move("right");}
	}
}
