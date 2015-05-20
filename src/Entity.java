import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.SlickException;

/**
 * All objects in the game world are represented by Entity.
 * The shared properties allow for easily implemented updating, rendering and collision detection.
 */
public class Entity {
	
	private int x,y;
	private int width,height;
	private Image img;
	private int dx,dy;
	private boolean solid;
	private int xOffset,yOffset;
	public Shape hitbox;
		
	
	
	
	/**
	 * Create a new Entity.
	 * Automatically sets up a rectangular hitbox lining up with img.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param img Image
	 * @param solid Can Entity objects pass through this object?
	 */
	public Entity(int x, int y, Image img, boolean solid) {
		this.x = x;
		this.y = y;
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
		dx = 0;
		dy = 0;
		this.solid = solid;
		setHitbox(new Rectangle(x,y,img.getWidth(),img.getHeight()),0,0);
	}
	
	public Entity(int x, int y, String path, boolean solid) throws SlickException {
		this(x,y,new Image(path), solid);
	}
	
	/**
	 * Set the hitbox used for collision detection. If an entity has an hitbox,
	 * it is collidable against other entities.
	 * @param xOffset
	 *            The offset of the hitbox on the x axis. Relative to the top
	 *            left point of the entity.
	 * @param yOffset
	 *            The offset of the hitbox on the y axis. Relative to the top
	 *            left point of the entity.
	 */
	public void setHitbox(Shape shape, int xOffset, int yOffset) {
		hitbox = shape;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.solid = true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void increaseX(int increment) {
		x += increment;
	}
	
	public void increaseY(int increment) {
		y += increment;
	}
	
	public Image getImage() {
		return img;
	}
	
	public int getDX() {
		return dx;
	}
	
	public int getDY() {
		return dy;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public Shape getHitbox(){
		return hitbox;
	}
	
	/**
	 * Update this object, taking the time delta into account.
	 * 
	 * @param delta Time
	 */
	public void update(GameContainer container, int delta) {
		
	}
	
	/**
	 * Render image at (x,y).
	 */
	public void render(int x, int y) {
		img.draw(x,y);
	}
	
}
