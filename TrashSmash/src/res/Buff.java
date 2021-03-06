package res;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class defines a buff object (power-up)
 * @author Bryan Chen
 */
public class Buff implements Drawable{
	
	private int x, y, typeCode;
	private final static int width = 29, height = 29;
	private final static int HP = 0, SPD = 1, SHK = 2, HLP = 3;
	private BufferedImage image;
	private Rectangle2D boundBox;
	private boolean isDead = false;
	
	/**
	 * Constructor. Builds a power-up of given type.
	 * 
	 * @param x x-location
	 * @param y y-location
	 * @param typeCode the type of power-up
	 */
	public Buff(int x, int y, int typeCode) {
		this.setX(x);
		this.setY(y);
		this.typeCode = typeCode;
		try {
			this.image = ImageIO.read(getClass().getClassLoader().getResource(this.getFileString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.boundBox = new Rectangle2D.Double(this.x, this.y, Bullet.width, Bullet.height);
	}

	/**
	 * Interprets typeCode and loads corresponding image.
	 * @return
	 */
	private String getFileString() {
		String fileString = "";
		switch(typeCode) {
			case HP:	fileString = "BuffIcons/hpBuff.png";
					break;
			case SPD:	fileString = "BuffIcons/speedBuff.png";
					break;
			case SHK:	fileString = "BuffIcons/shockwaveBuff.png";
					break;
			case HLP:	fileString = "BuffIcons/reinforceBuff.png";
					break;
		}
		return fileString;
	}

	@Override
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, null, x, y);
	}

	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
	 return height;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	public Rectangle2D getBoundBox() {
		return boundBox;
	}	
	
	/**
	 * Checks collision between the the power-up and player's ship.
	 * @param ship
	 * @return
	 */
	public boolean checkCollision(Ship ship) {
		if(this.x + this.getWidth()/2 >= ship.getX() && this.x <= ship.getX() + Ship.getWidth()/2) {
			if(this.y + this.getHeight()/2 >= ship.getY() && this.y <= ship.getY() + Ship.getHeight()/2) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	
}
