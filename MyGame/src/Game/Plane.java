package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	
	boolean left,right,up,down;
	int speed = 10;
	boolean life = true;
	
	public void drawSelf(Graphics g) {
		if(life) {
			g.drawImage(image, (int)x, (int)y, null);
			if (left&&x>=20) {
				x-=speed;
			}
			if (right&&x<=450) {
				x+=speed;
			}
			if (up&&y>=50) {
				y-=speed;
			}
			if (down&&y<=270) {
				y+=speed;
			}
		}
		
	}
	
	public Plane(Image img,double x,double y) {
		this.image = img;
		this.x = x;
		this.y = y;
		this.speed = 3;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	
	public void addDirection(KeyEvent e) {
		System.out.println("##"+e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
		
	}
	
	public void cancleDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
		
	}
}
