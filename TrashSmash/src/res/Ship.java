package res;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ship implements Drawable{
	private int x, y, health, gunSet, velocity;
	private int width = 128, height = 128;
	private BufferedImage image;
	
	public Ship(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setHealth(3);
		this.setGunSet(0);
		this.velocity = 1;
		try {
			this.image = ImageIO.read(new File("Assets/Blue/1.png"));
		} catch(IOException e) {
			System.out.println("Cannot find Assets/Blue/1.png");
		}
	}

	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}

	public int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	public int getGunSet() {
		return gunSet;
	}

	private void setGunSet(int gunSet) {
		this.gunSet = gunSet;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, null, x, y);
	}
	
	public void move(int direction) {
		if(direction == MovePattern.RIGHT) {
			this.x += velocity;
		}
		if(direction == MovePattern.LEFT) {
			this.x -= velocity;
		}
	}
	
	public void damage() {
		this.health -= 1;
		if(health < 0) {
			health = 0;
		}
	}
	
	public void cycleGun() {
		this.gunSet++;
		if(gunSet == 2) {
			gunSet = 0;
		}
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
	
}
