import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends MoveableEntity {
	
	private final float MOVEMENTSPEED = 5;
	private final float JUMPSPEED = 1;
	
	private float xspeed = 0, yspeed = 0;
	
	private Animation anim;
	private Image standing;
	
	public Player(int x, int y, String path) throws SlickException {
		this(x,y,new Image(path));
	}
	
	public Player(int x, int y, Image img) {
		super(x,y,img,true);
	}
	
	public void init() {
		
		try {
			standing = new Image("res/playerstand.png");
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
		
		SpriteSheet ss = null;
		try {
			ss = new SpriteSheet(new Image("res/playerrun.png"),64,64);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		anim = new Animation(ss, 150);
		anim.setAutoUpdate(false);
	}
	
	public void update(GameContainer container, int delta) {
		handleInput(container.getInput(), delta);
		this.hitbox.setLocation(this.getX(), this.getY());
		render();
	}
	
	private void handleInput(Input input, int delta) {
		
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			xspeed = -(float) (MOVEMENTSPEED * Tile.SIZE * delta) / 1_000;
			anim.update(delta);
		} else if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			xspeed = (float) (MOVEMENTSPEED * Tile.SIZE * delta) / 1_000;
			anim.update(delta);
		} else {
			xspeed = 0;
		}
		
		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_SPACE))
			yspeed -= JUMPSPEED;
		
		increaseX(xspeed);
		increaseY(yspeed);
	}
	
	public void render() {
		if (xspeed > 0)
			setImg(anim.getCurrentFrame().getFlippedCopy(true,false));
		else if (xspeed < 0)
			setImg(anim.getCurrentFrame());
		else
			setImg(standing);
	}
	
}
