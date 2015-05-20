import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends MoveableEntity {
	
	private final float MOVEMENTSPEED = 5;
	private final float JUMPSPEED = 1;
	
	private Animation walkingAnim;
	private Animation idleAnim;
	private Image standingImg;
	private boolean goingLeft = false;
	
	private int idleCounter;
	
	public Player(int x, int y, String path) throws SlickException {
		this(x,y,new Image(path));
	}
	
	public Player(int x, int y, Image img) {
		super(x,y,img,true);
	}
	
	public void init() {
		
		try {
			standingImg = new Image("res/playerstand.png");
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
		
		SpriteSheet ss = null;
		try {
			ss = new SpriteSheet(new Image("res/playerrun.png"),64,64);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		walkingAnim = new Animation(ss, 150);
		walkingAnim.setAutoUpdate(false);
		
		try {
			ss = new SpriteSheet(new Image("res/playeridle.png"),64,64);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		idleAnim = new Animation(ss, 500);
		idleAnim.setAutoUpdate(false);
	}
	
	public void update(GameContainer container, int delta) {
		handleInput(container.getInput(), delta);
		this.hitbox.setLocation(this.getX(), this.getY());
		
		checkCollision();
		
		render();
	}
	
	private void handleInput(Input input, int delta) {
		
		idleCounter += delta;
		
		if (idleCounter >= 4_000) {
			idleAnim.update(delta);
		}
		
		// for testing purposes
		if (input.isKeyDown(Input.KEY_UP)) {
			this.setYSpeed(-3);
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			this.setYSpeed(3);
		} else setYSpeed(0);
		
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			goingLeft = true;
			this.setXSpeed(-(float) (MOVEMENTSPEED * TileEntity.SIZE * delta) / 1_000);
			walkingAnim.update(delta);
		} else if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			goingLeft = false;
			this.setXSpeed((float) (MOVEMENTSPEED * TileEntity.SIZE * delta) / 1_000);
			walkingAnim.update(delta);
		} else {
			this.setXSpeed(0);
		}
		
		if (this.getXSpeed() != 0 || this.getYSpeed() != 0)
			idleCounter = 0;
		
		//if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_SPACE))
		
		increaseX(this.getXSpeed());
		increaseY(this.getYSpeed());
	}
	
	public void render() {
		
		Image newImg;
		
		if (idleCounter >= 5_000)
			newImg = idleAnim.getCurrentFrame();
		else if (this.getXSpeed() == 0)
			newImg = standingImg;
		else
			newImg = walkingAnim.getCurrentFrame();
		
		if (goingLeft)
			setImg(newImg);
		else
			setImg(newImg.getFlippedCopy(true,false));
	}
	
}
