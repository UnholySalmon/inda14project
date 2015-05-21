import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Instances of Player are the character which the player controls.
 * Objects move based off of input and plays animation.
 * The camera tracks the player.
 */
public class Player extends MoveableEntity {
	
	private final float MOVEMENTSPEED = 7.5f,
		JUMPSPEED = 3, GRAVITY = 1/30f;
	
	private Image standingImg, jumpingImg, fallingImg;
	private Animation walkingAnim, idleAnim;
	private boolean goingLeft = false;
	private int idleCounter;
	// amount of ms before the player plays it's idle animation
	private final int IDLEMS = 4_000;
	
	/**
	 * Create a new Player.
	 * 
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @param img Image
	 */
	public Player(int x, int y, Image img) {
		super(x,y,img,true);
	}
	
	/**
	 * Create a new Player.
	 * A shortcut constructor which accepts
	 * a path to an image rather than an image.
	 * 
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @param path Path to image
	 */
	public Player(int x, int y, String path) throws SlickException {
		this(x,y,new Image(path));
	}
	
	/**
	 * This method initializes resources such as images and animations.
	 * Always call this before utilizing Player objects.
	 */
	public void init() {
		SpriteSheet ss = null;
		
		try {
			standingImg = new Image("res/playerstand.png");
			jumpingImg = new Image("res/playerjump.png");
			fallingImg = new Image("res/playerfall.png");
			ss = new SpriteSheet(new Image("res/playerrun.png"),64,64);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		// create the walking animation using the spritesheet
		// and set its properties
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
	
	/**
	 * Updates this object's state.
	 * Input and collision are handled here.
	 * 
	 * @param container Obtain input from this
	 * @param delta Time
	 */
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		
		// horizontal movement happens here
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			goingLeft = true;
			// this calculation allows for MOVEMENTSPEED
			// to determine the player's speed in terms of tiles per second
			setXSpeed(-(float) (MOVEMENTSPEED * Tile.SIZE * delta) / 1_000);
			// update this animation to cycle frames
			walkingAnim.update(delta);
		} else if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			goingLeft = false;
			setXSpeed((float) (MOVEMENTSPEED * Tile.SIZE * delta) / 1_000);
			walkingAnim.update(delta);
		} else {
			setXSpeed(0);
		}
		
		// these three lines implement gravity acceleration and collision checking
		setJumping(true);
		setYSpeed(getYSpeed()+GRAVITY);
		checkCollision();
		
		if (!isJumping()) {
			if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_SPACE)) {
				setYSpeed(-JUMPSPEED);
			}
		}
		
		increaseX(getXSpeed());
		increaseY(getYSpeed());
		
		// the rest of the method body tracks whether
		// to play the idle animation
		idleCounter += delta;
		
		if (getXSpeed() != 0 || getYSpeed() != 0) {
			idleCounter = 0;
		} else if (idleCounter >= IDLEMS) {
			idleAnim.update(delta);
		}
	}
	
	/**
	 * Render the correct animation according to the camera's position.
	 * 
	 * The field img in Entity is not drawn here.
	 * Rather, img acts as a hitbox while the displayed animation is only for show.
	 * 
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public void render(float x, float y) {
		Image newImg;
		
		// the if statements initialized newImg with the proper frame to show
		if (idleCounter >= 5_000) {
			newImg = idleAnim.getCurrentFrame();
		} else if (isJumping() && getYSpeed() <= 0) {
			newImg = jumpingImg;
		} else if (isJumping() && getYSpeed() > 0) {
			newImg = fallingImg;
		} else if (this.getXSpeed() == 0) {
			newImg = standingImg;
		} else {
			newImg = walkingAnim.getCurrentFrame();
		}
		
		// goingLeft keeps track of where the player is facing,
		// so the frames can get flipped accordingly
		newImg = newImg.getFlippedCopy(!goingLeft,false);
		
		// posX and posY will line up the frame with the hitbox
		int posX = (int) x - newImg.getWidth()/2 + getWidth()/2,
			posY = (int) y - newImg.getHeight()/2 - getHeight() + 4;
		
		newImg.draw(posX,posY);
		// this last commented line draws the actual image, the hitbox
		// it is for debug purposes only
		// getImage().draw(Math.round(x),Math.round(y));
	}
	
}
