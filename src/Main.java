import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main {
	
	private static final float RATIO = 16/9f;
	private static final int HEIGHT = 720, WIDTH = (int) (HEIGHT * RATIO);

	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new Game("I Would Like To Be The Guy"));
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setTargetFrameRate(300);
		app.start();
		
	}
}

